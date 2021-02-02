<template>
  <div class="example">
  <button @click="$bvModal.show('pos-check-modal')" class="addfeed__button">등록</button>
  <b-modal id="pos-check-modal" modal-class="pos-check-modal" hide-header hide-footer centered size="sm">
    <div class="pos-check-modal-body">
      <div class="pos-check-title">
        글을 등록하시겠습니까?
      </div>
      <button class="pos-check-yes-button" @click="addFeed">예</button>
      <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal')">아니오</button>
    </div>
  </b-modal>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.pos-check-yes-button {
  color:white;
  /* background-color:#CB3E47; */ /* 삭제, 탈되 확인 색 */
  background-color:#9279e9;  /* 수정, 등록 확인 색 */
  border-radius:10px;
  font-size:14px;
  width:100px;
  height:30px;
}
.pos-check-no-button {
  color:#F3F3F3;
  background-color:#707070;
  border-radius:10px;
  font-size:14px;
  width:100px;
  height:30px;
  margin-left:15px;
}
::v-deep .pos-check-modal > .modal-dialog >.modal-content{
  background-color: #E8E8E8;
  border: 1px solid #707070;
  border-radius:15px;
  font-size:14px;
  width:310px;
  margin:auto;
}
.pos-check-title {
  height:60px;
  line-height:60px;
  font-weight:700;
}
.pos-check-modal-body {
  text-align:center;
  
}
</style>