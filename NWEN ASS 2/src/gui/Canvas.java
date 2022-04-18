package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Model;

@SuppressWarnings("serial")
public class Canvas extends JPanel{
  Model m;
  Canvas(Model m){ this.m=m; }
  
  @Override public void paint(Graphics gg){
	  super.paint(gg);
    var g=(Graphics2D)gg;
    g.setBackground(Color.DARK_GRAY);
    g.clearRect(0, 0, getWidth(),getHeight());
    for(var p: m.pDraw){ p.draw(g); }
    Toolkit.getDefaultToolkit().sync();
  }
  @Override public Dimension getPreferredSize(){
    return new Dimension((int)Model.size, (int)Model.size);
  }
}
