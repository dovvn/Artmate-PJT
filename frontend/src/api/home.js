import {createInstance} from './index.js';

const instance = createInstance();

function getFeedList(userId,success,fail){
  instance
  .get(`/api/feed/popular/${userId}`)
  .then(success)
  .catch(fail)
}

export {getFeedList}