import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:9999/map",
  headers: {
    "Content-type": "application/json",
  },
});
