var Router = require('vertx-web-js/router');
var SockJSHandler = require('vertx-web-js/sock_js_handler');
var StaticHandler = require('vertx-web-js/static_handler');
var DataStorageService = require('vertx-microservice-workshop-js/data_storage_service');

var isFresh = require('./cache');

// create a http router
var eb = vertx.eventBus();
var router = Router.router(vertx);

// create services
var store = DataStorageService.createProxy(vertx, 'vertx.checkin');

// Allow events for the designated addresses in/out of the event bus bridge
var opts = {
  inboundPermitteds:  [{address: 'vertx.checkin'}],
  outboundPermitteds: [{address: 'vertx.checkin.announce'}]
};

var cache = 86400;

// Create the event bus bridge and add it to the router.
router.route('/eventbus/*').handler(SockJSHandler.create(vertx).bridge(opts).handle);

// handle request to places
router.get('/checkin/:name/:lat/:lon').handler(function (ctx) {
  var name = ctx.request().getParam('name');
  var lat = parseFloat(ctx.request().getParam('lat'));
  var lon = parseFloat(ctx.request().getParam('lon'));

  store.checkin(name, lat, lon, function (res, err) {
    if (err) {
      ctx.fail(err);
    } else {
      ctx.response().end();
    }
  })
});

// handle tiles
router.route('/:z/:x/:y.png').handler(function (ctx) {
  var x = ctx.request().getParam('x');
  var y = ctx.request().getParam('y');
  var z = ctx.request().getParam('z');

  var etag = '\'' + z + '-' + x + '-' + y + '\'';

  ctx.response()
    .putHeader('etag', etag)
    .putHeader('cache-control', 'public, max-age=' + cache);

  if (isFresh(etag, ctx.request())) {
    // some caching code here
    ctx.response()
      .setStatusCode(304)
      .end();

  } else {
    eb.send('map-render', {}, {headers: {x: x, y: y, z: z}}, function (res, err) {
      if (err) {
        ctx.response().setStatusCode(500).end(err);
      } else {
        ctx.response().end({_jdel: res.body()});
      }
    });
  }
});

// Serve the static resources
router.route().handler(StaticHandler.create().handle);

vertx.createHttpServer().requestHandler(router.accept).listen(8080);