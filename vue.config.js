module.exports = {
  css: {
    // Enable CSS source maps.
    sourceMap: process.env.NODE_ENV !== "production",
  },
  devServer: {
    proxy: {
      "/oauth2.0": {
        target: "https://nid.naver.com/",
        changeOrigin: true,
      },
      "/v1": {
        target: "https://openapi.naver.com/",
        changeOrigin: true,
      },
    },
  },
};
