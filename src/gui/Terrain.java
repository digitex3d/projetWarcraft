package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;




import javax.swing.JPanel;

import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IVillageois;





@SuppressWarnings("serial")
public class Terrain extends JPanel implements MouseListener {

	IMoteurJeu moteurJeu;

    public  Terrain() {

    	addMouseListener(this);
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
	        		hv.getY(), hv.getLargeur(), hv.getHauteur());
	        g2d.fill(circle); 
	      
	        
	        
	        for( IVillageois v : moteurJeu.getListVillageois()){
	        	g.setColor(Color.BLACK);
	        	g.drawOval(v.getX(), v.getY(), v.getLargeur(), v.getHauteur());    

	        }
	        
	        for( IMine m : moteurJeu.getListMines()){
	        	g.setColor(Color.gray);
	        	g.drawOval(m.getX(), m.getY(), m.getLargeur(), m.getHauteur());    

	        }
	        
	        
	    }
	 
	
	 
	 public void updateMoteur(IMoteurJeu moteurJeu){
			this.moteurJeu = moteurJeu;
			this.repaint();

	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click");
		this.moteurJeu.eventListener(e, EEvent.CLICK);
		
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
