package decorators;

import enums.ERace;
import services.IVillageois;

public class VillageoisDecorator implements IVillageois {
	private IVillageois delegate;

	public VillageoisDecorator(IVillageois delegate) {
		super();
		this.delegate = delegate;
	}
	
	@Override
	public ERace getRace() {
		return this.delegate.getRace();
	}

	@Override
	public int getLargeur() {
		return this.delegate.getLargeur();
	}

	@Override
	public int getHauteur() {
		return this.delegate.getHauteur();
	}

	@Override
	public int getForce() {
		return this.delegate.getForce();
	}

	@Override
	public double getVitesse() {
		return this.delegate.getVitesse();	}

	@Override
	public int getPointsDeVie() {
		return this.delegate.getPointsDeVie();
	}

	@Override
	public int getQuantiteOr() {
		return this.delegate.getQuantiteOr();
	}

	@Override
	public boolean estMort() {
		return this.delegate.estMort();
	}



	@Override
	public void retrait(int pointsVie) {
		this.delegate.retrait(pointsVie);
	}

	@Override
	public IVillageois init(int x, int y,ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
		return this.delegate.init(x,y,race, largeur, hauteur, force, vitesse, pointsDeVie);
	}

	@Override
	public void chargeOr(int s) {
		this.delegate.chargeOr(s);
		
	}

	@Override
	public void dechargeOr(int s) {
		this.delegate.dechargeOr(s);
		
	}


	@Override
	public int getX() {
		return this.delegate.getX();
	}

	@Override
	public int getY() {
		return this.delegate.getY();
	}

	@Override
	public int getCorvee() {
		return this.delegate.getCorvee();
	}

	@Override
	public boolean estOccupe() {
		return this.delegate.estOccupe();
	}

	@Override
	public void setCorvee(int s, int corveeX, int corveeY) {
		this.delegate.setCorvee(s, corveeX,corveeY);
	}

	@Override
	public void decrCorvee() {
		this.delegate.decrCorvee();
	}

	@Override
	public void setXY(int x, int y) {
		this.delegate.setXY(x, y);
		
	}


}
