package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;



import javax.swing.JPanel;

import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IVillageois;





@SuppressWarnings("serial")
public class Terrain extends JPanel {

	IMoteurJeu moteurJeu;

    public  Terrain() {

     
        setFocusable(true);
        setBackground(Color.GREEN);
        setDoubleBuffered(true);

  
    }
    
	 public void paint(Graphics g) {
	        super.paint(g);
	        
	        Graphics2D g2d = (Graphics2D)g;
	        
	        // Paint Hotel de Ville
	        g.setColor(Color.RED);
	        IHotelVille hv = moteurJeu.getHotelDeVille();
	        Ellipse2D.Double circle = new Ellipse2D.Double(hv.getX(),
	        		hv.getY(), 20, 20);
	        g2d.fill(circle); 
	        
	        
	        for( IVillageois villageois : moteurJeu.getListVillageois()){
	        	g.setColor(Color.BLACK);
	        	g.drawOval(villageois.getX(), villageois.getY(), 4, 4);    

	        }
	        
	        for( IMine mine : moteurJeu.getListMines()){
	        	g.setColor(Color.gray);
	        	g.drawOval(mine.getX(), mine.getY(), 7, 7);    

	        }
	        
	        
	    }
	 
	 public void updateMoteur(IMoteurJeu moteurJeu){
			this.moteurJeu = moteurJeu;

	}
	 

}
