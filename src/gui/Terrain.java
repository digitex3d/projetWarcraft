package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;







import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;

import enums.EEntite;
import services.IMoteurJeu;
import services.ITerrain;





@SuppressWarnings("serial")
public class Terrain extends JPanel implements MouseListener {
	EventListener listener;
	ITerrain terrainJeu;
	IMoteurJeu moteur;

    public Terrain(EventListener listener, IMoteurJeu moteur) {
    	this.terrainJeu = moteur.getTerrain();
    	this.listener = listener;
    	addMouseListener(this);
        setFocusable(true);
        setBackground(Color.GREEN);
        setDoubleBuffered(true);
        this.moteur = moteur;
     
 
  
    }
    
	 public void paint(Graphics g) {
	        super.paint(g);
	        
	        for (int x = 0; x < terrainJeu.getLargeur(); x++) {
	        	   for (int y = 0; y < terrainJeu.getHauteur(); y++) {
	        		   HashSet<EEntite> entitees=  (HashSet<EEntite>) this.terrainJeu.getEntiteAt(x, y);
	        		   if(entitees.contains(EEntite.VILLAGEOIS))
	        			   g.setColor(Color.PINK);
	        		   else if(entitees.contains(EEntite.HDV)) 
	        			   g.setColor(Color.RED);
	        		   else if(entitees.contains(EEntite.MINE)) 
	        			   g.setColor(Color.GRAY);
	        		   else if(entitees.contains(EEntite.MURAILLE)) 
	        			   g.setColor(Color.yellow);
	        		   else if(entitees.contains(EEntite.ROUTE)) 
	        			   g.setColor(Color.orange);
	        		   else
	        			   g.setColor(Color.GREEN);
	        		
	        		   g.drawOval(x, y, 1, 1);    
	        			   
	   			}
			}
	      g.setColor(Color.BLACK);
	      g.setFont(new Font(null, Font.PLAIN, 18));
	      g.drawString("Pas:" + this.moteur.getPasJeuCourant(), 50, 440);
	      g.drawString("Or orc:" + this.moteur.getHotelVille(0).getOrRestant() , 50, 460);
	      g.drawString("Or humain:"  + this.moteur.getHotelVille(1).getOrRestant()  , 40, 480);
	     
   
	    }
	 
	
	 
	 public void updateTerrain(){
			this.repaint();

	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click");
		this.listener.gererClick(e.getX(), e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 

}
