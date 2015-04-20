package implementations;

import enums.ERace;
import services.IVillageois;

// TODO: completare e verificare
public class VillageoisImpl implements IVillageois {
	private ERace race;
	private int largeur;
	private int hauteur;
	private int force;
	private double vitesse;
	private int pointsDeVie;
	private int quantiteOr;
    private boolean estMort;
    
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
	public void retrait(int s) {
		this.pointsDeVie -= s;

	}

	@Override
	public boolean estMort() {
		return this.estMort;
		
	}
	
	@Override
	public void chargeOr(int s) {
		this.quantiteOr += s;
		
	}

	@Override
	public void dechargeOr(int s) {
		this.quantiteOr -= s;
		
	}

}
