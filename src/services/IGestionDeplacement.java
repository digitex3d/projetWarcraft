package services;

import java.util.ArrayList;

import require.IRequireVillageois;

public interface IGestionDeplacement extends IRequireVillageois {
	/* ########### Observators ########### */	
	ITerrain 	getTerr();
	IVillageois	getVill();
	int			getAngle();
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
	 * \pre: 0 <= angle <= 360
	 * \post: estCalcChemin() == false
	 */
	IGestionDeplacement init(int angle);
	
	/**
	 * [ calcChemin ]
	 * \post: estCalcChemin() == true
	 * \post: getCheminX().size() == getCheminY().size()
	 * \post: bonus = \sum i \from 0 \to getCheminX().size()- 1, terr.getBonusVitesse( getCheminX().get(i), getCheminY().get(i), vill.largeur(), vill.hauteur())
	 * \post: cheminX().size() = bonus + getVill().getVitesse()
	 * Soit lgVill =getVill().getLargeur()
	 * Soit htVill = getVill().getHauteur()
	 * \post: firstObstacle(calcChemin(GD)) = \if (\exist \min i \in {i | i \in [0, |cheminX|[ && 
 	 *								\exist x \in [getCheminX().get(i), getCheminX().get(i) + lgVill[,
 	 *								\exist y \in [getCcheminY().get(i),getCcheminY().get(i) + htVill[,
	 *								!getTerrain.estFranchissable( x, y)) i } ) 
	 *								else -1 
	 * 
	 * \post: getCheminX().get( cheminX().size()-1)) == getVill().getX() + bonus + getVill().getVitesse() * cos( angle(GD) )
	 * \post: getCheminY().get( cheminY().size()-1)) == getVill().getY() + bonus + getVill().getVitesse() * -sin( angle(GD) )
	 * 
	 * \post: getPointArrivee(calcChemin(GD)) = 
	 *		if(firstObstacle(calcChemin(GD)) == -1), 
	 *			{getCheminX().get( cheminX().size()-1)), getCheminY().get( cheminY().size()-1))- 1))} 
	 *		if(firstObstacle(calcChemin(GD)) == 0), 
	 *			{getVill().getX(), getVill().getY()} 
	 *		else 
	 *			{getCheminX().get(firstObstacle(calcChemin(GD)) - 1),getCheminY().get( firstObstacle(calcChemin(GD)) - 1)} 
     *
	 * 
	 */
	void calcChemin();
}
