/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map.source;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * OSM Node
 *
 * @author Paulo Lopes
 */
@DataObject(generateConverter = true)
public class Node implements Serializable {

  private static final long serialVersionUID = 1L;

	private double lat;
	private double lon;
	private int layer;

	private Map<String, String> tags;

  public Node() {
  }

  public Node(Node node) {
    this.lat = node.lat;
    this.lon = node.lon;
    this.layer = node.layer;
  }

  public Node(JsonObject json) {
    NodeConverter.fromJson(json, this);
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }

  public Map<String, String> getTags() {
    return tags;
  }

  public void setTags(Map<String, String> tags) {
    this.tags = tags;
  }

  public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void addTag(String key, String value) {
		
		if(tags == null) {
      tags = new HashMap<>();
    }

		tags.put(key, value);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		NodeConverter.toJson(this, json);
		return json;
	}
}
