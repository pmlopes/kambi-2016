import io.vertx.workshop.groovy.data.*

def service = DataStorageService.createProxy(vertx, "vertx.checkin")

service.getCheckins({ res ->
  if (res.failed()) {
    println "Cannot retrieve the list of checkins : " + res.cause()
  } else {
    println "Done"
    def list = res.result()
    list.each { map ->
      println map.name
    }
  }
})
