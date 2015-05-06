package implementations;

import services.IRoute;

public class RouteImpl implements IRoute{
	private int posX;
	private int posY;
	private int largeur;
	private int hauteur;
	private int bv;
	
	public IRoute init(int x, int y, int largeur, int hauteur, int bv) {
		this.posX = x;
		this.posY = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.bv = bv;
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
	public int getLargeur() {
		return this.largeur;
	}

	@Override
	public int getHauteur() {
		return this.hauteur;
	}


	@Override
	public int getBonusVitesse() {
		return this.bv;
	}

}
