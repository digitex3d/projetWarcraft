package implementations;

import services.IRoute;

public class RouteImpl implements IRoute{
	protected int posX;
	protected int posY;
	protected int largeur;
	protected int hauteur;
	protected int mult;
	
	public RouteImpl() {}
	
	public IRoute init(int x, int y, int largeur, int hauteur, int mult) {
		this.posX = x;
		this.posY = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.mult = mult;
		return this;
	}

	@Override
	public int getX() {
		return this.posX;
	}

	@Override
	public int getY() {
		return this.posY;
	}

	@Override
	public void setX(int x) {
		this.posX = x;
	}

	@Override
	public void setY(int y) {
		this.posY = y;
		
	}

	@Override
	public int getLargeur() {
		return this.largeur;
	}

	@Override
	public int getHauteur() {
		return this.hauteur;
	}


	@Override
	public int getMult() {
		return this.mult;
	}

}
