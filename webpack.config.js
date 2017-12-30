const path = require('path');

module.exports = {
    entry:{
        timetracking:'./src/main/webapp/timetracking/js/main.js'
    },
    output:{
        filename:'[name].bundle.js',
        path:path.resolve(__dirname, 'src/main/webapp/timetracking/dist')
    },
    devtool:'eval-source-map',
    module:{
        rules:[
            {
                test:/\.js$/,
                exclude:/(node_modules|bower_components)/,
                use:{
                    loader:'babel-loader',
                    options:{
                        presets:['babel-preset-env']
                    }
                }
            },
            {
                test:/\.css$/,
                use:['style-loader', 'css-loader']
            },
            {
                test:/\.(png|svg|jpg|gif)$/,
                use:['file-loader']
            },
            {
                test:/\.scss$/,
                use:['style-loader', 'css-loader', 'sass-loader']
            },
            {
                test:/\.html$/,
                use:['html-loader']
            },
            {
                test:/\.(woff|woff2|eot|ttf|otf)$/,
                use:['file-loader']
            }
        ]
    }
};
