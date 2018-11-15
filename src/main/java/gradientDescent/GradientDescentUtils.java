package gradientDescent;

public class GradientDescentUtils {
    public final double E = 0.01; // condition for stop
    private final double learning_rate = 0.001; // const step]



    // formula for y^ = endX + endY * x; // prediction
    private GradientDescentUtils(){}
    private static final GradientDescentUtils INSTANCE = new GradientDescentUtils();

    public double calculateGradient(double[][]points,double[]thetas){
      double gradient =0;
        for(int i=0;i<points.length;i++){
            gradient+= (calculate_H_theta(thetas,points[i][0])-points[i][1])*points[i][0];
        }
        return gradient;
    }

    public double calculateСostFunction(double[][]points,double[]thetas){
        double costFunction = 0;
        double samples = points.length;
        for(int i=0;i<points.length;i++){
            costFunction+=Math.pow((calculate_H_theta(thetas, points[i][0]) - points[i][1]), 2)/(samples)/2;
        }
        return costFunction;
    }

    public double[] updateTheta(double[] oldThetas,double[][]points){
        double[] newThetas = new double[oldThetas.length];
        newThetas[0]= oldThetas[0] - learning_rate*calculatePartialDerivative1(oldThetas,points);
        newThetas[1] = oldThetas[1] - learning_rate*calculatePartialDerivative2(oldThetas,points);
        return newThetas;
    }


    private double calculate_H_theta(double[] thetas,double x){
        double h_theta = thetas[0] + thetas[1]*x;
        return h_theta;
    }

    private double calculatePartialDerivative1(double[]thetas,double[][]points){
        double partialDerivative = 0;
        double samples=points.length;
        for(int i=0;i<samples;i++){
            partialDerivative+=(calculate_H_theta(thetas,points[i][0])-points[i][1])/samples;
        }
        return partialDerivative;
    }

    private double calculatePartialDerivative2(double[]thetas,double[][]points){
        double partialDerivative = 0;
        double samples=points.length;
        for(int i=0;i<samples;i++){
            partialDerivative+=(calculate_H_theta(thetas,points[i][0])-points[i][1])*points[i][0]/samples;
        }
        return partialDerivative;
    }

    public static GradientDescentUtils getInstance(){
         return INSTANCE;
    }
}
