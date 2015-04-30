package implemError;

import services.IHotelVille;


public class HotelVilleImplError implements IHotelVille {
	protected int largeur;
	protected int hauteur;
	protected int orRestant;
	protected int posX;
	protected int posY;
	
	public HotelVilleImplError() {}
		
	@Override
	public IHotelVille init(int x, int y, int largeur, int hauteur, int or) {
		this.posX = x;
		this.posY = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = or - 1; //BUG
		
		return this;
		
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
	public int getOrRestant() {
		return this.orRestant;
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
	public void depot(int s) {
		this.orRestant += s + 1; //BUG
	}


}
