package decorators;

import services.IRoute;

public class RouteDecorator implements IRoute {
	protected IRoute delegate;
	
	public RouteDecorator(IRoute delegate) {

		this.delegate = delegate;
		
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
	public int getLargeur() {
		
		return this.delegate.getLargeur();
	}

	@Override
	public int getHauteur() {
		return this.delegate.getHauteur();
	}

	@Override
	public IRoute init(int x, int y, int largeur, int hauteur, int mult) {
		return this.delegate.init(x, y, largeur, hauteur, mult);
	}

	@Override
	public int getMult() {
		return this.delegate.getMult();
	}

}
