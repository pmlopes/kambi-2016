/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map.source;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import io.vertx.workshop.map.BoundingBox;

/**
 * OSM Way
 *
 * @author Paulo Lopes
 */
@DataObject(generateConverter = true)
public class Way extends BoundingBox implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Node> nodes;

  private int layer;
  private String name;
  private Map<String, String> tags;

  public Way() {
    super(360.0, 360.0, -360.0, -360.0);
  }

  public Way(Way  way) {
    super(way.getMinLat(), way.getMinLon(), way.getMaxLat(), way.getMaxLon());
    nodes = way.getNodes();
  }

  public Way(JsonObject json) {
    WayConverter.fromJson(json, this);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getLayer() {
    return layer;
  }

  public void setLayer(int layer) {
    this.layer = layer;
  }

  public void insertTag(String key, String value) {
    if (tags == null) tags = new HashMap<>();
    tags.put(key, value);
  }

  public Map<String, String> getTags() {
    return tags;
  }

  public void setTags(Map<String, String> tags) {
    this.tags = tags;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public void addNode(Node node) {
    if (nodes == null) {
      nodes = new LinkedList<>();
    }

    nodes.add(node);
    double lat = node.getLat();
    double lon = node.getLon();

    if (lat < getMinLat()) setMinLat(lat);
    if (lat > getMaxLat()) setMaxLat(lat);
    if (lon < getMinLon()) setMinLon(lon);
    if (lon > getMaxLon()) setMaxLon(lon);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    WayConverter.toJson(this, json);
    return json;
  }
}
