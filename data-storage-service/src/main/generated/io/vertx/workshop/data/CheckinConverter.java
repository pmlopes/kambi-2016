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

package io.vertx.workshop.data;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link io.vertx.workshop.data.Checkin}.
 *
 * NOTE: This class has been automatically generated from the {@link io.vertx.workshop.data.Checkin} original class using Vert.x codegen.
 */
public class CheckinConverter {

  public static void fromJson(JsonObject json, Checkin obj) {
    if (json.getValue("lat") instanceof Number) {
      obj.setLat(((Number)json.getValue("lat")).doubleValue());
    }
    if (json.getValue("lon") instanceof Number) {
      obj.setLon(((Number)json.getValue("lon")).doubleValue());
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
  }

  public static void toJson(Checkin obj, JsonObject json) {
    json.put("lat", obj.getLat());
    json.put("lon", obj.getLon());
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
  }
}