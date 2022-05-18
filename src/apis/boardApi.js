import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:80/notice",
  headers: {
    "Content-type": "application/json",
  },
});
