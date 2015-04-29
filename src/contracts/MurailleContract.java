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
		// \inv: estDetruite(M) min= pointsDeVie(M) <= 0
		if (super.estDetruite() && super.getPointsDeVie() > 0)
			throw new InvariantError("estDetruite(M) min= pointsDeVie(M) <= 0");
	}
	
	public IMuraille init(int x, int y, int largeur, int hauteur, int pointsVie) {
		this.checkInvariants();
		
		// \pre: init(x, y, l, h, pv) require l%2 = 1 ∧ h%2 = 1 ∧ pv > 0
		if (largeur % 2 != 1)
			throw new PreconditionError("pre: largeur%2 != 1");
		if (hauteur % 2 != 1) 
			throw new PreconditionError("pre: hauteur%2 != 1");
		if (pointsVie <= 0)
			throw new PreconditionError("pre: pv > 0");
			
		super.init(x, y, largeur, hauteur, pointsVie);
		
		this.checkInvariants();
		
		// \post: posX(init(x, y, l, h, pv)) = x
		if (super.getPosX() != x)
			throw new PostconditionError("post: posX(init(x, y, l, h, pv)) = x");
		// \post: posY(init(x, y, l, h, pv)) = y
		if (super.getPosY() != y)
			throw new PostconditionError("post: posY(init(x, y, l, h, pv)) = y");
		// \post: largeur(init(x, y, l, h, pv)) = l
		if (super.getLargeur() != largeur)
			throw new PostconditionError("post: largeur(init(x, y, l, h, pv)) = l");
		// \post: hauteur(init(x, y, l, h, pv)) = h
		if (super.getHauteur() != hauteur)
			throw new PostconditionError("post: hauteur(init(x, y, l, h, pv)) = h");
		// \post: pointsDeVie(init(x, y, l, h, pv)) = pv
		if (super.getPointsDeVie() != pointsVie)
			throw new PostconditionError("post: pointsDeVie(init(x, y, l, h, pv)) = pv");
		
		return this;
	}

	public void retrait(int s) {
		this.checkInvariants();
		
		// \pre: retrait(M, s) require s > 0 ∧ ¬estDetruite(M)
		if (s <= 0 || super.estDetruite())
			throw new PreconditionError("pre: retrait(M, s) require s > 0 ∧ ¬estDetruite(M)");
		
		int pv_pre = super.getPointsDeVie();
		super.retrait(s);
		
		this.checkInvariants();
		
		// \post: pointsDeVie(retrait(M, s)) = pointsDeVie(M)@pre - s
		if (super.getPointsDeVie() != pv_pre - s)
			throw new PostconditionError("post: pointsDeVie(retrait(M, s)) = pointsDeVie(M)@pre - s");
	}
}
