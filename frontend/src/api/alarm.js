import {createInstance} from "./index.js";

const instance = createInstance();

function deleteAlarm(deleteId, success, fail) {
  instance
  .delete(`api/signal/${deleteId}`)
  .then(success)
  .catch(fail);
}

export {deleteAlarm};