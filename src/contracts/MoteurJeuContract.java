package contracts;

import decorators.MoteurJeuDecorator;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.IMine;
import services.IMoteurJeu;
import services.IVillageois;

public class MoteurJeuContract extends MoteurJeuDecorator{
	public MoteurJeuContract(IMoteurJeu delegate) {
		super(delegate);
		
	}
	
	public void checkInvariants() {}
	
	// --------------------- [init] -----------------------------
	public IMoteurJeu init(int largeur, int hauteur, int maxPasJeu){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		// \pre init(largeur,hauteur,maxPas) require 
		//						largeur≥ 600 
		//					∧ hauteur≥ 400 
		//					∧ maxPas≥ 0
		if( largeur < 600)
			throw new PreconditionError("pre init(largeur,hauteur,maxPas) require largeur ≥ 600");
		if( largeur < 400)
			throw new PreconditionError("pre init(largeur,hauteur,maxPas) require hauteur ≥ 400");
		if( maxPasJeu < 0)
			throw new PreconditionError("pre init(largeur,hauteur,maxPas) require maxPas ≥ 0");


		/* ######## 	Execution  		######### */
		super.init(largeur, hauteur, maxPasJeu);

		/* ######## Verification des postcondition ######### */
		// \post: maxPasJeu(init(l,h,m))=m
		if( this.getMaxPasJeu() != maxPasJeu )
			throw new PostconditionError("post: maxPasJeu(init(l,h,m))=m");
		// \post: pasJeuCourant(init(l,h,m))=0	
		if( this.getPasJeuCourant() != 0 )
			throw new PostconditionError("post: pasJeuCourant(init(l,h,m))=0");
		// \post: largeurTerrain(init(l,h,m))=l
		if( this.getLargeurTerrain() != largeur)
			throw new PostconditionError("post: largeurTerrain(init(l,h,m))=l");
		// \post: hauteurTerrain(init(l,h,m))=h
		if( this.getHauteurTerrain() != hauteur)
			throw new PostconditionError("post: hauteurTerrain(init(l,h,m))=h");
		// \post: estFini(init(l,h,m))=False
		if( this.estFini())
			throw new PostconditionError("post: estFini(init(l,h,m))=False");
		
		// Initialisation Hotel de ville
		// \post: positionHotelVilleX( init(l,h,m) ) <= 51 
		if( this.getPositionHotelVilleX() > 51)
			throw new PostconditionError("post: positionHotelVilleX( init(l,h,m) ) <= 51 ");
		
		// \post: positionHotelVilleY( init(l,h,m) ) <= 51 
		if( this.getPositionHotelVilleY() > 51)
			throw new PostconditionError("post: positionHotelVilleY( init(l,h,m) ) <= 51 ");
		// \post: HotelVille::orRestant( getHotelVille( init(l,h,m) ) ) = 16
		if( this.getHotelDeVille().getOrRestant() != 16)
			throw new PostconditionError("post: HotelVille::orRestant( getHotelVille( init(l,h,m) ) ) = 16");
		
		// // Initialisation Villageois
		// \forall numV \in numeroesVillageois:
		// 	  positionVillageoisY(M, numV) - positionHotelVilleY(M) <= 51
		//      	Ʌ  positionVillageoisX(M, numV) - positionHotelVilleX(M) <= 51
	    //  	    Ʌ Villageois::pointsDeVie( getVillageois(M, numV) ) = 100
		//     	Ʌ Villageois::quantiteOr( getVillageois(M, numV) ) = 0
		//  		Ʌ Villageois::estMort( getVillageois(M, numV) ) = False
		for( IVillageois v : this.getListVillageois() ){
			if( v.getX() - this.hotelDeVille.getX() > 51 )
				throw new PostconditionError("post: positionVillageoisY(M, numV) - positionHotelVilleY(M) <= 51 ");
			if( v.getY() - this.hotelDeVille.getY() > 51 )
				throw new PostconditionError("post: positionVillageoisY(M, numV) - positionHotelVilleY(M) <= 51 ");
			if( v.getQuantiteOr() != 0)
				throw new PostconditionError("post: Villageois::quantiteOr( getVillageois(M, numV) ) = 0");
			if( v.getPointsDeVie() != 100)
				throw new PostconditionError("post: Villageois::pointsDeVie( getVillageois(M, numV) ) = 100");
			if( v.estMort() != false)
				throw new PostconditionError("post: Villageois::estMort( getVillageois(M, numV) ) = False");
		}
		
		//// Initialisation Mines
		//\forall numM \in numeroesMine:
		//	  positionMineY(M, numM) <= largeurTerrain
		//     	Ʌ  positionMineX(M, numM) <= hauteurTerrain
		// 	     Ʌ Mine::estAbandonne( getVillageois(M, numV) ) = True
		for( IMine m : this.getListMines() ){
			if( m.getX() > this.getLargeurTerrain() )
				throw new PostconditionError("post: positionMineX(M, numM) <= largeurTerrain ");
			if( m.getY() - this.hotelDeVille.getY() > 51 )
				throw new PostconditionError("post: positionMineY(M, numM) <= hauteurTerrain ");
			if( m.estAbandonne() != false)
				throw new PostconditionError("post: Mine::estAbandonne( getMine(M, numM) ) = True");
		
		}
		
		
		// Deuxième check des invariants
		this.checkInvariants();
		
		return this;

	}
}

