<template>
  <div id="Search">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: "Search",

}
</script>

<style scoped>
  @import '../../components/css/style.css';
  #Search{
    width: 100vw;
    height: 100vh;
  }
</style>