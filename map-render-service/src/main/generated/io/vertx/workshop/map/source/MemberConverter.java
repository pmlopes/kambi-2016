/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.workshop.map.source;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link io.vertx.workshop.map.source.Member}.
 *
 * NOTE: This class has been automatically generated from the {@link io.vertx.workshop.map.source.Member} original class using Vert.x codegen.
 */
public class MemberConverter {

  public static void fromJson(JsonObject json, Member obj) {
    if (json.getValue("next") instanceof JsonObject) {
      obj.setNext(new io.vertx.workshop.map.source.Member((JsonObject)json.getValue("next")));
    }
    if (json.getValue("node") instanceof JsonObject) {
      obj.setNode(new io.vertx.workshop.map.source.Node((JsonObject)json.getValue("node")));
    }
    if (json.getValue("role") instanceof String) {
      obj.setRole((String)json.getValue("role"));
    }
    if (json.getValue("way") instanceof JsonObject) {
      obj.setWay(new io.vertx.workshop.map.source.Way((JsonObject)json.getValue("way")));
    }
  }

  public static void toJson(Member obj, JsonObject json) {
    if (obj.getNext() != null) {
      json.put("next", obj.getNext().toJson());
    }
    if (obj.getNode() != null) {
      json.put("node", obj.getNode().toJson());
    }
    if (obj.getRole() != null) {
      json.put("role", obj.getRole());
    }
    if (obj.getWay() != null) {
      json.put("way", obj.getWay().toJson());
    }
  }
}