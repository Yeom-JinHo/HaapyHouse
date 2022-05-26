package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.MemberServiceImpl;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;





@RestController
@RequestMapping("/users")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	MemberService userService;
	
	@Autowired
	private jwtServiceImpl jwtService;
	
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(@RequestParam("userId") String userId, @RequestParam(name = "socialType") String socialType) {
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("userId", userId);
		System.out.println(socialType);
		resultMap.put("socialType", socialType);
		try {
			if(userService.idCheck(resultMap)) {
				return new ResponseEntity<String>(SUCCESS , HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(FAIL , HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertUser(@RequestBody MemberDto user) {
		try {
			if(userService.insertUser(user)) {
				return new ResponseEntity<String>(SUCCESS , HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(FAIL , HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<MemberDto>>  getAllUsers() throws Exception{
		return new ResponseEntity<List<MemberDto>> (userService.getAllUsers() , HttpStatus.OK);
	}

//	@GetMapping("/{userId}")
//	public ResponseEntity<MemberDto>  getUserByUserId(@PathVariable String userId) throws Exception {
//		return new ResponseEntity<MemberDto> (userService.getUserByUserId(userId) , HttpStatus.OK);
//	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<String> updateUserPw(@RequestBody MemberDto user) throws Exception {
		
		try {
			if(userService.updateUserPw(user)) {
				return new ResponseEntity<String>(SUCCESS , HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(FAIL , HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId) throws Exception {

		try {
			if(userService.deleteUser(userId)) {
				return new ResponseEntity<String>(SUCCESS , HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(FAIL , HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto userDto,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("로그인 요청");
		MemberDto loginUser;
		try {
			
			if(userDto.getSocialType() == null) {
				loginUser = userService.login(userDto);
			}else {
				loginUser = userDto;
			}
			
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());
				System.out.println(refreshToken);
				userService.saveRefreshToken(userDto.getUserId(), refreshToken);
				logger.debug("access토큰정보 : {}", accessToken);
				logger.debug("refresh 토큰정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
				Cookie cookie = new Cookie("refresh-token", refreshToken);
				cookie.setHttpOnly(true);
				cookie.setSecure(true);
				cookie.setPath("/");
				response.addCookie(cookie);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	@GetMapping("/userinfo/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userid,@RequestParam(name = "socialType") String socialType,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, String> userMap = new HashMap<>();
		userMap.put("userid", userid);
		System.out.println(socialType);
		userMap.put("socialType", socialType);
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto userDto = userService.getUserByUserId(userMap);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@PutMapping("logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			userService.deleteRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
				
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto user, HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		jwtService.checkToken(token);
		
		if(token.equals(userService.getRefreshToken(user.getUserId()))) {
			String accessToken= jwtService.createAccessToken("userid", user.getUserId());
			resultMap.put("access-token", accessToken);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
}
