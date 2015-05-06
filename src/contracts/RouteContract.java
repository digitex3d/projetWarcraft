package contracts;

import services.IRoute;
import decorators.RouteDecorator;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class RouteContract extends RouteDecorator {

	public RouteContract(IRoute delegate) {
		super(delegate);
		
	}
	
	// Pas d'invariants
	public void checkInvariants() {	}
	
	@Override
	public IRoute init(int x, int y, int largeur, int hauteur, int bv) {
		// pre: largeur % 2 = 1 && hauteur % 2 = 1 && x >= 0 && y >= 0 && bv > 0
		if (x < 0)
			throw new PreconditionError("x >= 0");
		if (y < 0)
			throw new PreconditionError("y >= 0");
		if (largeur % 2 != 1) 
			throw new PreconditionError("largeur % 2 == 1");
		if (hauteur % 2 != 1)           
			throw new PreconditionError("hauteur % 2 == 1");
		if (bv < 0)                   
			throw new PreconditionError("bv > 0");
			
		 super.init(x, y, largeur, hauteur, bv);
		 
		 this.checkInvariants();
		 
		// post: posx() = x
		if (super.getX() != x)
			throw new PostconditionError("posx() = x");
		// post: posy() = y
		if (super.getY() != y)
			throw new PostconditionError("posx() = x");
		// post: largeur() == largeur
		if (super.getLargeur() != largeur)
			throw new PostconditionError("largeur() == largeur");
		// post: hauteur() == hauteur
		if (super.getHauteur() != hauteur)
		  	throw new PostconditionError("hauteur() == hauteur");
		// post: bonusVitesse() == bv
		if (super.getBonusVitesse() != bv)
		  	throw new PostconditionError("bonusVitesse() == bv");
		 
		 return this;
	}


}
