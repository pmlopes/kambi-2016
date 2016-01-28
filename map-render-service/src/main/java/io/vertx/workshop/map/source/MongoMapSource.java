/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map.source;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.workshop.map.BoundingBox;
import io.vertx.workshop.map.MapException;
import io.vertx.workshop.map.index.QTree;
import io.vertx.workshop.map.source.osm.OSMReader;

import java.io.*;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Map Source is a class capable of loading some source map data into a structure we can use to render.
 *
 * @author Paulo Lopes
 */
public class MongoMapSource extends OSMReader implements MapSource {

  private static final Logger LOG = Logger.getLogger(MongoMapSource.class.getName());

  private final Semaphore semaphore = new Semaphore(250, true);

  private QTree<Way> wayIndex;
  private MongoClient mongo;

  public MongoMapSource(MongoClient mongo, String filename) throws MapException, ClassNotFoundException, IOException {
    long tRead = System.currentTimeMillis();
    this.mongo = mongo;
    try (FileInputStream in = new FileInputStream(filename)) {
      load(in);
    }
    LOG.info("OSM loading done (" + ((System.currentTimeMillis() - tRead) / 1000f) + ") secs");
  }

  public MongoMapSource(MongoClient mongo) throws MapException {
    long tRead = System.currentTimeMillis();
    this.mongo = mongo;
    // load the bounding box so we can create a geo spatial index
    mongo.findOne("bbox", new JsonObject(), null, findOne -> {
      if (findOne.succeeded()) {
        wayIndex = new QTree<>(new BoundingBox(findOne.result()));
        // now load all the ways
        mongo.find("ways", new JsonObject(), find -> {
          if (find.succeeded()) {
            for (JsonObject way : find.result()) {
              wayIndex.add(new Way(way));
            }
            LOG.info("OSM loading done (" + ((System.currentTimeMillis() - tRead) / 1000f) + ") secs");
          } else {
            throw new RuntimeException(find.cause());
          }
        });
      } else {
        throw new RuntimeException(findOne.cause());
      }
    });
  }

  @Override
  public List<Way> getWaysInBoundingBox(BoundingBox bbox) {
    return wayIndex.get(bbox);
  }

  @Override
  public void initIndex(BoundingBox bbox) {
    connection().insert("bbox", bbox.toJson(), res -> {
      release();
    });
  }

  @Override
  public void indexWay(Way w) {
    connection().insert("ways", w.toJson(), res -> {
      release();
    });
  }

  private MongoClient connection() {
    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return mongo;
  }

  private void release() {
    semaphore.release();
  }

  public static void main(String[] args) throws Exception {
    Vertx vertx = Vertx.vertx();
    MongoClient mongo = MongoClient.createShared(vertx, new JsonObject()
        .put("db_name", "osm")
        .put("connection_string", "mongodb://localhost:27017"));

    new MongoMapSource(mongo, "/home/plopes/Downloads/Amsterdam.osm");
  }
}
