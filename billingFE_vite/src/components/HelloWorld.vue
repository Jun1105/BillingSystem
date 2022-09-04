<!-- @format -->

<template>
  <h1>{{ msg }}</h1>
  <button @click="handleClick">count is: {{ count }}</button>
  <p>
    Edit
    <code>components/HelloWorld.vue</code>
    to test hot module replacement.
  </p>
  <div class="first">
    <div class="a">aaa</div>
  </div>
</template>

<script lang="ts">
  import { login } from '@/api/login'
  export default {
    name: 'HelloWorld',
    props: {
      msg: String,
    },
    data() {
      return {
        count: 0,
      }
    },
    methods: {
      handleClick() {
        login()
          .then((result) => {
            console.log(result)
          })
          .catch((err) => {})
      },
    }
  }
</script>
<style lang="scss">
  .first {
    .a {
      color: red;
    }
  }
</style>
