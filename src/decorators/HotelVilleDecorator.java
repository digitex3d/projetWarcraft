package decorators;

import services.IHotelVille;

public class HotelVilleDecorator implements IHotelVille{
	private IHotelVille delegate;

	public HotelVilleDecorator(IHotelVille delegate) {
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
	public int getX() {
		return this.delegate.getX();
	}

	@Override
	public int getY() {
		return this.delegate.getY();
	}

	@Override
	public void depot(int s) {
		this.delegate.depot(s);
	}

	@Override
	public IHotelVille init(int x, int y, int largeur, int hauteur, int or) {
		return this.init(x, y, largeur, hauteur, or);
	}

}
