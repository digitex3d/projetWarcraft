package contracts;

import services.IMuraille;
import decorators.MurailleDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class MurailleContract extends MurailleDecorator {

	public MurailleContract(IMuraille delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		// inv: estDetruite() min= pointsDeVie() <= 0 
		if (super.estDetruite() && super.getPointsDeVie() > 0)
			throw new InvariantError("estDetruite() min= pointsDeVie() <= 0");
	}
	
	public IMuraille init(int x, int y, int largeur, int hauteur, int pointsVie) {
		// pre: largeur % 2 == 1 && hauteur % 2 == 1 && pointsVie > 0
		if (largeur % 2 != 1)
			throw new PreconditionError("largeur % 2 == 1");
		if (hauteur % 2 != 1) 
			throw new PreconditionError("hauteur % 2 == 1");
		if (pointsVie <= 0)
			throw new PreconditionError("pointsVie > 0");
		
		// pre: x >= 0
		if (x < 0)
			throw new PreconditionError("x >= 0");
		// pre: x >= 0
		if (y < 0)
			throw new PreconditionError("y >= 0");
			
		super.init(x, y, largeur, hauteur, pointsVie);
		
		this.checkInvariants();
		
		// post: posx() == x
		if (super.getX() != x)
			throw new PostconditionError("posx() == x");
		// post: posy() == y
		if (super.getY() != y)
			throw new PostconditionError("posy() == y");
		// post: largeur() == largeur
		if (super.getLargeur() != largeur)
			throw new PostconditionError("largeur() == largeur");
		// post: hauteur() == hauteur
		if (super.getHauteur() != hauteur)
			throw new PostconditionError("hauteur() == hauteur");
		// post: pointsDeVie() == pointsVie
		if (super.getPointsDeVie() != pointsVie)
			throw new PostconditionError("pointsDeVie() == pointsVie");
		
		return this;
	}

	public void retrait(int s) {
		this.checkInvariants();
		
		// pre: s > 0 && ! estDetruite()
		if (s <= 0 || super.estDetruite())
			throw new PreconditionError("s > 0 && ! estDetruite()");
		
		int pv_pre = super.getPointsDeVie();
		super.retrait(s);
		
		this.checkInvariants();
		
		// post: pointsDeVie() == pointsDeVie@pre - s
		if (super.getPointsDeVie() != pv_pre - s)
			throw new PostconditionError("pointsDeVie() == pointsDeVie@pre - s");
	}
}
