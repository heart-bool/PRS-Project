<template>
  <div id="header">
    <div class="header_item" style="margin-left: 50px">
      <a href="/"><img src="../assets/images/logo.png" alt="ss"/></a>
    </div>
    <div class="header_item"></div>
    <div class="header_item"></div>
  </div>
</template>

<script>
  export default {
    data () {
      return {

      }
    }
  }
</script>
<style type="scss">
  #header {
    height: 13%;
    width: 100%;
    box-shadow: 1px 1px 1px 1px lightslategrey;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .header_item {
    width: 80px;
    height: 80%;
    background-color: red;
    margin: 10px;
  }

  .header_item a img {
    width: 100%;
    height: 100%;
  }

</style>
