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
    height: 100vh;
    padding: 50px;
  }
</style>