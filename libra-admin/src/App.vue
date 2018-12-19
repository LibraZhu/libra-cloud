<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default{
    name: 'App'
  }
  resolve: {
    alias: {
      vue: 'vue/dist/vue.js'
    }
  }
</script>
