module.exports = {
    // 代码换行的字符长度
    printWidth: 80,
    // 缩进长度
    tabWidth: 2,
    // 缩进的方式 false空格 或 trueTab
    useTabs: false,
    // 语句的结尾是否要使用分号
    semi: false,
    // 使用单引号代替双引号
    singleQuote: true,
    // 对象的属性是否需要添加引号
    quoteProps: 'as-needed',
    // 在 JSX 中使用单引号替代双引号
    jsxSingleQuote: false,
    // 元素末尾的逗号。默认为 es5
    trailingComma: 'es5',
    // 对象中的空格
    bracketSpacing: true,
    // 将 html 开始标签的后面一个 > 放在最后一行的末尾，而不是单独一行
    jsxBracketSameLine: false,
    // 当箭头函数只有一个参数时，是否加上括号
    arrowParens: 'always',
    // 指定编译指示：只格式化在文件顶部包含特定注释的文件。
    requirePragma: false,
    // 文件已经被 Prettier 格式化之后，会在文件顶部插入一个特殊的 @format 标记。
    insertPragma: true,
    // 所有空格都将认定为是无意义的
    htmlWhitespaceSensitivity: 'ignore',
    // vue 文件中，是否给 script 和 style 内的代码添加缩进。
    vueIndentScriptAndStyle: true,
    // 指定代码最后是否添加一行空行
    endOfLine: 'lf',
}
