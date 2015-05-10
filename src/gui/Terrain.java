package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;







import java.util.HashSet;

import javax.swing.JPanel;

import enums.EEntite;
import services.ITerrain;





@SuppressWarnings("serial")
public class Terrain extends JPanel implements MouseListener {
	EventListener listener;
	ITerrain terrainJeu;

    public  Terrain(EventListener listener) {
    	if(this.terrainJeu == null){
    		System.out.println("Il faut binder le terrain de jeu!");
    	}
    	this.listener = listener;
    	addMouseListener(this);
        setFocusable(true);
        setBackground(Color.GREEN);
        setDoubleBuffered(true);
        
 
  
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
   
	    }
	 
	
	 
	 public void updateTerrain(ITerrain terrainJeu){
			this.terrainJeu = terrainJeu;
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
