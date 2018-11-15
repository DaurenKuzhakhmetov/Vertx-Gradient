package gradientDescent;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneratorData {
    public static void main(String[] args) {
        try(FileWriter writer = new FileWriter("data.txt", false))
        {
            Random random = new Random();
            for(int i=0;i<1000000;i++){
                for(int j=0;j<2;j++){
                   writer.write(String.valueOf((random.nextInt(2) + random.nextDouble()))+" ");
                }
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
