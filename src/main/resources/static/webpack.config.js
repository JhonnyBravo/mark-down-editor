var path = require("path");

module.exports = {
    mode: "development",
    entry: {
        "build/edit_record": "./src/main/ts/client/edit_record.ts",
        "build/view_record": "./src/main/ts/client/view_record.ts"
    },
    output: {
        path: __dirname,
        filename: "[name].bundle.js"
    },
    resolve: {
        extensions: [".ts", ".tsx", ".js", ".json"]
    },
    module: {
        rules: [
            {
                test: /\.(ts|js)x?$/,
                exclude: /node_modules/,
                loader: "babel-loader"
            }
        ]
    }
};
