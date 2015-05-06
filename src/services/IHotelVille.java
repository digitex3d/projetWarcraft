package services;

import enums.ERace;

public interface IHotelVille extends IEntite{
	/* ########### Observators ########### */
	ERace getEtatAppartenance();
	int getOrRestant();
	
	/* ########### Constructors ########### */		
	/**
		pre: largeur % 2 == 1 && hauteur % 2 == 1 && x >= 0 && y >= 0 && or >= 0
		post: posx() == x
		post: posy() == y
		post: largeur() == largeur
		post: hauteur() == hauteur
		post: orRestant() == or
		post: etatAppartenance() == app
	 */
			 
	IHotelVille init(int x, int y, int largeur, int hauteur, int or, ERace app);

	/* ########### Operators ########### */
	/**
		pre: s > 0
		post: orRestant() == orRestant()@pre + s
	 */
	void depot(int s);
	
}
