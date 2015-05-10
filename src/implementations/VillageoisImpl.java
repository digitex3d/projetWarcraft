package implementations;

import enums.ERace;
import services.IVillageois;

public class VillageoisImpl implements IVillageois {
	private ERace race;
	private int largeur;
	private int hauteur;
	private int force;
	private double vitesse;
	private int pointsDeVie;
	private int quantiteOr;
	private int posX;
	private int posY;
	private int corvee;
    
    
    @Override
	public IVillageois init(int x, int y, ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
    	this.posX 			  =   x;
    	this.posY 			  =   y;
		this.race         =   race; 
		this.largeur      =   largeur; 
		this.hauteur      =   hauteur; 
		this.force        =   force; 
		this.vitesse      =   vitesse; 
		this.pointsDeVie  =   pointsDeVie; 
		return this;
		
	}
    
    public VillageoisImpl() {}
    
	public int getPointsDeVie() {
		return pointsDeVie;
	}


	public int getQuantiteOr() {
		return quantiteOr;
	}

	public ERace getRace() {
		return race;
	}



	public int getLargeur() {
		return largeur;
	}



	public int getHauteur() {
		return hauteur;
	}



	public int getForce() {
		return force;
	}


	public double getVitesse() {
		return vitesse;
	}

	@Override
	public void retrait(int s) {
		this.pointsDeVie -= s;

	}

	@Override
	public boolean estMort() {
		return this.pointsDeVie <= 0;
		
	}
	
	@Override
	public void chargeOr(int s) {
		this.quantiteOr += s;
		
	}

	@Override
	public void dechargeOr(int s) {
		this.quantiteOr -= s;
		
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
	public int getCorvee() {
		return this.corvee;
	}

	@Override
	public boolean estOccupe() {
		return this.corvee > 0;
	}

	@Override
	public void decrCorvee() {
		this.corvee--;
	}

	@Override
	public void setXY(int x, int y) {
		this.posX = x;
		this.posY = y;

	}
	
	@Override
	public void setCorvee(int s, int corveeX, int corveeY) {
		this.corvee = s;
		this.posX = corveeX;
		this.posY = corveeY;
		
	}
}
