<template>
  <div id="app">
    <Header></Header>
    {{msg}}
  </div>
</template>

<script>
  import Header from './components/ViewHeader';

  export default {
    name: 'app',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },

    mounted() {
      if (localStorage.name) {

        this.name = localStorage.name;
      } else {
        this.$axios
          .post('http://gateway-server:8080/auth/passwordLogin', {
            "loginName": "zhangsan520",
            "password": "e10adc3949ba59abbe56e057f20f883e"
          })
          .then(response => {
            console.log(response)
          })
      }

    },
    watch: {
      name(newName) {
        localStorage.name = newName;
      }
    },
    components: {Header}
  }
</script>

<style lang="scss">
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    height: 100%;
    width: 100%;
    border-color: blue;
  }

  html, body, #app {
    height: 100%;
  }

  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
