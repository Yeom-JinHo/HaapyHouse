import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:5500/users",
  headers: {
    "Content-type": "application/json",
  },
});
