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

		// \pre: !vill.estMort() && !vill.estOccupe() && 0 <= angle  &&  angle <= 360
		if (!( !vill.estMort() && !vill.estOccupe() && 0 <= angle  &&  angle <= 360)) 
			throw new PreconditionError("!vill.estMort() && !vill.estOccupe() && 0 <= angle  &&  angle <= 360");


		/* ######## 	Execution  		######### */
		super.init(terr, vill, angle);

		// Deuxième check des invariants
		this.checkInvariants();

		/* ######## Verification des postcondition ######### */

		// \post: estCalcChemin() == false
		if (super.estCalcChemin())                                     
			throw new PostconditionError("estCalcChemin() == false"); 

		// \post: vill() == V
		if (super.getVill() != vill)                                   
			throw new PostconditionError(" vill() == V"); 
		// \post: terr() == T
		if (super.getTerr() != terr)                                     
			throw new PostconditionError(" terr() == T"); 
		// \post: angle() == angle
		if (super.getAngle() != angle)                                     
			throw new PostconditionError("angle() == angle"); 		


		return this;


	}

	//	------------------------------- [ calcChemin ] -------------------------------
	public void calcChemin(){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */

		// \pre: estCalcChemin() == true
		if ( ! (super.estCalcChemin()) ) 
			throw new PreconditionError("estCalcChemin() == true");

		/* ######## 	Execution  		######### */
		super.calcChemin();


		/* ######## Verification des postcondition ######### */

		// \post: super.getCheminX().size() == super.getCheminY().size() 
		if ( ! (super.getCheminX().size() == super.getCheminY().size() ) ) 
			throw new PostconditionError("super.getCheminX().size() == super.getCheminY().size() ");

		// \post: super.getCheminX().size() == super.getCheminY().size() 
		if ( ! (super.getCheminX().size() == super.getCheminY().size() ) ) 
			throw new PostconditionError("super.getCheminX().size() == super.getCheminY().size() ");

		// \post: let bonus = \sum i \from 0 \to cheminX().size() - 1 in 
		//			terr.getBonusVitesse(cheminX().get(i),  cheminY().get(i))
		//			cheminX().size() == bonus + vill.vitesse()	
		int bonus = 0;
		for (int i = 0; i < getCheminX().size(); i++) {
			bonus += super.getTerr().getBonusVitesse(super.getCheminX().get(i), super.getCheminX().get(i) );
		}

		if ( ! (super.getCheminX().size() == bonus + super.getVill().getVitesse() ) ) 
			throw new PostconditionError("cheminX().size() == bonus + vill.vitesse() ");


		// \post: Soit lgVill \def  Villageois::largeur(vill(GD))
		//        Soit htVill \def  Villageois::hauteur(vill(GD))
		// firstObstacle(calcChemin(GD)) = 
		// 		i si \exist min i \in {i | i \in [0, |cheminX|[ \and 
		// 	 	\exist x \in [get(cheminX(GD), i), get(cheminX(GD), i) + lgVill[,
		// 	 	\exist y \in [get(cheminY(GD), i), get(cheminY(GD), i) + htVill[,
		// 		¬Terrain::estFranchissable(terr(GD), x, y)}
		// 		-1 sinon
		int lgVill = super.getVill().getLargeur();
		int htVill = super.getVill().getHauteur();

		for (int i = 0; i < super.getCheminX().size(); i++) 
			if( !super.getTerr().estFranchissable(	super.getCheminX().get(i), 
					super.getCheminY().get(i), 
					lgVill,
					htVill)){
				if ( ! (this.getFirstObstacle() == i) ) 
					throw new PostconditionError("this.firstObstacle == i ");

			}

		if ( ! (this.getFirstObstacle() != -1)  ) 
			throw new PostconditionError("getFirstObstacle() != -1");

		// \post: getCheminX().get( super.getCheminX().size() -1 ) ==  
		//	   getVill().getX() + bonus + getVill().getVitesse() * cos(getAngle())
		if ( super.getCheminX().get( super.getCheminX().size() -1 )  != 
				super.getVill().getX() + bonus + super.getVill().getVitesse() * Math.cos(super.getAngle())
				) 
			throw new PostconditionError("getCheminX().get( super.getCheminX().size() -1 ) == "
					+ "getVill().getX() + bonus + getVill().getVitesse() * cos(getAngle())");

		// \post:	   get(cheminY(calcChemin(GD)), |cheminY(calcChemin(GD))|-1)) =  
		//	   	Villageois::posy(vill(GD)) + (bonus + Villageois::vitesse(vill(GD))) * -sin( angle(GD) )
		if ( super.getCheminY().get( super.getCheminY().size() -1 )  != 
				super.getVill().getY() + bonus + super.getVill().getVitesse() * -Math.sin(super.getAngle())
				) 
			throw new PostconditionError("getCheminY().get( super.getCheminY().size() -1 ) == "
					+ "getVill().getY() + bonus + getVill().getVitesse() * -sin(getAngle())");

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
			if (  paX != super.getVill().getX()  || 
					paY != super.getVill().getY() ){
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
