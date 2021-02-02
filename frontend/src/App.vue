<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  data(){
    return{
    }
  }
};
</script>

<style lang="scss">
#app {
  // font-family: Avenir, Helvetica, Arial, sans-serif;
  // -webkit-font-smoothing: antialiased;
  // -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  // color: #2c3e50;
  height: 100%;
  background-color: rgba(var(--b3f,250,250,250),1)
}

#nav {
  a {
    font-weight: bold;
    color: #CCCCCC;

    &.router-link-exact-active {
      color: #A593DF;
    }
  }
}
</style>
