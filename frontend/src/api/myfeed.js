import {createInstance} from "./index.js";

const instance = createInstance();

function listMyfeed(userid, success, fail) {
  instance
  .get(`api/feed/list/${userid}`)
  .then(success)
  .catch(fail);
}

function detailFeed(feedno,userid, success, fail) {
  instance
  .get(`api/feed/${userid}/${feedno}`)
  .then(success)
  .catch(fail);
}

function addFeed(formData, success, fail) {
  instance
  .post(`api/feed`,formData,{
    headers: {
      "Content-Type": `multipart/form-data`,
    }
  })
  .then(success)
  .catch(fail);
}

function modifyFeed(formData, success, fail) {
  instance
  .put(`api/feed`,formData,{
    headers: {
      "Content-Type": `multipart/form-data`,
    }
  })
  .then(success)
  .catch(fail);
}

function deleteFeed(feedno, success, fail) {
  instance
  .delete(`api/feed/${feedno}`)
  .then(success)
  .catch(fail);
}

export {listMyfeed, detailFeed, addFeed, deleteFeed, modifyFeed};