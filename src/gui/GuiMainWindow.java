package gui;


import javax.swing.JFrame;

import services.ITerrain;

@SuppressWarnings("serial")
public class GuiMainWindow extends JFrame {
	Terrain terrain;
	

	public GuiMainWindow(ITerrain terrainJeu, EventListener listener) {
		this.terrain = new Terrain(listener);
	
		initUI(terrainJeu.getLargeur(),terrainJeu.getHauteur());
	}

	private void initUI(int largeur, int hauteur) {

		add(this.terrain);

		setSize(largeur, hauteur);

		setTitle("Warcraft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}    

	public void updateTerrain(ITerrain terrainJeu){
		this.terrain.updateTerrain( terrainJeu);
	}
	
	
	
	
	
	
}

