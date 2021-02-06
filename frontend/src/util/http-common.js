import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://i4b202.p.ssafy.io/",
  headers: {
    "Content-type": "application/json"
  }
});
