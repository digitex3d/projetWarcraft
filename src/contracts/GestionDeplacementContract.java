package contracts;

import services.IGestionDeplacement;
import services.ITerrain;
import services.IVillageois;
import decorators.GestionDeplacementDecorator;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class GestionDeplacementContract extends GestionDeplacementDecorator {

	public GestionDeplacementContract(IGestionDeplacement delegate) {
		super(delegate);

	}

	// Pas d'invariants
	public void checkInvariants() {	}

	//	------------------------------- [ init ] -------------------------------
	public IGestionDeplacement init(ITerrain terr, IVillageois vill, int angle){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */

		// \pre: 0 <= angle  &&  angle <= 360
		if (!( 0 <= angle  &&  angle <= 360)) 
			throw new PreconditionError("0 <= angle  &&  angle <= 360");


		/* ######## 	Execution  		######### */
		super.init();

		// Deuxième check des invariants
		this.checkInvariants();

		/* ######## Verification des postcondition ######### */

		// \post: estCalcChemin() == false
		if (super.estCalcChemin())                                     
			throw new PostconditionError("estCalcChemin() == false"); 

		// \post: terr() == T
		if (super.getTerr() != terr)                                     
			throw new PostconditionError(" terr() == T"); 


		return this;


	}

	//	------------------------------- [ calcChemin ] -------------------------------
	public void calcChemin(int numVill, int angle){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des preconditions ######### */
		// \pre: 0 <= angle <= 360
		if ( angle > 360 || angle < 0 ) 
				throw new PreconditionError(" 0 <= angle <= 360");
		
		// \pre: numVill <= getTerr().getListeVillageois().size()
		if ( numVill > this.getTerr().getListeVillageois().size() ) 
				throw new PreconditionError("numVill <= getTerr().getListeVillageois().size()");

		/* ######## 	Execution  		######### */
		super.calcChemin(numVill, angle);


		/* ######## Verification des postcondition ######### */
		
		// \post: estCalcChemin() == true
		if ( ! (super.estCalcChemin()) ) 
			throw new PostconditionError("estCalcChemin() == true");
		
		IVillageois vill = super.getTerr().getListeVillageois().get(numVill);
		// \post: super.getCheminX().size() == super.getCheminY().size() 
		if ( ! (super.getCheminX().size() == super.getCheminY().size() ) ) 
			throw new PostconditionError("super.getCheminX().size() == super.getCheminY().size() ");

		// \post: super.getCheminX().size() == super.getCheminY().size() 
		if ( ! (super.getCheminX().size() == super.getCheminY().size() ) ) 
			throw new PostconditionError("super.getCheminX().size() == super.getCheminY().size() ");

		// \post: let bonus = \sum i \from 0 \to cheminX().size() - 1 in 
		//			terr.getBonusVitesse(cheminX().get(i),  cheminY().get(i), vill.largeur(), vill.hauteur())
		//			cheminX().size() == bonus + vill.vitesse()	
		int bonus = 0;
		for (int i = 0; i < getCheminX().size(); i++) {
			bonus += super.getTerr().getBonusVitesse(super.getCheminX().get(i), super.getCheminY().get(i), vill.getLargeur(), vill.getHauteur());
		}

		
		if ( ! (super.getCheminX().size() <= bonus + vill.getVitesse() ) ) 
			throw new PostconditionError("cheminX().size() == bonus + vill.vitesse() ");


		// \post: Soit lgVill \def  Villageois::largeur(vill(GD))
		//        Soit htVill \def  Villageois::hauteur(vill(GD))
		// firstObstacle(calcChemin(GD)) = 
		// 		i si \exist min i \in {i | i \in [0, |cheminX|[ \and 
		// 	 	\exist x \in [get(cheminX(GD), i), get(cheminX(GD), i) + lgVill[,
		// 	 	\exist y \in [get(cheminY(GD), i), get(cheminY(GD), i) + htVill[,
		// 		¬Terrain::estFranchissable(terr(GD), x, y)}
		// 		-1 sinon
		int lgVill = vill.getLargeur();
		int htVill = vill.getHauteur();

		for (int i = 0; i < super.getCheminX().size(); i++) 
			if( !super.getTerr().estFranchissable(	super.getCheminX().get(i), 
					super.getCheminY().get(i), 
					lgVill,
					htVill)){
				if ( ! (this.getFirstObstacle() == i) ) 
					throw new PostconditionError("this.firstObstacle == i ");

			}
		

		// \post: getPointArrivee(calcChemin(GD)) = 
		// if firstObstacle() == -1 
		//			{getCheminY().get( getCheminY().size() -1 ), getCheminY().get( getCheminY().size() -1 )} 
		//	else if firstObstacle() == 0
		//		{vill.posx(), vill.posy()}
		//	else {getCheminX().get(  firstObstacle() - 1 ), getCheminY().get(  firstObstacle() - 1 )} 

		int paX = super.getPointArrivee().get(0);
		int paY = super.getPointArrivee().get(1);

		if( super.getFirstObstacle() == -1){
			if (  paX != super.getCheminX().get( super.getCheminX().size() -1 ) || 
					paY != super.getCheminY().get( super.getCheminY().size() -1 )){
				throw new PostconditionError("firstObstacle() == -1 => {getCheminY().get( getCheminY().size() -1 ), "
						+ "getCheminY().get( getCheminY().size() -1 )");
			}
		}else if ( super.getFirstObstacle() == 0){
			if (  paX != vill.getX()  || 
					paY != vill.getY() ){
				throw new PostconditionError("firstObstacle() == 0 => {vill.posx(), vill.posy()}");
			}

		}else{
			if (  paX != super.getCheminX().get( super.getFirstObstacle() -1 ) || 
					paY != super.getCheminY().get( super.getFirstObstacle() -1 )){
				throw new PostconditionError("getCheminX().get(  firstObstacle() - 1 ),"
						+ " getCheminY().get(  firstObstacle() - 1 )");
			}


		}


		// Deuxième check des invariants
		this.checkInvariants();



	}

}
