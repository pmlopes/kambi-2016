package io.vertx.workshop.data;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Checkin {

  private String name;

  private double lon;

  private double lat;

  public Checkin() {
    // Empty constructor
  }

  public Checkin(Checkin other) {
    this.name = other.name;
    this.lon = other.lon;
    this.lat = other.lat;
  }

  public Checkin(JsonObject json) {
    CheckinConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    CheckinConverter.toJson(this, json);
    return json;
  }

  public double getLat() {
    return lat;
  }

  public Checkin setLat(double lat) {
    this.lat = lat;
    return this;
  }

  public double getLon() {
    return lon;
  }

  public Checkin setLon(double lon) {
    this.lon = lon;
    return this;
  }

  public String getName() {
    return name;
  }

  public Checkin setName(String name) {
    this.name = name;
    return this;
  }
}
