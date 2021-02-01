import {createInstance} from "./index.js";

const instance = createInstance();

function login(user, success, fail){
  instance.defaults.headers["accesss-token"] = window.localStorage.getItem(
    "access-token"
  );
  instance
  .post("api/user/login", JSON.stringify(user))
  .then(success)
  .catch(fail);
}
function join(user, success, fail){
  instance
  .post("api/user", JSON.stringify(user))
  .then(success)
  .catch(fail);
}
function checkName(userName, success, fail){
  instance
  .get(`api/user/checkname/${userName}`)
  .then(success)
  .catch(fail);
}
function findPw(userId, success, fail){
  instance
  .get(`api/user/findPw/${userId}`)
  .then(success)
  .catch(fail);
}
async function findById(userid, success, fail) {
  instance.defaults.headers["access-token"] = window.localStorage.getItem(
    "access-token"
  );
  await instance
    .get(`api/user/info/${userid}`)
    .then(success)
    .catch(fail);
}
export {login, join, checkName, findPw, findById};