package decorators;

import services.IMuraille;

public class MurailleDecorator implements IMuraille{
	private IMuraille delegate;
	
	public MurailleDecorator(IMuraille delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public int getLargeur() {
		return delegate.getLargeur();
	}

	@Override
	public int getHauteur() {
		return delegate.getHauteur();
	}

	@Override
	public int getX() {
		return delegate.getX();
	}

	@Override
	public int getY() {
		return delegate.getY();
	}

	@Override
	public int getPointsDeVie() {
		return delegate.getPointsDeVie();
	}

	@Override
	public boolean estDetruite() {
		return delegate.estDetruite();
	}

	@Override
	public IMuraille init(int x, int y, int largeur, int hauteur, int pointsVie) {
		return delegate.init(x, y, largeur, hauteur, pointsVie);
	}

	@Override
	public void retrait(int s) {
		delegate.retrait(s);
	}

}
