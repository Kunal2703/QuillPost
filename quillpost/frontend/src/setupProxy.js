const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  // Proxy requests to the external API through your Node.js server
  app.use(
    '/api/register',
    createProxyMiddleware({
      target: 'http://authentication:8000',
      changeOrigin: true,
    })
  );

  app.use(
    '/api/login',
    createProxyMiddleware({
      target: 'http://authentication:8000',
      changeOrigin: true,
    })
  );

  app.use(
    '/api/post/*',
    createProxyMiddleware({
      target: 'http://comments:8000',
      changeOrigin: true,
    })
  );

  app.use(
    '/api/**',
    createProxyMiddleware({
      target: 'http://post:8080',
      changeOrigin: true,
    })
  );

 


};