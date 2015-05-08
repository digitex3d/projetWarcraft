package decorators;

import java.util.ArrayList;

import services.IGestionDeplacement;
import services.ITerrain;

public class GestionDeplacementDecorator implements IGestionDeplacement {
	private IGestionDeplacement delegate;

	public GestionDeplacementDecorator(IGestionDeplacement delegate) {
		super();
		this.delegate = delegate;
	}
	
	@Override
	public ITerrain getTerr() {
		return this.delegate.getTerr();
		
	}

	@Override
	public boolean estCalcChemin() {
		return this.delegate.estCalcChemin();
		
	}

	@Override
	public ArrayList<Integer> getCheminX() {
		return this.delegate.getCheminX();
	}

	@Override
	public ArrayList<Integer> getCheminY() {
		return this.delegate.getCheminY();
	}

	@Override
	public ArrayList<Integer> getPointArrivee() {
		return this.delegate.getPointArrivee();

	}

	@Override
	public int getFirstObstacle() {
		return this.delegate.getFirstObstacle();

	}

	@Override
	public IGestionDeplacement init() {
		return this.delegate.init();
	}

	@Override
	public void calcChemin(int numVill, int angle) {
		this.delegate.calcChemin(numVill, angle);


	}


	@Override
	public void bindTerrain(ITerrain terr) {
		this.delegate.bindTerrain(terr);
	}

}
