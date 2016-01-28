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
 * Converter for {@link io.vertx.workshop.map.source.Node}.
 *
 * NOTE: This class has been automatically generated from the {@link io.vertx.workshop.map.source.Node} original class using Vert.x codegen.
 */
public class NodeConverter {

  public static void fromJson(JsonObject json, Node obj) {
    if (json.getValue("lat") instanceof Number) {
      obj.setLat(((Number)json.getValue("lat")).doubleValue());
    }
    if (json.getValue("layer") instanceof Number) {
      obj.setLayer(((Number)json.getValue("layer")).intValue());
    }
    if (json.getValue("lon") instanceof Number) {
      obj.setLon(((Number)json.getValue("lon")).doubleValue());
    }
    if (json.getValue("tags") instanceof JsonObject) {
      java.util.Map<String, java.lang.String> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("tags").forEach(entry -> {
        if (entry.getValue() instanceof String)
          map.put(entry.getKey(), (String)entry.getValue());
      });
      obj.setTags(map);
    }
  }

  public static void toJson(Node obj, JsonObject json) {
    json.put("lat", obj.getLat());
    json.put("layer", obj.getLayer());
    json.put("lon", obj.getLon());
    if (obj.getTags() != null) {
      JsonObject map = new JsonObject();
      obj.getTags().forEach((key,value) -> map.put(key, value));
      json.put("tags", map);
    }
  }
}