<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <button @click="handleClick">
      {{ title }}
    </button>
  </div>
</template>

<script>
import { title } from 'process';
import { getlogin } from '@/api/login'
export default {
  name: 'HelloWorld',
  props: {
    msg: {
      type: String,
      default: () => ''
    }
  },
  data() {
    return {
      title: '点我'
    }
  },
  methods: {
    handleClick() {
      getlogin().then(value => {
        console.log(value);
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
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
