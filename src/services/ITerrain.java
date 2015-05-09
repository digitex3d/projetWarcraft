package services;

import java.util.List;
import java.util.Set;

import require.IRequireListHotelVille;
import require.IRequireListMine;
import require.IRequireListMuraille;
import require.IRequireListRoute;
import require.IRequireListVillageois;
import enums.EEntite;

public interface ITerrain extends 
	IRequireListHotelVille, 
	IRequireListMine,
	IRequireListMuraille,
	IRequireListRoute,
	IRequireListVillageois {
	
	/* ########### Observators ########### */	
	public int getLargeur();
	public int getHauteur();
	public List<IMuraille> getListeMuraille();
	public List<IRoute> getListeRoute();
	public List<IVillageois> getListeVillageois();
	public List<IMine> getListeMine();
	public List<IHotelVille> getListeHotelVille();
	// pre: x >= 0 && y >= 0 && x + l <= largeur() && y + h <= hauteur()
	public boolean estFranchissable(int x, int y, int l, int h);
	// pre: 0 <= x < largeur() && 0 <= y < hauteur()
	public Set<EEntite> getEntiteAt(int x, int y);
	// pre: x >= 0 && y >= 0 && x + l <= largeur() && y + h <= hauteur()
	public int getBonusVitesse(int x, int y, int l, int h);
	// pre: x >= 0 && y >= 0 && x + l <= largeur() && y + h <= hauteur()
	public IRoute getRouteAt(int x, int y, int l, int h);

	/* ########### Constructors ########### */		
	/**
		pre: largeur >= 600 && hauteur >= 400
		post: largeur() == largeur
		post: hauteur() == hauteur
		post: \forall HV \in getListeHotelVille():
					HV.posx() + HV.largeur() <= largeur && 
					HV.posy() + HV.hauteur() <= hauteur &&
					HV.orRestant() == 16 &&
					\forall HV.posx() <= x < HV.posx() + HV.largeur()
					\forall HV.posy() <= y < HV.posy() + HV.hauteur()
						HDV \in getEntiteAt(x, y)
		post: \forall Vill \in getListeVillageois():
				HDV := HDV \in getListeHotelVille() && HDV.etatAppartenance() == Vill.race()
					Vill.posx() + Vill.largeur() <= largeur && 
					Vill.posy() + Vill.hauteur() <= hauteur &&
					distance(Vill.posx(), Vill.posy(), HDV.posx(), HDV.posy()) <= 51 &&
					Vill.pointsDeVie() == 100 &&
					\forall Vill.posx() <= x < Vill.posx() + Vill.largeur()
					\forall Vill.posy() <= y < Vill.posy() + Vill.hauteur()
						VILLAGEOIS \in getEntiteAt(x, y)
		post: \forall Mi \in getListeMine():
					Mi.posx() + Mi.largeur() <= largeur && 
					Mi.posy() + Mi.hauteur() <= hauteur &&
					\forall Mi.posx() <= x < Mi.posx() + Mi.largeur()
					\forall Mi.posy() <= y < Mi.posy() + Mi.hauteur()
						MINE \in getEntiteAt(x, y)
		post: \forall Ro \in getListeRoute():
					Ro.posx() + Ro.largeur() <= largeur && 
					Ro.posy() + Ro.hauteur() <= hauteur &&
					\forall Ro.posx() <= x < Ro.posx() + Ro.largeur()
					\forall Ro.posy() <= y < Ro.posy() + Ro.hauteur()
						ROUTE \in getEntiteAt(x, y)
		post: \forall Mu \in getListeMuraille():
					Mu.posx() + Mu.largeur() <= largeur && 
					Mu.posy() + Mu.hauteur() <= hauteur &&
					\forall Mu.posx() <= x < Mu.posx() + Mu.largeur()
					\forall Mu.posy() <= y < Mu.posy() + Mu.hauteur()
						MURAILLE \in getEntiteAt(x, y)
	 */
	public ITerrain init(int largeur, int hauteur);
	
	/* ########### Operators ########### */
	/**
		pre: estFranchissable(x, y, l, h)
		post: \forall j \in [x, x + l[, \forall k \in [y, y + h[
			if getEntiteAt(j, k)@pre == {RIEN} then
				getEntiteAt(j, k) == {ent}
			else
				getEntiteAt(j, k) == getEntiteAt(j, k)@pre \plus {ent}
	 */
	public void setEntiteAt(EEntite ent, int x, int y, int l, int h);
	
	
	/**
		pre: x >= 0 && y >= 0 && x + l <= largeur() && y + h <= hauteur() &&
				\forall j \in [x, x + l[, \forall k \in [y, y + h[
					ent \in getEntiteAt(j, k)
		post: \forall j \in [x, x + l[, \forall k \in [y, y + h[,
					if getEntiteAt(j, k)@pre.size() == 1 then
						getEntiteAt(j, k) == {RIEN}
					else 
						getEntiteAt(j, k) == getEntiteAt(j, k)@pre \minus {ent}
	 */
	public void removeEntiteAt(EEntite ent, int x, int y, int l, int h);
	
	/**
		pre: 0 <= numV < getListeVillageois().size() && 
				MINE \in getEntiteAt(getListeVillageois().get(numV).posx(),
				 					 getListeVillageois().get(numV).posy())
		post: Vill := getListeVillageois().get(numV)
			\forall x \in [Vill.posx(), Vill.posx() + Vill.largeur()[,
			\forall y \in [Vill.posy(), Vill.posy() + Vill.hauteur()[,
				VILLAGEOIS \in getEntiteAt(x, y)
	 */
	public void reinsertVillageois(int numV);
	
	/* ########### Invariants ########### */
	// inv: getRouteAt(x, y) min= ro \verifies ro \in getListeRoute() && ro.posx() \in [x, x + l[ && ro.posy() \in [y, y + h[
	// inv: estFranchissable(x, y, l, h) min= \forall i \in [x, x + l[, \forall j \in [y, y + h[,
	//											getEntiteAt(i, j) == {RIEN} ∨ getEntiteAt(x, y) == {ROUTE}
	// inv: if \exist j \in [x, x + l[, k \in [y, y + h[ \verifies {ROUTE} == getEntiteAt(j, k) then
	//			getBonusVitesse(x, y, l, h) min= getRouteAt(j, k).bonusVitesse()
	//		else getBonusVitesse(x, y, l, h) min= 0
	// inv:	\forall x \in [0, largeur[, \forall y \in [0, hauteur[
	//		if getEntiteAt(x, y).size() > 0 &&
	//		if RIEN \in getEntiteAt(x, y) then getEntiteAt(x, y).size == 1
}
