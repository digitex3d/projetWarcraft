package implementations;

import enums.ERace;
import services.IHotelVille;


public class HotelVilleImpl implements IHotelVille {
	private int largeur;
	private int hauteur;
	private int orRestant;
	private int posX;
	private int posY;
	private ERace appartenance;
	
	@Override
	public IHotelVille init(int x, int y, int largeur, int hauteur, int or, ERace app) {
		this.posX = x;
		this.posY = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = or;
		this.appartenance = app;
		
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
	public void depot(int s) {
		this.orRestant += s;
	}

	@Override
	public ERace getEtatAppartenance() {
		return this.appartenance;
	}
}
