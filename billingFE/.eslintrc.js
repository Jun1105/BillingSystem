module.exports = {
    root: true,
    parserOptions: {
        "ecmaVersion": 7,
        sourceType: 'module'
    },
    "parser": "vue-eslint-parser",   // 添加这一句
    rules: {
        "@typescript-eslint/explicit-module-boundary-types": "off"
    }
}