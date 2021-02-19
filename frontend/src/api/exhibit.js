import {createInstance} from "./index.js";

const instance=createInstance();

function getExhibitList(userId,success,fail){
  instance
  .get(`api/exhibit/${userId}`)
  .then(success)
  .catch(fail)
}

function getExhibitRecommend(userId,success,fail){
  instance
  .get(`api/exhibit/recommend/${userId}`)
  .then(success)
  .catch(fail)
}

function getListForMap(success,fail){
  instance
  .get("api/exhibit/map")
  .then(success)
  .catch(fail)
}

function getScrapBook(userId, success, fail){
  instance
  .get(`api/scrapbook/${userId}`)
  .then(success)
  .catch(fail)
}

function deleteScrapBook(userId, id, success, fail){
  instance
  .delete(`api/scrapbook/${userId}/${id}`)
  .then(success)
  .catch(fail)
}
function getOnlineExhibit(userId,success,fail){
  instance
  .get(`api/exhibit/online/${userId}`)
  .then(success)
  .catch(fail)
}
export {getExhibitList, getExhibitRecommend, getListForMap, getScrapBook, deleteScrapBook, getOnlineExhibit}