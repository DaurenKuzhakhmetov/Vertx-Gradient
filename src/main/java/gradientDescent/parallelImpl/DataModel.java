package gradientDescent.parallelImpl;

public class DataModel {
    private double[][] points;
    private double[] thetas;
       public DataModel(double[][] points,double[]thetas){
           this.points = points;
           this.thetas  = thetas;
       }
    public double[][] getPoints(){
           return points;
    }

    public double[] getThetas(){
        return thetas;
    }
}
