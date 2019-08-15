const path = require("path");

module.exports = {
    mode: "development",
    entry: {
        "src/js/dist/index": "./src/js/index.js"
    },
    output: {
        path: __dirname,
        filename: "[name].bundle.js"
    },
    resolve: {
        modules: [
            path.join(__dirname, "src"),
            "node_modules"
        ],
        extensions: [".js"],
        alias: {
            "vue$": "vue/dist/vue.esm.js"
        }
    },
    module: {
        rules: [
            {
                test: "/\.js$/",
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                }
            }
        ]
    }
}
