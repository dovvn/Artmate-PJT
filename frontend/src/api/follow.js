import {createInstance} from "./index.js";

const instance = createInstance();

function listFollower(userid, success, fail) {
  instance
  .get(`api/user/follower/${userid}`)
  .then(success)
  .catch(fail);
}

function listFollowing(userid, success, fail) {
  instance
  .get(`api/user/following/${userid}`)
  .then(success)
  .catch(fail);
}

export {listFollower, listFollowing};