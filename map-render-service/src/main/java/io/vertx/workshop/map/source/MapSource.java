/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map.source;

import java.util.List;

import io.vertx.workshop.map.BoundingBox;

/**
 * Map Source is a class capable of loading some source map data into a structure we can use to render.
 *
 * @author Paulo Lopes
 */
public interface MapSource {

  List<Way> getWaysInBoundingBox(BoundingBox bbox);
}
