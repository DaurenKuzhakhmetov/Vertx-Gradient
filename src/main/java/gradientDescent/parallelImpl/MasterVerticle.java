package gradientDescent.parallelImpl;

import com.sun.deploy.util.ArrayUtil;
import gradientDescent.GradientDescentUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;



public class MasterVerticle extends AbstractVerticle {
  private double[][] points;
  private GradientDescentUtils utils = GradientDescentUtils.getInstance();
    public MasterVerticle(double[][]points) {
        this.points = points;
    }

  public void start(Future<Void> startFuture){
      long startTime = System.currentTimeMillis();
      double[] initThetas = {2,2};  // default thetas
      System.out.println("Wait result..");

      for(int i=0;i<25;i++) {
          vertx.deployVerticle(SlaveVerticle.class.getName(), new DeploymentOptions().setInstances(8));
          double sumGradient =0;
          for (int k = 0; k < 8; k++) {
              DataModel dataModel = new DataModel(points, initThetas);
              vertx.eventBus().send(
                      "sample.data",
                      dataModel,  // i have a problem in here, may be need to convert to byte array
                      // see : https://github.com/eclipse-vertx/vert.x/issues/1224
                      r -> {
                         double gradient = (double) r.result().body();
                      }
              );
          }
      }
      long endTime = System.currentTimeMillis();
      System.out.println(endTime - startTime + " ms");
      vertx.close();
  }
}