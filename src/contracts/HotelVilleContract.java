package contracts;

import decorators.HotelVilleDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.IHotelVille;
import services.IMine;

// Terminer
public class HotelVilleContract extends HotelVilleDecorator {

	public HotelVilleContract(IHotelVille delegate) {
		super(delegate);
		
	}

	// Pas d'invariants
	public void checkInvariants() {	}
	
	
	
	public IHotelVille init(int x, int y, int largeur, int hauteur, int or){
		/* ######## Verification des préconditions ######### */
		/* pre init(x,y,largeur,hauteur,or) require 
		* 				largeur%2=1 ∧ 
		* 				hauteur%2=1 ∧
		*  			0 <= posx <= largeur ∧
		*  			0 <= posy <= hauteur ∧
		*  		    or >= 0
		* 
		*/
		if (largeur % 2 != 1) 
			throw new PreconditionError("pre: largeur%2!=1");
		if (hauteur % 2 != 1) 
			throw new PreconditionError("pre: hauteur%2!=1");
		if (x > hauteur || x < 0) 
			throw new PreconditionError("pre: 0 <= posx <= largeur");
		if (y > hauteur || y < 0) 
			throw new PreconditionError("pre: 0 <= posy <= hauteur");
		if (or  < 0) 
			throw new PreconditionError("pre: or >= 0");

		//Sauvegarde contexte  

		/* ######## 	Execution  		######### */
		super.init(x, y, largeur, hauteur, or);

		// Deuxième check des invariants
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		// \post :  largeur(init(l,h))=l
		if( super.getLargeur() != largeur)
			throw new PostconditionError("largeur(init(l,h))=l");
		// \post :  hauteur(init(l,h))=h
		if( super.getHauteur() != hauteur)
			throw new PostconditionError("hauteur(init(l,h))=h");
		// \post : orRestant(init(x,y,l,h,or))=or
		if( super.getOrRestant() != or)
			throw new PostconditionError("orRestant(init(x,y,l,h,or))=or");
		// \post: posX(init(x, y, l, h, pv)) = x
		if (super.getX() != x)
			throw new PostconditionError("post: posX(init(x, y, l, h, or)) = x");
		// \post: posY(init(x, y, l, h, pv)) = y
		if (super.getY() != y)
			throw new PostconditionError("post: posY(init(x, y, l, h, or))) = y");
		
		
		return this;
	}
	
	public void depot(int s){
		/* ######## Premier check des invariants ######### */
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
	
		// \pre retrait(M,s) require ¬estLaminee(M) ∧ s>0
		if( s <= 0)
			throw new PreconditionError("retrait(M,s) require  s>0");

		/* ######## 	Sauvegarde contexte  		######### */
		int OrRestant_pre = super.getOrRestant();
		
		// Execution
		super.depot(s);
		
		/* ######## Deuxième check des invariants ######### */
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		// \post: orRestant(retrait(H,s))=orRestant(H)+s
			if (super.getOrRestant() != OrRestant_pre + s)                                     
				throw new PostconditionError("orRestant(retrait(H,s))=orRestant(H)+s");  
			
	}
	
	
}
