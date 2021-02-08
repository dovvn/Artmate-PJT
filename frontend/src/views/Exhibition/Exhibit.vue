<template>
  <div class="exdiv">
      <router-view></router-view>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.bm-burger-bars {
    background-color: white;
}
.exdiv {
  width: 380px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}

/* ------------------------------------------- */
@media screen and (min-width: 1024px) {
  .exdiv {
    width: 760px;
  }
}
</style>