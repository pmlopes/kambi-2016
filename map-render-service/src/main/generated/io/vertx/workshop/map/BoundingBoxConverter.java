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

package io.vertx.workshop.map;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link io.vertx.workshop.map.BoundingBox}.
 *
 * NOTE: This class has been automatically generated from the {@link io.vertx.workshop.map.BoundingBox} original class using Vert.x codegen.
 */
public class BoundingBoxConverter {

  public static void fromJson(JsonObject json, BoundingBox obj) {
    if (json.getValue("maxLat") instanceof Number) {
      obj.setMaxLat(((Number)json.getValue("maxLat")).doubleValue());
    }
    if (json.getValue("maxLon") instanceof Number) {
      obj.setMaxLon(((Number)json.getValue("maxLon")).doubleValue());
    }
    if (json.getValue("minLat") instanceof Number) {
      obj.setMinLat(((Number)json.getValue("minLat")).doubleValue());
    }
    if (json.getValue("minLon") instanceof Number) {
      obj.setMinLon(((Number)json.getValue("minLon")).doubleValue());
    }
  }

  public static void toJson(BoundingBox obj, JsonObject json) {
    json.put("maxLat", obj.getMaxLat());
    json.put("maxLon", obj.getMaxLon());
    json.put("minLat", obj.getMinLat());
    json.put("minLon", obj.getMinLon());
    if (obj.getNEQuadrant() != null) {
      json.put("neQuadrant", obj.getNEQuadrant().toJson());
    }
    if (obj.getNWQuadrant() != null) {
      json.put("nwQuadrant", obj.getNWQuadrant().toJson());
    }
    if (obj.getSEQuadrant() != null) {
      json.put("seQuadrant", obj.getSEQuadrant().toJson());
    }
    if (obj.getSWQuadrant() != null) {
      json.put("swQuadrant", obj.getSWQuadrant().toJson());
    }
  }
}