package gui;

import services.IMoteurJeu;
import services.ITerrain;



public class ThreadListener extends Thread {



	private GuiMainWindow window; 

	public ThreadListener(EventListener l, IMoteurJeu moteur) {

		this.window = new GuiMainWindow(moteur, l);
		window.updateTerrain();
	}
	
	@Override
	public void run()
	{

	
		
		window.setVisible(true);
	}
	
	public void updateTerrain(){
		
		window.updateTerrain();
	}
}

