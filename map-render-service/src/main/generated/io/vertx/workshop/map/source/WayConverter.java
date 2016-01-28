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
 * Converter for {@link io.vertx.workshop.map.source.Way}.
 *
 * NOTE: This class has been automatically generated from the {@link io.vertx.workshop.map.source.Way} original class using Vert.x codegen.
 */
public class WayConverter {

  public static void fromJson(JsonObject json, Way obj) {
    if (json.getValue("layer") instanceof Number) {
      obj.setLayer(((Number)json.getValue("layer")).intValue());
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("nodes") instanceof JsonArray) {
      json.getJsonArray("nodes").forEach(item -> {
        if (item instanceof JsonObject)
          obj.addNode(new io.vertx.workshop.map.source.Node((JsonObject)item));
      });
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

  public static void toJson(Way obj, JsonObject json) {
    json.put("layer", obj.getLayer());
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getNodes() != null) {
      json.put("nodes", new JsonArray(
          obj.getNodes().
              stream().
              map(item -> item.toJson()).
              collect(java.util.stream.Collectors.toList())));
    }
    if (obj.getTags() != null) {
      JsonObject map = new JsonObject();
      obj.getTags().forEach((key,value) -> map.put(key, value));
      json.put("tags", map);
    }
  }
}