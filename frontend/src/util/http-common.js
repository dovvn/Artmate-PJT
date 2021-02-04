import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://i4b202.p.ssafy.io:7777/",
  headers: {
    "Content-type": "application/json"
  }
});
