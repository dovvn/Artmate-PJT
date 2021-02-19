import {createInstance} from './index.js';

const instance = createInstance();

function getFeedList(success,fail){
  instance
  .get(`/api/feed/popular`)
  .then(success)
  .catch(fail)
}

export {getFeedList}