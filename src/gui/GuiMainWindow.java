package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;

import services.IMoteurJeu;
import services.ITerrain;

@SuppressWarnings("serial")
public class GuiMainWindow extends JFrame {
	Terrain terrain;
	

	public GuiMainWindow(IMoteurJeu moteur, EventListener listener) {
		this.terrain = new Terrain(listener, moteur);
	
	        
		initUI(moteur.getTerrain().getLargeur(),moteur.getTerrain().getHauteur());
	
	}

	private void initUI(int largeur, int hauteur) {

		add(this.terrain);

		setSize(largeur, hauteur);

		setTitle("Warcraft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setLocationRelativeTo(null);
	}    

	public void updateTerrain(){
		this.terrain.updateTerrain();
	}
	
	
	
	
	
	
}

