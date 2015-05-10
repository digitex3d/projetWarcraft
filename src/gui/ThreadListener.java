package gui;

import services.ITerrain;



public class ThreadListener extends Thread {


	private EventListener lis;
	private ITerrain terrain;
	private GuiMainWindow window; 

	public ThreadListener(EventListener l, ITerrain terr) {
		this.lis = l;
		this.terrain = terr;
		this.window = new GuiMainWindow(terrain, lis);
	}
	
	@Override
	public void run()
	{

	
		window.updateTerrain(terrain);
		window.setVisible(true);
	}
	
	public void updateTerrain( ITerrain terr ){
		
		window.updateTerrain(terr);
	}
}

