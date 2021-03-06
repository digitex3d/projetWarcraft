package decorators;

import enums.ERace;
import services.IMine;

public class MineDecorator implements IMine{
	private IMine delegate;

	public MineDecorator(IMine delegate) {
		super();
		this.delegate = delegate;
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
	public int getOrRestant() {
		return this.delegate.getOrRestant();
	}

	@Override
	public boolean estAbandonne() {
		return this.delegate.estAbandonne();
	}

	@Override
	public boolean estLaminee() {
		return this.delegate.estLaminee();
	}

	@Override
	public int getAbandonCompteur() {
		return this.delegate.getAbandonCompteur();
	}

	@Override
	public IMine init(int x, int y, int l, int h) {
		return this.delegate.init(x, y, l, h);
	}

	@Override
	public void retrait(int s) {
		this.delegate.retrait(s);
	}

	@Override
	public void acceuil(ERace race) {
		this.delegate.acceuil(race);
	}

	@Override
	public void abandoned() {
		this.delegate.abandoned();
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
	public ERace getEtatAppartenance() {
		return this.delegate.getEtatAppartenance();
	}
}
