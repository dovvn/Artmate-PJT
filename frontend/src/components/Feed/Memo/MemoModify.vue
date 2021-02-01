<template>
  <div class="MemoModify">
    <div class="title">댓글 수정</div>
    <div class="feed__memo__write">
      <input placeholder="  댓글을 달아보세요." class="feed__memo__write__input" type="text">
      <button class="feed__memo__write__button">
        <font-awesome-icon icon="paper-plane"/>
      </button>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
@import "../../../components/css/style.css";
* {
  font-family: 'NanumBarunGothic', sans-serif;
}
.MemoModify {
  max-width:380px;
  margin:20px
}
.title {
  font-size:20px;
  font-weight:700;
  /* padding-top:25px; */
}
.feed__memo__write {
  color: #999999;
  margin-top:15pt;
  display:flex;
}

.feed__memo__write__input {
  background-color: #F5F5F5;
  border-radius: 20px;
  flex-basis:90%;
  height:22pt;
}
.feed__memo__write__button {
  font-size:13pt;
  margin-left:10pt;
}
</style>