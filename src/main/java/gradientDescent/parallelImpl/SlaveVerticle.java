package gradientDescent.parallelImpl;

import gradientDescent.GradientDescentUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import javax.xml.crypto.Data;

public class SlaveVerticle extends AbstractVerticle{
    GradientDescentUtils utils = GradientDescentUtils.getInstance();
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.eventBus().<DataModel>consumer("sample.data", message -> {
            double[][] points = message.body().getPoints();
            double[]thetas = message.body().getThetas();
            double gradient = utils.calculateGradient(points,thetas);
            message.reply(gradient);
        });
        vertx.close();
    }
}
