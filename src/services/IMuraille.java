package services;

public interface IMuraille {
	/* ########### Observators ########### */	
	public int getLargeur();
	public int getHauteur();
	public int getPosX();
	public int getPosY();
	public int getPointsDeVie();
	public boolean estDetruite();
	
	/* ########### Constructors ########### */		
	// \pre: init(x, y, l, h, pv) require l%2 = 1 ∧ h%2 = 1 ∧ pv > 0
	// \post: posX(init(x, y, l, h, pv)) = x
	// \post: posY(init(x, y, l, h, pv)) = y
	// \post: largeur(init(x, y, l, h, pv)) = l
	// \post: hauteur(init(x, y, l, h, pv)) = h
	// \post: pointsDeVie(init(x, y, l, h, pv)) = pv
	public IMuraille init(int x, int y, int largeur, int hauteur, int pointsVie);
	
	/* ########### Operators ########### */
	// \pre: retrait(M, s) require s > 0 ∧ ¬estDetruite(M)
	// \post: pointsDeVie(retrait(M, s)) = pointsDeVie(M)@pre - s
	public void retrait(int s);
	
	/* ########### Invariants ########### */
	// \inv: estDetruite(M) min= pointsDeVie(M) <= 0 
}
