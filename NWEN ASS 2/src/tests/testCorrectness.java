package tests;
import datasets.DataSetLoader;
import datasets.DataSetLoaderp;
import model.ModelParallel;
import model.Particle;
import model.Model;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.IntStream;

public class testCorrectness {
    ModelParallel mp;
    Model m;
    final int MAX_ITERATIONS = 500;

    @Test
    public void testRandom(){
        m=DataSetLoader.getRandomSet(100,800,100);
        mp=DataSetLoaderp.getRandomSet(100,800,100);
        testModelc(m,mp);
    }

    @Test
    public void testRotatingGrid(){
         m=DataSetLoader.getRandomRotatingGrid(0.02d,100, 800, 40);
         mp=DataSetLoaderp.getRandomRotatingGrid(0.02d,100, 800, 40);
        testModelc(m,mp);
    }

    @Test
    public void testRandomGrid(){
         m=DataSetLoader.getRandomGrid(100, 800, 30);
         mp =DataSetLoaderp.getRandomGrid(100, 800, 30);
        testModelc(m,mp);

    }


    public void testModelc(Model m,ModelParallel mp){
        for(int i =0;i<1000;i++){
            m.step();
            mp.step();
            assertTrue(compare(m,mp)==0);
        }


    }


    private int compare(Model m, ModelParallel mp){
        int iterations;
        boolean check;
        int fails=0;

        for(Particle i:m.p){
            iterations=0;
            check=false;
            for(Particle j:mp.p){
                if(j.equals(i)){
                    check=true;
                }
                if(!check&&iterations==mp.p.size()-1){
                    fails++;
                }
                iterations++;
            }
        }
        return fails;

    }

}
