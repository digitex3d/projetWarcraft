package gui;

import services.ITerrain;



public class ThreadListener extends Thread {



	private GuiMainWindow window; 

	public ThreadListener(EventListener l, ITerrain terr) {

		this.window = new GuiMainWindow(terr, l);
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

