/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map.source;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

/**
 * OSM Member
 *
 * @author Paulo Lopes
 */
@DataObject(generateConverter = true)
class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  private Node node;
  private Way way;
  private String role;
  private Member next;

  public Member() {}

  public Member(JsonObject json) {
    MemberConverter.fromJson(json, this);
  }

  public Member(Member member) {
    this.node = member.node;
    this.way = member.way;
    this.role = member.role;
    this.next = member.next;
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public Way getWay() {
    return way;
  }

  public void setWay(Way way) {
    this.way = way;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Member getNext() {
    return next;
  }

  public void setNext(Member next) {
    this.next = next;
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    MemberConverter.toJson(this, json);
    return json;
  }
}
