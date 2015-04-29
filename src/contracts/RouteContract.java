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
	public IRoute init(int x, int y, int largeur, int hauteur, int mult) {
		/* 	pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
		* 	require 	largeur%2=1 ∧ 
		* 				hauteur%2=1 ∧
		* 				force > 0	∧
		* 				vitesse > 0 ∧
		* 				pointsVie > 0
		*/
		if (largeur % 2 != 1) 
			throw new PreconditionError("pre: largeur%2!=1");
		if (hauteur% 2 != 1)           
			throw new PreconditionError("pre: hauteur%2!=1");
		if (mult < 0)                   
			throw new PreconditionError("pre: force <= 0");
	
		
		 super.init(x, y, largeur, hauteur, mult);
		 
		 /* post
		  * posx(init(x,y,l,h,m))=x
		  * posy(init(x,y,l,h,m))=y
		  * largeur(init(x,y,l,h,m))=l
		  * hauteur(init(x,y,l,h,m))=h
		  * mult(init(x,y,l,h,m))=m
		  */
		  /* ######## Verification des postcondition ######### */
		  // \post :  largeur(init(l,h))=l
		  if( super.getLargeur() != largeur)
		  	throw new PostconditionError("largeur(init(l,h))=l");
		  // \post :  hauteur(init(l,h))=h
		  if( super.getHauteur() != hauteur)
		  	throw new PostconditionError("hauteur(init(l,h))=h");
		  // \post :  posx(init(x,y,l,h,m))=x
		  if( super.getX() != x)
		  	throw new PostconditionError("posx(init(x,y,l,h,m))=x");
		  // \post :  posy(init(y,x,l,h,m))=y
		  if( super.getY() != y)
		  	throw new PostconditionError("posy(init(x,y,l,h,m))=y");
		  // \post :  mult(init(x,y,l,h,m))=m
		  if( super.getMult() != mult)
		  	throw new PostconditionError("mult(init(x,y,l,h,m))=m");
		 
		 return this;
	}


}
