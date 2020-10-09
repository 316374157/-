module.exports = {
    devServer: {
        disableHostCheck: true,
        host: '127.0.0.1',
        port: 80,
    },
    lintOnSave: false,
    transpileDependencies: [
        'vue-echarts',
        'resize-detector'
    ]
}