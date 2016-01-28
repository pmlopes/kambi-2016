/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map.source;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.Map;

/**
 * OSM Relation
 *
 * @author Paulo Lopes
 */
@DataObject(generateConverter = true)
class Relation implements Serializable {

  private static final long serialVersionUID = 1L;

	private int id;
	private Map<String, String> tags;
	private Member member;

  public Relation() {}

  public Relation(JsonObject json) {
    RelationConverter.fromJson(json, this);
  }

  public Relation(Relation relation) {
    this.id = relation.id;
    this.tags = relation.tags;
    this.member = relation.member;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Map<String, String> getTags() {
    return tags;
  }

  public void setTags(Map<String, String> tags) {
    this.tags = tags;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    RelationConverter.toJson(this, json);
    return json;
  }
}
