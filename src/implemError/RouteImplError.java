package implemError;

import services.IRoute;

public class RouteImplError implements IRoute{
	protected int posX;
	protected int posY;
	protected int largeur;
	protected int hauteur;
	protected int mult;
	
	public RouteImplError() {}
	
	public IRoute init(int x, int y, int largeur, int hauteur, int mult) {
		this.posX = x;
		this.posY = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.mult = mult + 2; //BUG
		return this;
	}

	@Override
	public int getX() {
		return 0; //BUG
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
		return this.hauteur - 1; //BUG
	}


	@Override
	public int getMult() {
		return this.mult;
	}

}
