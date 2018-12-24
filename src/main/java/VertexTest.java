import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.pmw.tinylog.Logger;
import webapi.WebAPI;

public class VertexTest extends AbstractVerticle {

    private void deploy(Vertx vertx) {
        Logger.info("Initialised Vertx");

        vertx.deployVerticle(new WebAPI(), new DeploymentOptions(), complete -> {
            Logger.info("Deployed WebServer");
        });
    }

    @Override
    public void start() {
        deploy(vertx);
    }

    public static void main(String... args) {
        final Vertx vertx = Vertx.vertx();
        new VertexTest().deploy(vertx);
    }
}
