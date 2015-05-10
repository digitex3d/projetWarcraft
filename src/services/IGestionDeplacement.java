package services;

import java.util.ArrayList;

import require.IRequireTerrain;

public interface IGestionDeplacement extends 
	IRequireTerrain {
	/* ########### Observators ########### */	
	ITerrain 	getTerr();
	boolean		estCalcChemin();
	
	// \pre: estCalcChemin(GD)
	ArrayList<Integer>	getCheminX();
	
	// \pre: estCalcChemin(GD)
	ArrayList<Integer>	getCheminY();
	
	// \pre: estCalcChemin(GD)
	ArrayList<Integer>	getPointArrivee();
	
	// \pre: estCalcChemin(GD)
	int			getFirstObstacle();
	
	/* ########### Constructors ########### */	
	
	/**
	 * \post: estCalcChemin() == false
	 */
	IGestionDeplacement init();
	
	/**
	 * [ calcChemin ]
	 * Soit vill \def terr.getListeVillageois().get(villNum)
	 * \post: estCalcChemin() == true
	 * \post: getCheminX().size() == getCheminY().size()
	 * \post: bonus = \sum i \from 0 \to getCheminX().size()- 1, terr.getBonusVitesse( getCheminX().get(i), getCheminY().get(i), vill.largeur(), vill.hauteur())
	 * \post: cheminX().size() = bonus + vill.getVitesse()
	 * Soit lgVill =vill.getLargeur()
	 * Soit htVill = vill.getHauteur()
	 * \post: firstObstacle(calcChemin(villNum, angle)) = \if (\exist \min i \in {i | i \in [0, |cheminX|[ && 
 	 *								\exist x \in [getCheminX().get(i), getCheminX().get(i) + lgVill[,
 	 *								\exist y \in [getCheminY().get(i),getCcheminY().get(i) + htVill[,
	 *								!getTerrain.estFranchissable( x, y)) i } ) 
	 *								else -1 
	 * 
	 * 
	 * \post: getPointArrivee(calcChemin(villNum, angle)) = 
	 *		if(firstObstacle(calcChemin(villNum, angle)) == -1), 
	 *			{getCheminX().get( cheminX().size()-1)), getCheminY().get( cheminY().size()-1))- 1))} 
	 *		if(firstObstacle(calcChemin(villNum, angle)) == 0), 
	 *			{vill.getX(), vill.getY()} 
	 *		else 
	 *			{getCheminX().get(firstObstacle(calcChemin(villNum, angle)) - 1),getCheminY().get( firstObstacle(calcChemin(villNum, angle)) - 1)} 
     *
	 * 
	 */
	void calcChemin(int villNum, int angle);
}
