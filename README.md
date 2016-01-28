# Vertx.x Check-in

This is a simple demo used for vert.x talks. The application demonstrates several features from the vert.x project such as:

* Framework (we need to include a jar dependency to use it)
* Unopiniated (the projects follow a standard maven structure but is not required to be se)
* Reactive (all code is async)
* Polyglot (examples are in Java, JavaScript and Groovy)
* Distributed (client and frontend from a cluster with data service to get data)

## Components

### Data Storage Service

This service reads and writes data to a Mongo database it show the reactive side of Vert.x as well how to connect to a data source.

It shows how to use service proxies to generate bindings for other languages and use the service across the cluster as if it was a local instance.

### Data Storage Client

Shows how to form a cluster and use the proxy.

It also shows that the proxy and code does not to be in the same language as the implementation.

### Frontend

Shows how to create a HTTP REST API and extend the EventBus to the web with the SockJSBridge.

### Map Render Service

Shows that we can consume services and scale at will.
