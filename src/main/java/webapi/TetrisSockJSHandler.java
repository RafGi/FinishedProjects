package webapi;

import io.vertx.core.Vertx;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

class TetrisSockJSHandler {

    private final SockJSHandler sockJSHandler;

    TetrisSockJSHandler(final Vertx vertx) {
        sockJSHandler = SockJSHandler.create(vertx);
    }

    private void addBridgeOptions(String permittedAddress) {
        final PermittedOptions inbound = new PermittedOptions()
                .setAddressRegex(permittedAddress);

        final PermittedOptions outbound = inbound;
        final BridgeOptions options = new BridgeOptions()
                .addInboundPermitted(inbound)
                .addOutboundPermitted(outbound);
        sockJSHandler.bridge(options);
    }

    SockJSHandler create(String permittedAddress) {
        System.out.println("in create function");
        addBridgeOptions(permittedAddress);
        System.out.println("added bridgeoptions");
        return sockJSHandler;
    }
}
