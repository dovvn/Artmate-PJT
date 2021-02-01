import {createInstance} from "./index.js";

const instance = createInstance();

function listMemo(feedno, success, fail) {
  instance
  .get(`api/comment/${feedno}`)
  .then(success)
  .catch(fail);
}

function addMemo(memo, success,fail) {
  instance
  .post(`api/comment`, JSON.stringify(memo))
  .then(success)
  .catch(fail);
}

function modifyMemo(memo, success, fail) {
  instance
  .put(`api/comment`, JSON.stringify(memo))
  .then(success)
  .catch(fail);
}

function deleteMemo(memoId, success, fail) {
  instance
  .delete(`api/comment/${memoId}`)
  .then(success)
  .catch(fail);
}

export {listMemo, addMemo, modifyMemo, deleteMemo};