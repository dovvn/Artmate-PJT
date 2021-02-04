<template>
  <div class="oneBtn">
      <b-modal id="pos-added-modal" modal-class="pos-added-modal" hide-header hide-footer centered size="sm">
      <div class="pos-added-modal-body">
        <div class="pos-added-title">
          피드 등록이 완료되었습니다.
        </div>
        <button class="pos-added-check-button" @click="$bvModal.hide('pos-added-modal')">확인</button>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {

}
</script>

<style>
::v-deep .pos-added-modal > .modal-dialog >.modal-content{
  background-color: #E8E8E8;
  border: 1px solid #707070;
  border-radius:16px;
  font-size:14px;
  width:310px;
  margin:auto;
  box-shadow: #00000096 20px 20px 40px;
}


.pos-added-modal-body {
  text-align:center;
}

.pos-added-title {
  height:60px;
  line-height:60px;
  font-weight:500;
  /* text-align:center; */
}

.pos-added-check-button {
  color: #6D44FD;
  margin-top: 15px;
}
</style>