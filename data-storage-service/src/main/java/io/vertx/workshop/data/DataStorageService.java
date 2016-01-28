package io.vertx.workshop.data;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

/**
 * Service exposed on the event bus to provide access to
 * the stored Places.
 */
@VertxGen
@ProxyGen
public interface DataStorageService {

  /**
   * Method called to create a proxy (to consume the service).
   *
   * @param vertx   vert.x
   * @param address the address on the vent bus where the service is served.
   * @return the proxy on the {@link DataStorageService}
   */
  static DataStorageService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(DataStorageService.class, vertx, address);
  }

  void checkin(String name, double lat, double lon, Handler<AsyncResult<Void>> resultHandler);

  void getCheckins(Handler<AsyncResult<List<Checkin>>> resultHandler);
}
