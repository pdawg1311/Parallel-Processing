package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Particle {
  public Particle(double mass, double speedX, double speedY, double x, double y){
    this.mass = mass;
    this.speedX = speedX;
    this.speedY = speedY;
    this.x = x;
    this.y = y;
    }
  public Set<Particle> impacting=new HashSet<Particle>();
  public double mass;
  public double speedX;
  public double speedY;
  public double x;
  public double y;
  public void move(){
    x+=speedX/(Model.timeFrame);
    y+=speedY/(Model.timeFrame);
    //uncomment the following to have particle bouncing on the boundary
    //if(this.x<0){this.speedX*=-1;}
    //if(this.y<0){this.speedY*=-1;}
    //if(this.x>Model.size){this.speedX*=-1;}
    //if(this.y>Model.size){this.speedY*=-1;}
    }



    public boolean equals(Particle p){

    if (this.mass==p.mass && this.speedX == p.speedX && this.speedY == p.speedY&& this.x==p.x&&this.y==p.y){
      return true;
    } else{return false;}
    }



  public Particle clone() {
    return new Particle(this.mass, this.speedX, this.speedY, this.x, this.y);
  }
  public  boolean isImpact(double dist,double otherMass){
    if(Double.isNaN(dist)){ return true; }
    double distMass=Math.sqrt(mass)+Math.sqrt(otherMass);
    return dist<distMass*distMass;
    }
  public  boolean isImpact(Iterable<Particle> ps){
    for(Particle p:ps){
      if(this==p){ continue; }
      double dist=distance2(p);
      if(isImpact(dist,p.mass)){ return true; }
      }
    return false;
    }
  public double distance2(Particle p){
    double distX=this.x-p.x;
    double distY=this.y-p.y;
    return distX*distX+distY*distY;
    }
  public void interact(Model m){
    for(Particle p:m.p){
      if(p==this) {continue;}
      double dirX=-Math.signum(this.x-p.x);
      double dirY=-Math.signum(this.y-p.y);
      double dist=distance2(p);//this is already distance^2
      if(isImpact(dist,p.mass)){ this.impacting.add(p);continue; }
      dirX=p.mass*Model.gravitationalConstant*dirX/(dist*Model.timeFrame);
      dirY=p.mass*Model.gravitationalConstant*dirY/(dist*Model.timeFrame);
      assert this.speedX<=Model.lightSpeed:this.speedX;
      assert this.speedY<=Model.lightSpeed:this.speedY;
      double newSpeedX=this.speedX+dirX;
      newSpeedX/=(1+(this.speedX*dirX)/Model.lightSpeed);
      double newSpeedY=this.speedY+dirY;
      newSpeedY/=(1+(this.speedY*dirY)/Model.lightSpeed);
      if(!Double.isNaN(dirX)){ this.speedX=newSpeedX; }
      if(!Double.isNaN(dirY)){ this.speedY=newSpeedY; }
      }
    }







  }