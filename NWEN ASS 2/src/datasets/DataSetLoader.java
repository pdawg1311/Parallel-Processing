package datasets;

import java.util.Random;

import model.Model;
import model.Particle;

public class DataSetLoader {
  public static Model getRegularGrid(int min, int max,int distance){
    Model result=new Model();
    for(int i=min;i<max;i+=distance){
      for(int j=min;j<max;j+=distance){
        result.p.add(new Particle(0.5,0,0,i,j));
        }
      }
    return result;
    }
  public static Model getRandomGrid(int min, int max,int distance){
    Model result=new Model();
    Random r=new Random(1);
    for(int i=min;i<max;i+=distance){
      for(int j=min;j<max;j+=distance){
        result.p.add(new Particle(0.5,0,0,i+0.5-r.nextDouble(),j+0.5-r.nextDouble()));
        }
      }
    return result;
    }
  public static Model getRandomSet(int min, int max,int size){
    Model result=new Model();
    Random r=new Random(1);
    for(int i=0;i<size;i++){
      result.p.add(new Particle(0.5,0,0,min+r.nextInt(max-min)+0.5-r.nextDouble(),min+r.nextInt(max-min)+0.5-r.nextDouble()));
      }
    return result;
    }
  public static Model getRandomRotatingGrid(double speed,int min, int max,int distance){
    Model result=new Model();
    Random r=new Random(1);
    for(int i=min;i<max;i+=distance){
      for(int j=min;j<max;j+=distance){
        result.p.add(new Particle(0.5,Math.signum(j-Model.size/2)*r.nextDouble()*speed,Math.signum(Model.size/2-i)*r.nextDouble()*speed,i+r.nextDouble(),j+r.nextDouble()));
        }
      }
    return result;
    }
  public static Model getElaborate(int min, int max,int distance,double deathRate){
    Model result=getRandomRotatingGrid(0.05d,min,max,distance);
    Random r=new Random(1);
    for(int i=0;i<result.p.size();i+=1){
      if(r.nextDouble()>deathRate){result.p.get(i).mass+=r.nextDouble()/2d;continue;}
      result.p.remove(i);i-=1;
      }
    result.p.add(new Particle(500,0,0,Model.size/2,Model.size/2));
    return result;
    }
  }
