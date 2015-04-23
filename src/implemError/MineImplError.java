package implemError;

import services.IMine;

// TODO: verificare
public class MineImplError implements IMine {
	int largeur;
	int hauteur;
	int orRestant;
	int abandonCompteur;
	boolean estLaminee;
	boolean estAbandonne;
	
	public MineImplError() {}
		
	@Override
	public IMine init(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = 51;
		this.abandonCompteur = 51;
		return this;
		
	}
		
	@Override
	public int getLargeur() {
		return this.getLargeur();
	}

	@Override
	public int getHauteur() {
		return this.getHauteur();
	}

	@Override
	public int getOrRestant() {
		return this.getOrRestant();
	}

	@Override
	public boolean estAbandonne() {
		return this.estAbandonne();
	}

	@Override
	public boolean estLaminee() {
		return this.estLaminee();
	}

	@Override
	public int getAbandonCompteur() {
		return this.getAbandonCompteur();
	}

	

	@Override
	public void retrait(int s) {
		this.orRestant -= s;
	}

	@Override
	public void acceuil() {
		this.abandonCompteur = 1;
	}

	@Override
	public void abandoned() {
		this.abandonCompteur -= 1;
		
	}


}
