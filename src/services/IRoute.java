package services;

public interface IRoute extends IEntite {
	/* ########### Observators ########### */
	public int getBonusVitesse();

	/* ########### Constructors ########### */		
	/**
		pre: largeur % 2 == 1 && hauteur % 2 == 1 && x >= 0 && y >= 0 && bv > 0
		post: posx() == x
		post: posy() == y
		post: largeur() == largeur
		post: hauteur() == hauteur
		post: bonusVitesse() == bv
	 */

	public IRoute init(int x, int y, int largeur, int hauteur, int bv);
		
}
