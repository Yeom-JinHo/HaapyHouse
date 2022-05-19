import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:5500/notice",
  headers: {
    "Content-type": "application/json",
  },
});
