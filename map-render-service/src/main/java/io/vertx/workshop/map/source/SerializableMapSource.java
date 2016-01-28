/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map.source;

import io.vertx.workshop.map.BoundingBox;
import io.vertx.workshop.map.MapException;
import io.vertx.workshop.map.index.QTree;
import io.vertx.workshop.map.source.osm.OSMReader;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Map Source is a class capable of loading some source map data into a structure we can use to render.
 *
 * @author Paulo Lopes
 */
public class SerializableMapSource extends OSMReader implements MapSource {

  private static final Logger LOG = Logger.getLogger(SerializableMapSource.class.getName());

  private QTree<Way> wayIndex;

  @SuppressWarnings("unchecked")
  public SerializableMapSource(String filename) throws MapException, ClassNotFoundException, IOException {
    long tRead = System.currentTimeMillis();
    File fIndex = new File(filename + ".idx.gz");
    if (fIndex.exists()) {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(fIndex));
      wayIndex = (QTree<Way>) in.readObject();
      in.close();
    } else {
      FileInputStream in = new FileInputStream(filename);
      load(in);
      in.close();
      ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(fIndex)));
      out.writeObject(wayIndex);
      out.close();
    }
    LOG.info("OSM loading done (" + ((System.currentTimeMillis() - tRead) / 1000f) + ") secs");
  }

  @SuppressWarnings("unchecked")
  public SerializableMapSource(InputStream in) throws ClassNotFoundException, IOException {
    long tRead = System.currentTimeMillis();
    ObjectInputStream oin = new ObjectInputStream(new GZIPInputStream(in));
    wayIndex = (QTree<Way>) oin.readObject();
    oin.close();
    LOG.info("OSM loading done (" + ((System.currentTimeMillis() - tRead) / 1000f) + ") secs");
  }

  @Override
  public List<Way> getWaysInBoundingBox(BoundingBox bbox) {
    return wayIndex.get(bbox);
  }

  @Override
  public void initIndex(BoundingBox bbox) {
    wayIndex = new QTree<>(bbox);
  }

  @Override
  public void indexWay(Way w) {
    wayIndex.add(w);
  }

  public static void main(String[] args) throws Exception {
    new SerializableMapSource(args[0]);
  }
}
