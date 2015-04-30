package services;

public interface IRoute extends IEntite {
	/* ########### Observators ########### */
	public int getMult();

	
	/* ########### Constructors ########### */		
	// \pre: 			largeur % 2=1 ∧ 
	// \pre: 			hauteur % 2=1 ∧
	// \pre: 			x > 0 ∧
	// \pre: 			y > 0 ∧
	// \pre: 			mult > 0

	public IRoute init(int x, int y, int largeur, int hauteur, int mult);
		
}
