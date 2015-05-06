service : GestionDeplacement
types: List<int>, Terrain, Villageois, boolean, int
observators:
	const terr: [GestionDeplacement] \larr Terrain
	const vill: [GestionDeplacement] \larr Villageois
	const angle: [GestionDeplacement] \larr int
	estCalcChemin: [GestionDeplacement] \larr boolean
	cheminX: [GestionDeplacement] \larr List<int>
		pre cheminX(GD) require estCalcChemin(GD)
	cheminY: [GestionDeplacement] \larr List<int>
		pre cheminY(GD) require estCalcChemin(GD)
	getPointArrivee: [GestionDeplacement] \larr List<int>
		pre getPointArrivee(GD) require estCalcChemin(GD)
 	firstObstacle: [GestionDeplacement] \larr int
		pre firstObstacle(GD) require estCalcChemin(GD)
 
constructors:
	init: Terrain \per Villageois \per int \larr [GestionDeplacement]
		pre init(T, V, angle) require 
			\not Villageois::estMort(V) \and 
			\not Villageois::estOccupe(V) \and 
			0 \lte angle \lte 360

operators:
	calcChemin: [GestionDeplacement] \larr [GestionDeplacement]
		
observations:
	[init]
		estCalcChemin(init(T, V, angle)) = false
		vill(init(T, V, angle)) = V
		terr(init(T, V, angle)) = T
		angle(init(T, V, angle)) = angle

[calcChemin]
	estCalcChemin(calcChemin(GD)) = true
	|cheminX(calcChemin(GD))| = |cheminY(calcChemin(GD))|
	Soit bonus \def \sum i \from 0 \to |cheminX(GD)| - 1, 
	Terrain::getBonusVitesse(terr(GD),  get(cheminX(GD), i),  get(cheminY(GD), i))
	|cheminX(calcChemin(GD))| = bonus + Villageois::vitesse(vill(GD))
	Soit lgVill \def  Villageois::largeur(vill(GD))
	Soit htVill \def  Villageois::hauteur(vill(GD))
	firstObstacle(calcChemin(GD)) = 
	i si \exist min i \in {i | i \in [0, |cheminX|[ \and 
 	\exist x \in [get(cheminX(GD), i), get(cheminX(GD), i) + lgVill[,
 	\exist y \in [get(cheminY(GD), i), get(cheminY(GD), i) + htVill[,
	¬Terrain::estFranchissable(terr(GD), x, y)}
	-1 sinon
	get(cheminX(calcChemin(GD)), |cheminX(calcChemin(GD))|-1)) =  
				Villageois::posx(vill(GD)) + (bonus + Villageois::vitesse(vill(GD))) * cos( angle(GD) )
get(cheminY(calcChemin(GD)), |cheminY(calcChemin(GD))|-1)) =  
Villageois::posy(vill(GD)) + (bonus + Villageois::vitesse(vill(GD))) * -sin( angle(GD) )

getPointArrivee(calcChemin(GD)) = 
{get(cheminX, |cheminX| - 1), get(cheminX, |cheminY| - 1)} si firstObstacle(calcChemin(GD)) = -1
{Villageois::posx(vill(GD)), Villageois::posy(vill(GD))} si firstObstacle(calcChemin(GD)) = 0
{get(cheminX, firstObstacle(calcChemin(GD)) - 1), get(cheminY, firstObstacle(calcChemin(GD)) - 1)} sinon
