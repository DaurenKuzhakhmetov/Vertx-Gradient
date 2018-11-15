package gradientDescent.simpleImpl;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainGradientDescent {

    public static void main(String[] args) {
        System.out.println("wait result..");
        MainGradientDescent mainGradientDescent = new MainGradientDescent();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader("data.txt"));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        double[][] inputData = mainGradientDescent.convertStringToDouble(sb);


        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SimpleGradientDescent(inputData),new DeploymentOptions().setWorker(true));
    }



    private double[][] convertStringToDouble(StringBuilder builder){
        String convert = builder.toString();
        String [] array = convert.split("\n");
        double[][] inputData = new double[array.length][2];
        for(int i=0;i<array.length;i++)
        {
            inputData[i][0] = Double.parseDouble(array[i].substring(0,array[i].indexOf(" ")));
            inputData[i][1] = Double.parseDouble(array[i].substring(array[i].indexOf(" "),array[i].length()));
        }
        return inputData;
    }
}
