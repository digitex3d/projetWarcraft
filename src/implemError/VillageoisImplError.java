package implemError;

import enums.ERace;
import services.IVillageois;

// TODO: completare e verificare
public class VillageoisImplError implements IVillageois {
	private ERace race;
	private int largeur;
	private int hauteur;
	private int force;
	private double vitesse;
	private int pointsDeVie;
	private int quantiteOr;
    private boolean estMort;
	int posX;
	int posY;
    
    @Override
	public IVillageois init(int x, int y, ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
		this.race         =   race; 
		this.largeur      =   largeur; 
		this.hauteur      =   hauteur; 
		this.force        =   force; 
		this.vitesse      =   vitesse; 
		this.pointsDeVie  =   pointsDeVie; 
		return this;
		
	}
    
    public VillageoisImplError() {}
    
	public int getPointsDeVie() {
		return pointsDeVie;
	}


	public int getQuantiteOr() {
		return quantiteOr;
	}

	public boolean isEstMort() {
		return estMort;
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
	// Bug
	public void retrait(int s) {
		this.pointsDeVie -= s+1;

	}

	@Override
	public boolean estMort() {
		return !this.estMort;
		
	}

	@Override
	public void chargeOr(int s) {
		this.quantiteOr += s;
		
	}

	@Override
	// Bug
	public void dechargeOr(int s) {
		this.quantiteOr += s;
		
	}
	
	@Override
	public int getY() {
	
		return this.getY();
	}

	
	@Override
	public void setX(int x) {
		this.posX= x;
		
	}

	@Override
	public void setY(int y) {
		this.posX= y;
		
	}

	@Override
	public int getX() {
	
		return 0;
	}

}
