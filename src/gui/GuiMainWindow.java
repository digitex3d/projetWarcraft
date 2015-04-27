package gui;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import services.IMoteurJeu;
import services.IVillageois;

@SuppressWarnings("serial")
public class GuiMainWindow extends JFrame {
	Terrain terrain;
	

	public GuiMainWindow(int largeur, int hauteur) {
		this.terrain = new Terrain();
		
	
		initUI(largeur, hauteur);
	}

	private void initUI(int largeur, int hauteur) {

		add(this.terrain);

		setSize(largeur, hauteur);

		setTitle("Warcraft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}    

	public void updateMoteur(IMoteurJeu moteur){
		this.terrain.updateMoteur( moteur);
	}
	
	
	
	
	
	
}

