/** @format */

module.exports = {
  root: true, // 当前项目使用这个配置文件, 不会往父级目录找.eslintrc.js文件
  env: {
    // 指定eslint启动环境(vuecli底层是node支持), browser: true也可以在浏览器设置
    node: true, // Node.js 全局变量和 Node.js 作用域
    es6: true, // 启用 ES6 语法支持以及新的 ES6 全局变量或类型
    browser: true // 浏览器全局变量
  },
  parserOptions: {
    ecmaVersion: 7,
    sourceType: 'module',
    parser: '@typescript-eslint/parser'
  },
  extends: [
    //默认使用vue2的配置
    //"plugin:vue/essential",
    //修改使用vue3的规则
    'plugin:vue/vue3-strongly-recommended',
    'plugin:prettier/recommended',
    'plugin:@typescript-eslint/recommended'
  ],
  parser: 'vue-eslint-parser',
  plugins: ['vue', '@typescript-eslint'],
  rules: {
    quotes: [2, 'single'], // 强制使用一致的反勾号、双引号或单引号
    eqeqeq: [2, 'always'], // 要求使用 === 和 !==
    // indent: [2, 4],
    'no-unused-vars': 'off',
    'no-multiple-empty-lines': 0,
    '@typescript-eslint/explicit-module-boundary-types': 'off',
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off', // 上线环境用打印就报警告, 开发环境关闭此规则
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off', // debugger可以终止代码执行
    'no-multiple-empty-lines': 'off', // 不允许有连续多行空行(关闭规则)
    'no-const-assign': 2, //禁止修改const声明变量
    'comma-dangle': 2, // 数组和对象键值对最后一个逗号， never参数：不能带末尾的逗号, always参数：必须带末尾的逗号
    'vue/multi-word-component-names': 0, //关闭驼峰命名规则
    'arrow-parens': ['error', 'as-needed'], //箭头函数括号规则
    'comma-spacing': [2, { before: false, after: true }] // 控制逗号前后的空格
  }
}
