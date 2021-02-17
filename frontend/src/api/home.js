import {createInstance} from './index.js';

const instance = createInstance();

function getFeedList(success,fail){
  instance
  .get(`/api/feed/popular`)
  .then(success, console.log("들어가냐"))
  .catch(fail)
}

export {getFeedList}