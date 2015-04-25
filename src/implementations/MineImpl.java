package implementations;

import services.IMine;

public class MineImpl implements IMine {
	int largeur;
	int hauteur;
	int orRestant;
	int abandonCompteur;
	boolean estLaminee;
	boolean estAbandonne;
	int posX;
	int posY;
	
	public MineImpl() {}
		
	@Override
	public IMine init(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = 51;
		this.abandonCompteur = 51;
		this.estLaminee = false;
		this.estAbandonne = true;
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
	public boolean estAbandonne() {
		return this.estAbandonne;
	}

	@Override
	public boolean estLaminee() {
		return this.estLaminee;
	}

	@Override
	public int getAbandonCompteur() {
		return this.abandonCompteur;
	}

	

	@Override
	public void retrait(int s) {
		this.orRestant -= s;
	}

	//TODO: à revoir
	@Override
	public void acceuil() {
		this.abandonCompteur = 0;
		
	}

	//TODO: à revoir
	@Override
	public void abandoned() {
			this.abandonCompteur += 1;
		
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


}
