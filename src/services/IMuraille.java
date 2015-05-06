package services;

public interface IMuraille extends IEntite{
	/* ########### Observators ########### */
	public int getPointsDeVie();
	public boolean estDetruite();
	
	/* ########### Constructors ########### */		
	/**
		pre: largeur % 2 == 1 && hauteur % 2 == 1 && pointsVie > 0
		post: posx() == x
		post: posy() == y
		post: largeur() == largeur
		post: hauteur() == hauteur
		post: pointsDeVie() == pointsVie
	 */
	public IMuraille init(int x, int y, int largeur, int hauteur, int pointsVie);
	
	/* ########### Operators ########### */
	/**
		pre: s > 0 && ! estDetruite()
		post: pointsDeVie() == pointsDeVie@pre - s
	 */
	public void retrait(int s);
	
	/* ########### Invariants ########### */
	// inv: estDetruite() min= pointsDeVie() <= 0 
}
