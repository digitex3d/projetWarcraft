package decorators;

import java.util.ArrayList;

import services.IGestionDeplacement;
import services.ITerrain;
import services.IVillageois;

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
	public IVillageois getVill() {
		return this.delegate.getVill();
	
	}

	@Override
	public int getAngle() {
		return this.delegate.getAngle();
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
	public IGestionDeplacement init(int angle) {
		return this.delegate.init(angle);
	}

	@Override
	public void calcChemin() {
		this.delegate.calcChemin();


	}

	@Override
	public void bind(IVillageois vill) {
		this.delegate.bind(vill);
	}

	@Override
	public void bindTerrain(ITerrain terr) {
		this.delegate.bindTerrain(terr);
	}

}
