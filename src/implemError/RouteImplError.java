package implemError;

import services.IRoute;

public class RouteImplError implements IRoute{
	protected int posX;
	protected int posY;
	protected int largeur;
	protected int hauteur;
	protected int bv;
	
	public RouteImplError() {}
	
	public IRoute init(int x, int y, int largeur, int hauteur, int bv) {
		this.posX = x;
		this.posY = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.bv = bv + 2; //BUG
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
	public int getLargeur() {
		return this.largeur;
	}

	@Override
	public int getHauteur() {
		return this.hauteur - 1; //BUG
	}


	@Override
	public int getBonusVitesse() {
		return this.bv;
	}

}
