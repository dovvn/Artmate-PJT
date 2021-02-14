import {createInstance} from './index.js';

const instance = createInstance();

function getFeedList(userId,success,fail){
  instance
  .get(`api/feed/allList/${userId}`)
  .then(success)
  .catch(fail)
}

export {getFeedList}