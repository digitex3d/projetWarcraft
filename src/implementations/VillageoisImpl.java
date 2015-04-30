package implementations;

import enums.ERace;
import services.IVillageois;

// TODO: completare e verificare
public class VillageoisImpl implements IVillageois {
	protected ERace race;
	protected int largeur;
	protected int hauteur;
	protected int force;
	protected double vitesse;
	protected int pointsDeVie;
	protected int quantiteOr;
    protected int posX;
    protected int posY;
    
    @Override
	public IVillageois init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
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
	public void setX(int x) {
		this.posX = x;
		
	}

	@Override
	public void setY(int y) {
		this.posY = y;
		
	}

}
