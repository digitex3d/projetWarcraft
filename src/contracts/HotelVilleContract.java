package contracts;

import decorators.HotelVilleDecorator;
import enums.ERace;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.IHotelVille;

public class HotelVilleContract extends HotelVilleDecorator {

	public HotelVilleContract(IHotelVille delegate) {
		super(delegate);
	}

	// Pas d'invariants
	public void checkInvariants() {	}
	
	public IHotelVille init(int x, int y, int largeur, int hauteur, int or, ERace app){
		// pre: largeur % 2 == 1 && hauteur % 2 == 1 && x >= 0 && y >= 0 && or >= 0
		if (largeur % 2 != 1) 
			throw new PreconditionError("largeur % 2 == 1");
		if (hauteur % 2 != 1) 
			throw new PreconditionError("hauteur % 2 == 1");
		if (x < 0) 
			throw new PreconditionError("x >= 0");
		if (y < 0) 
			throw new PreconditionError("y >= 0");
		if (or < 0) 
			throw new PreconditionError("or >= 0");

		super.init(x, y, largeur, hauteur, or, app);

		this.checkInvariants();
		
		// post: largeur() == largeur
		if (super.getLargeur() != largeur)
			throw new PostconditionError("largeur() == largeur");
		// post: hauteur() == hauteur
		if (super.getHauteur() != hauteur)
			throw new PostconditionError("hauteur() == hauteur");
		// post: orRestant() == or
		if (super.getOrRestant() != or)
			throw new PostconditionError("orRestant() == or");
		// post: posx() == x
		if (super.getX() != x)
			throw new PostconditionError("posx() == x");
		// post: posy() == y
		if (super.getY() != y)
			throw new PostconditionError("posy() == y");
		// post: etatAppartenance() == app
		if (super.getEtatAppartenance() != app)
			throw new PostconditionError("etatAppartenance() == app");
		
		return this;
	}
	
	public void depot(int s){
		this.checkInvariants();

		// pre: s > 0
		if (s <= 0)
			throw new PreconditionError("s > 0");

		int OrRestant_pre = super.getOrRestant();
		
		super.depot(s);
		
		this.checkInvariants();
		
		// post: orRestant() == orRestant()@pre + s
		if (super.getOrRestant() != OrRestant_pre + s)                                     
			throw new PostconditionError("orRestant() == orRestant()@pre + s");  
	}
}
