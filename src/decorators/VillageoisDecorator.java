package decorators;

import enums.ERace;
import services.IVillageois;

//TODO: completare e verificare
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
	public IVillageois init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
		return this.delegate.init(race, largeur, hauteur, force, vitesse, pointsDeVie);
	}

}
