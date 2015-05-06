package implementations;

import services.IMuraille;

public class MurailleImpl implements IMuraille {
	private int largeur;
	private int hauteur;
	private int x;
	private int y;
	private int pointsDeVie;
	
	@Override
	public int getLargeur() {
		return largeur;
	}

	@Override
	public int getHauteur() {
		return hauteur;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getPointsDeVie() {
		return pointsDeVie;
	}

	@Override
	public boolean estDetruite() {
		return pointsDeVie <= 0;
	}

	@Override
	public IMuraille init(int x, int y, int largeur, int hauteur, int pointsVie) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.pointsDeVie = pointsVie;
		return this;
	}

	@Override
	public void retrait(int s) {
		this.pointsDeVie -= s;
	}
}
