package services;

import java.util.List;
import java.util.Set;

import enums.EEntite;

public interface ITerrain {
	/* ########### Observators ########### */	
	int getLargeur();
	int getHauteur();
	List<IMuraille> getListeMuraille();
	List<IRoute> getListeRoute();
	List<IVillageois> getListeVillageois();
	List<IMine> getListeMine();
	List<IHotelVille> getListeHotelVille();
	// pre: x >= 0 && y >= 0 && x + l < largeur() && y + h < hauteur()
	boolean estFranchissable(int x, int y, int l, int h);
	// pre: 0 <= x < largeur() && 0 <= y < hauteur()
	Set<EEntite> getEntiteAt(int x, int y);
	// pre: 0 <= x < largeur() && 0 <= y < hauteur()
	int getBonusVitesse(int x, int y);
	// pre: x >= 0 && y >= 0 && x + l < largeur() && y + h < hauteur()
	IRoute getRouteAt(int x, int y, int l, int h);

	/* ########### Constructors ########### */		
	/**
		pre: largeur >= 600 && hauteur >= 400
		post: largeur() == largeur
		post: hauteur() == hauteur
		post: \forall HV \in getListeHotelVille():
					HV.posx() + HV.largeur() < largeur && 
					HV.posy() + HV.hauteur() < hauteur &&
					HV.orRestant() == 16 &&
					\forall HV.posx() <= x < HV.posx() + HV.largeur()
					\forall HV.posy() <= y < HV.posy() + HV.hauteur()
						HDV \in getEntiteAt(x, y)
		post: \forall Vill \in getListeVillageois():
				HDV := HDV \in getListeHotelVille() && HDV.etatAppartenance() == Vill.race()
					Vill.posx() + Vill.largeur() < largeur && 
					Vill.posy() + Vill.hauteur() < hauteur &&
					distance(Vill.posx(), Vill.posy(), HDV.posx(), HDV.posy()) <= 51 &&
					Vill.pointsDeVie() == 100 &&
					\forall Vill.posx() <= x < Vill.posx() + Vill.largeur()
					\forall Vill.posy() <= y < Vill.posy() + Vill.hauteur()
						VILLAGEOIS \in getEntiteAt(x, y)
		post: \forall Mi \in getListeMine():
					Mi.posx() + Mi.largeur() < largeur && 
					Mi.posy() + Mi.hauteur() < hauteur &&
					\forall Mi.posx() <= x < Mi.posx() + Mi.largeur()
					\forall Mi.posy() <= y < Mi.posy() + Mi.hauteur()
						MINE \in getEntiteAt(x, y)
		post: \forall Ro \in getListeRoute():
					Ro.posx() + Ro.largeur() < largeur && 
					Ro.posy() + Ro.hauteur() < hauteur &&
					\forall Ro.posx() <= x < Ro.posx() + Ro.largeur()
					\forall Ro.posy() <= y < Ro.posy() + Ro.hauteur()
						ROUTE \in getEntiteAt(x, y)
		post: \forall Mu \in getListeMuraille():
					Mu.posx() + Mu.largeur() < largeur && 
					Mu.posy() + Mu.hauteur() < hauteur &&
					\forall Mu.posx() <= x < Mu.posx() + Mu.largeur()
					\forall Mu.posy() <= y < Mu.posy() + Mu.hauteur()
						MURAILLE \in getEntiteAt(x, y)
	 */
	ITerrain init(int largeur, int hauteur);
	
	/* ########### Operators ########### */
	/**
		pre: estFranchissable(x, y, l, h)
		post: \forall j \in [x, x + l[, \forall k \in [y, y + h[
			if getEntiteAt(j, k)@pre == {RIEN} then
				getEntiteAt(j, k) == {ent}
			else
				getEntiteAt(j, k) == getEntiteAt(j, k)@pre \plus {ent}
	 */
	void setEntiteAt(EEntite ent, int x, int y, int l, int h);
	
	/**
		pre: 0 <= numV < getListeVillageois().size() &&
		 	estFranchissable(xn, yn, getListeVillageois().get(numV).largeur(), getListeVillageois().get(numV).hauteur())
		post: Vill@pre :=  getListeVillageois()@pre.get(numV)
			  \forall x \in [xn, xn + Vill@pre.largeur()[,
			  \forall y \in [yn, yn + Vill@pre.hauteur()[,
					if getEntiteAt(x, y)@pre == {RIEN} then
						getEntiteAt(x, y) == {VILLAGEOIS}
					else
						getEntiteAt(x, y) == getEntiteAt(x, y)@pre \plus {VILLAGEOIS}
			  \forall x \in [Vill@pre.posx(), Vill@pre.posx() + Vill@pre.largeur()[ \and \not \in [xn, xn + Vill@pre.largeur()[,
			  \forall y \in [Vill@pre.posy(), Vill@pre.posy() + Vill@pre.hauteur()[ \and \not \in [yn, yn + Vill@pre.hauteur()[,
					if getEntiteAt(x, y)@pre == {VILLAGEOIS} then
						getEntiteAt(x, y) == {RIEN}
					else
						getEntiteAt(x, y) == getEntiteAt(x, y)@pre \minus {VILLAGEOIS}
					
	 */
	void moveVillageoisAt(int numV, int xn, int yn);
	
	/**
		pre: x >= 0 && y >= 0 && x + l < largeur() && y + h < hauteur() &&
				\forall j \in [x, x + l[, \forall k \in [y, y + h[
					ent \in getEntiteAt(j, k)
		post: \forall j \in [x, x + l[, \forall k \in [y, y + h[,
					if getEntiteAt(j, k)@pre.size() == 1 then
						getEntiteAt(j, k) == {RIEN}
					else 
						getEntiteAt(j, k) == getEntiteAt(j, k)@pre \minus {ent}
	 */
	void removeEntiteAt(EEntite ent, int x, int y, int l, int h);
	
	/**
		pre: 0 <= numV < getListeVillageois().size() && 
				MINE \in getEntiteAt(getListeVillageois().get(numV).posx(),
				 					 getListeVillageois().get(numV).posy())
		post: Vill := getListeVillageois().get(numV)
			\forall x \in [Vill.posx(), Vill.posx() + Vill.largeur()[,
			\forall y \in [Vill.posy(), Vill.posy() + Vill.hauteur()[,
				VILLAGEOIS \in getEntiteAt(x, y)
	 */
	void reinsertVillageois(int numV);
}
