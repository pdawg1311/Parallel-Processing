package tests;

import datasets.DataSetLoader;
import datasets.DataSetLoaderp;
import model.Model;
import model.ModelParallel;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testPerformance {

    int maxIterations=500;
    int repeat=100;


    @Test
    public void elaborateBig(){
        Model m= DataSetLoader.getElaborate(200, 700, 2,0);
        ModelParallel mp = DataSetLoaderp.getElaborate(200, 700, 2,0);
        testModel(m,mp);

    }
    @Test
    public void elaborateSmall(){
        Model m= DataSetLoader.getElaborate(200, 700, 2,0.99);
        ModelParallel mp = DataSetLoaderp.getElaborate(200, 700, 2,0.99);
        testModel(m,mp);
    }
    @Test
    public void testRandomSmall(){
        Model  m=DataSetLoader.getRandomSet(100,800,100);
        ModelParallel  mp=DataSetLoaderp.getRandomSet(100,800,100);
        testModel(m,mp);
    }

    @Test
    public void testRotatingGridSmall(){
        Model m=DataSetLoader.getRandomRotatingGrid(0.02d,100, 800, 40);
        ModelParallel mp=DataSetLoaderp.getRandomRotatingGrid(0.02d,100, 800, 40);
        testModel(m,mp);
    }
    @Test
    public void testRotatingGridBig(){
        Model m=DataSetLoader.getRandomRotatingGrid(0.02d,50, 800, 10);
        ModelParallel mp=DataSetLoaderp.getRandomRotatingGrid(0.02d,50, 800, 10);
        testModel(m,mp);
    }



     @Test
    public void testRandomBig(){
      Model  m=DataSetLoader.getRandomSet(100,800,1000);
      ModelParallel  mp=DataSetLoaderp.getRandomSet(100,800,1000);
        testModel(m,mp);

    }



        public void testModel(Model m,ModelParallel mp) {

        double timeSeq=0;
        double timePara=0;


            for(int j=0;j<repeat;j++) {
                long time0 = System.currentTimeMillis();
                for (int i = 0; i < maxIterations; i++) {
                    m.step();
                }
                long time1 = System.currentTimeMillis();
                timeSeq += time1 - time0;

                time0 = System.currentTimeMillis();
                for (int i = 0; i < maxIterations; i++) {
                    mp.step();
                }
                time1 = System.currentTimeMillis();
                timePara += time1 - time0;

            }


            System.out.println("time seq "+timeSeq);
            System.out.println("time para "+timePara);
           assertTrue(timePara<timeSeq);

        }



}
