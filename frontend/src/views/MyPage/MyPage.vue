<template>
  <div class="div">
      <router-view></router-view>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.div {
  width: 760px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}
/* ------------------------------ 반응형 ------------------------------ */
@media screen and (max-width: 1024px) {
  .div {
    width: 380px;
  }
}
</style>