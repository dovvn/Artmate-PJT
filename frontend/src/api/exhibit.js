import {createInstance} from "./index.js";

const instance=createInstance();

function getExhibitList(success,fail){
  instance
  .get("api/exhibit")
  .then(success)
  .catch(fail)
}

function getExhibitDetail(ex_id,success,fail){
  instance
  .get(`api/exhibit/${ex_id}`)
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