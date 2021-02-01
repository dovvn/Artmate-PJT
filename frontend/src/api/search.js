import {createInstance} from './index.js';

const instance = createInstance();

function searchPopularity(success,fail){
  instance
  .get('api/search/popular')
  .then(success)
  .catch(fail);
} 
function searchKeyword(keyword,success,fail){
  instance
  .get(`api/search/${keyword}`)
  .then(success)
  .catch(fail);
}
function searchList(success,fail){
  instance
  .get('api/search')
  .then(success)
  .catch(fail);
}

export {searchKeyword, searchPopularity, searchList};