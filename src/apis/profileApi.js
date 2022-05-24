import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:9999/profile",
  headers: {
    "Content-type": "application/json",
  },
});
