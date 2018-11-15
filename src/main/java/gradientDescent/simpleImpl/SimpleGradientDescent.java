package gradientDescent.simpleImpl;

import gradientDescent.GradientDescentUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import java.util.Arrays;

public class SimpleGradientDescent extends AbstractVerticle {
   private double[][]points;
   private GradientDescentUtils utils = GradientDescentUtils.getInstance();
    public SimpleGradientDescent(double[][]points){
         this.points = points;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
     long startTime = System.currentTimeMillis();
        double[] initThetas = {2,2};  // default thetas
        int count =0; //counter iterations
        double previous_cost = 10; // default
        double current_cost = utils.calculateСostFunction(points,initThetas);

       while(Math.abs(previous_cost-current_cost)>utils.E){
            previous_cost = current_cost;
            double [] thetas =  utils.updateTheta(initThetas,points);
            current_cost = utils.calculateСostFunction(points,thetas);
            initThetas = thetas;
            count++;
        }
        System.out.println("our thetas : "+Arrays.toString(initThetas));
        System.out.println("iterations: " + count);
     long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
        vertx.close();
    }
    }

