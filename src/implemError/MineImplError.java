package implemError;

import enums.ERace;
import services.IMine;

public class MineImplError implements IMine {
	int largeur;
	int hauteur;
	int orRestant;
	int abandonCompteur;
	ERace etatAppartenance;
	int posX;
	int posY;
	
		
	@Override
	public IMine init(int x, int y, int largeur, int hauteur) {
		this.posX = x;
		this.posY = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = 45; //BUG
		this.abandonCompteur = 50; //BUG
		this.etatAppartenance = ERace.ORC;
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
		return this.abandonCompteur == 51;
	}

	@Override
	public boolean estLaminee() {
		return this.orRestant <= 5; //BUG
	}

	@Override
	public int getAbandonCompteur() {
		return this.abandonCompteur;
	}

	@Override
	public void retrait(int s) {
		this.orRestant -= s - 1; //BUG
	}

	@Override
	public void acceuil(ERace race) {
		this.etatAppartenance = race;
		this.abandonCompteur = 0;
		this.orRestant--;
	}

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
	public ERace getEtatAppartenance() {
		return etatAppartenance;
	}
}
