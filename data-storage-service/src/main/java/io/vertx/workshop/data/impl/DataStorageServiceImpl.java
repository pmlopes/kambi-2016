package io.vertx.workshop.data.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.workshop.data.Checkin;
import io.vertx.workshop.data.DataStorageService;
import io.vertx.ext.mongo.MongoClient;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link DataStorageService}.
 */
public class DataStorageServiceImpl implements DataStorageService {

  public static final String COLLECTION = "checkin";
  public static final Logger LOGGER = Logger.getLogger("Data Storage Service");

  private final MongoClient mongo;
  private final EventBus eb;

  public DataStorageServiceImpl(Vertx vertx, JsonObject config) {
    this.mongo = MongoClient.createShared(vertx, config, "osm");
    this.eb = vertx.eventBus();
    LOGGER.info("Data Storage Service instantiated");
  }

  @Override
  public void getCheckins(Handler<AsyncResult<List<Checkin>>> handler) {
    mongo.find(COLLECTION, new JsonObject(), find -> {
      if (find.succeeded()) {
        handler.handle(Future.succeededFuture(find.result().stream()
            .map(Checkin::new).collect(Collectors.toList())));
      } else {
        handler.handle(Future.failedFuture(find.cause()));
      }
    });
  }

  @Override
  public void checkin(String name, double lat, double lon, Handler<AsyncResult<Void>> handler) {
    final JsonObject query = new JsonObject().put("name", name);

    final JsonObject update = new JsonObject().put("$set", new JsonObject()
        .put("name", name)
        .put("lat", lat)
        .put("lon", lon));

    UpdateOptions options = new UpdateOptions().setUpsert(true);

    mongo.updateWithOptions(COLLECTION, query, update, options, res -> {
      if (res.failed()) {
        handler.handle(Future.failedFuture(res.cause()));
      } else {
        handler.handle(Future.succeededFuture());
        // after return we can continue doing some work
        eb.publish("vertx.checkin.announce", new JsonObject()
            .put("name", name)
            .put("lat", lat)
            .put("lon", lon));
      }
    });
  }

  public void close() {
    mongo.close();
  }
}
