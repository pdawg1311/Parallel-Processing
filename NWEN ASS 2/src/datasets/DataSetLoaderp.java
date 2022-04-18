package datasets;

import model.Model;
import model.ModelParallel;
import model.Particle;

import java.util.Random;

public class DataSetLoaderp {
  public static ModelParallel getRegularGrid(int min, int max, int distance){
    ModelParallel result=new ModelParallel();
    for(int i=min;i<max;i+=distance){
      for(int j=min;j<max;j+=distance){
        result.p.add(new Particle(0.5,0,0,i,j));
        }
      }
    return result;
    }

    
  public static ModelParallel getRandomGrid(int min, int max,int distance){
    ModelParallel result=new ModelParallel();
    Random r=new Random(1);
    for(int i=min;i<max;i+=distance){
      for(int j=min;j<max;j+=distance){
        result.p.add(new Particle(0.5,0,0,i+0.5-r.nextDouble(),j+0.5-r.nextDouble()));
        }
      }
    return result;
    }
  public static ModelParallel getRandomSet(int min, int max,int size){
    ModelParallel result=new ModelParallel();
    Random r=new Random(1);
    for(int i=0;i<size;i++){
      result.p.add(new Particle(0.5,0,0,min+r.nextInt(max-min)+0.5-r.nextDouble(),min+r.nextInt(max-min)+0.5-r.nextDouble()));
      }
    return result;
    }
  public static ModelParallel getRandomRotatingGrid(double speed,int min, int max,int distance){
    ModelParallel result=new ModelParallel();
    Random r=new Random(1);
    for(int i=min;i<max;i+=distance){
      for(int j=min;j<max;j+=distance){
        result.p.add(new Particle(0.5,Math.signum(j-Model.size/2)*r.nextDouble()*speed,Math.signum(Model.size/2-i)*r.nextDouble()*speed,i+r.nextDouble(),j+r.nextDouble()));
        }
      }
    return result;
    }
  public static ModelParallel getElaborate(int min, int max,int distance,double deathRate){
    ModelParallel result=getRandomRotatingGrid(0.05d,min,max,distance);
    Random r=new Random(1);
    for(int i=0;i<result.p.size();i+=1){
      if(r.nextDouble()>deathRate){result.p.get(i).mass+=r.nextDouble()/2d;continue;}
      result.p.remove(i);i-=1;
      }
    result.p.add(new Particle(500,0,0,Model.size/2,Model.size/2));
    return result;
    }
  }
