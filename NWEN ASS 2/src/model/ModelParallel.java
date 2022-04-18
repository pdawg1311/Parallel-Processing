package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ModelParallel extends Model{



   public volatile double mass=0;
 public volatile double speedX=0;
     public volatile double speedY=0;
    public volatile double x=0;
    public volatile double y=0;





    public void step() {

        this.p.parallelStream().forEach(p-> p.interact(this));

        this.p.parallelStream().forEach(p ->p.move() );

        mergeParticles();
        updateGraphicalRepresentation();
    }

    void updateGraphicalRepresentation() {
        var d=new ArrayList<DrawableParticle>();
        for(var p:this.p){ d.add(DrawableParticle.of(p)); }
        this.pDraw=d;//atomic update
    }
    public void mergeParticles(){
        var deadPs=new Stack<Particle>();
        for(var p:this.p){ if(!p.impacting.isEmpty()){ deadPs.add(p); } }
        this.p.removeAll(deadPs);
        while(!deadPs.isEmpty()){
            Particle current=deadPs.pop();
            Set<Particle> ps=getSingleChunck(current);
            deadPs.removeAll(ps);
            this.p.add(mergeParticles(ps));
        }
    }
    private Set<Particle> getSingleChunck(Particle current) {
        Set<Particle> impacting=new HashSet<>();
        impacting.add(current);
        while(true){
            Set<Particle> tmp=new HashSet<>();
            for(Particle pi:impacting){ tmp.addAll(pi.impacting); }
            boolean changed=impacting.addAll(tmp);
            if(!changed){ break; }
        }
        //now impacting have all the chunk of collapsing particles
        return impacting;
    }
    public Particle mergeParticles(Set<Particle> ps){
        double speedX=0;
        double speedY=0;
        double x=0;
        double y=0;
        double mass=0;

        for(Particle p:ps){
            mass+=p.mass;
            x+=p.x*p.mass;
            y+=p.y*p.mass;
            speedX+=p.speedX*p.mass;
            speedY+=p.speedY*p.mass;
        }
        x/=mass;
        y/=mass;
        speedX/=mass;
        speedY/=mass;
        return new Particle(mass,speedX,speedY,x,y);
    }


}