package contracts;

import java.util.ArrayList;
import java.util.Set;

import services.IHotelVille;
import services.IMine;
import services.IMuraille;
import services.IRoute;
import services.ITerrain;
import services.IVillageois;
import utils.Utils;
import decorators.TerrainDecorator;
import enums.EEntite;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class TerrainContract extends TerrainDecorator {
	public TerrainContract(ITerrain delegate) {
		super(delegate);
	}
	
	//TODO implementare invarianti di minimizzazione
	public void checkInvariants() {
		// inv: getRouteAt(x, y) min= ro \verifies ro \in getListeRoute() && ro.posx() \in [x, x + l[ && ro.posy() \in [y, y + h[
		// inv: estFranchissable(x, y, l, h) min= \forall i \in [x, x + l[, \forall j \in [y, y + h[,
		//											getEntiteAt(i, j) == {RIEN} ∨ getEntiteAt(x, y) == {ROUTE}
		// inv: if \exist j \in [x, x + l[, k \in [y, y + h[ \verifies {ROUTE} == getEntiteAt(j, k) then
		//			getBonusVitesse(x, y, l, h) min= getRouteAt(j, k).bonusVitesse()
		//		else getBonusVitesse(x, y, l, h) min= 0
		// inv:	\forall x \in [0, largeur[, \forall y \in [0, hauteur[
		//		if getEntiteAt(x, y).size() > 0 &&
		//		if RIEN \in getEntiteAt(x, y) then getEntiteAt(x, y).size == 1
		for (int x = 0; x < super.getLargeur(); x++)
			for (int y = 0; y < super.getHauteur(); y++) {
				Set<EEntite> ents = getEntiteAt(x, y);
				if (ents.size() <= 0)
					throw new InvariantError("getEntiteAt(x, y).size() > 0");
				if (ents.contains(EEntite.RIEN) && ents.size() > 1)
					throw new InvariantError("if RIEN \\in getEntiteAt(x, y) then getEntiteAt(x, y).size == 1");
			}	
	}
	
	public Set<EEntite> getEntiteAt(int x, int y) {
		// pre: 0 <= x < largeur() && 0 <= y < hauteur()
		if (x < 0 || x > super.getLargeur())
			throw new PreconditionError("0 <= x < largeur()");
		if (y < 0 || y > super.getHauteur())
			throw new PreconditionError("0 <= y < hauteur()");
		
		return super.getEntiteAt(x, y);
	}
	
	public boolean estFranchissable(int x, int y, int l, int h) {
		// pre: x >= 0 && y >= 0 && x + l <= largeur() && y + h <= hauteur()
		if (x < 0)
			throw new PreconditionError("x >= 0");
		if (y < 0)
			throw new PreconditionError("y >= 0");
		if (x + l > super.getLargeur())
			throw new PreconditionError("x + l <= largeur()");
		if (y + h > super.getHauteur())
			throw new PreconditionError("y + h <= hauteur()");
		return super.estFranchissable(x, y, l, h);
	}
	
	public int getBonusVitesse(int x, int y, int l, int h) {
		// pre: x >= 0 && y >= 0 && x + l < largeur() && y + h < hauteur()
		if (x < 0)
			throw new PreconditionError("x >= 0");
		if (y < 0)
			throw new PreconditionError("y >= 0");
		if (x + l > super.getLargeur())
			throw new PreconditionError("x + l <= largeur()");
		if (y + h > super.getHauteur())
			throw new PreconditionError("y + h <= hauteur()");
		
		return super.getBonusVitesse(x, y, l, h);
	}
	
	public IRoute getRouteAt(int x, int y, int l, int h) {
		// pre: x >= 0 && y >= 0 && x + l < largeur() && y + h < hauteur()
		if (x < 0)
			throw new PreconditionError("x >= 0");
		if (y < 0)
			throw new PreconditionError("y >= 0");
		if (x + l > super.getLargeur())
			throw new PreconditionError("x + l <= largeur()");
		if (y + h > super.getHauteur())
			throw new PreconditionError("y + h <= hauteur()");
		
		return super.getRouteAt(x, y, l, h);
	}
	
	public ITerrain init(int largeur, int hauteur) {
		// pre: largeur >= 600 && hauteur >= 400
		if (largeur < 600)
			throw new PreconditionError("largeur >= 600");
		if (hauteur < 400)
			throw new PreconditionError("hauteur >= 400");

		super.init(largeur, hauteur);
		
		this.checkInvariants();
		
		// post: largeur() == largeur
		if (super.getLargeur() != largeur)
			throw new PostconditionError("largeur() == largeur");
		// post: hauteur() == hauteur
		if (super.getHauteur() != hauteur)
			throw new PostconditionError("hauteur() == hauteur");
		/**
			post: \forall HV \in getListeHotelVille():
					HV.posx() + HV.largeur() < largeur && 
					HV.posy() + HV.hauteur() < hauteur &&
					HV.orRestant() == 16 &&
					\forall HV.posx() <= x < HV.posx() + HV.largeur()
					\forall HV.posy() <= y < HV.posy() + HV.hauteur()
						HDV \in getEntiteAt(x, y)

		 */
		for (IHotelVille hv : super.getListeHotelVille()) {
			if (hv.getX() + hv.getLargeur() >= largeur)
				throw new PostconditionError("HV.posx() + HV.largeur() < largeur");
			if (hv.getY() + hv.getHauteur() >= hauteur)
				throw new PostconditionError("HV.posy() + HV.hauteur() < hauteur");
			if (hv.getOrRestant() != 16)
				throw new PostconditionError("HV.orRestant() == 16");
			for (int x = hv.getX(); x < hv.getX() + hv.getLargeur(); x++)
				for (int y = hv.getY(); y < hv.getY() + hv.getHauteur(); y++)
					if ( ! getEntiteAt(x, y).contains(EEntite.HDV))
						throw new PostconditionError("HDV \\in getEntiteAt(x, y)");
		}
		/**
			post: \forall Vill \in getListeVillageois():
			HDV := HDV \in getListeHotelVille() && HDV.etatAppartenance() == Vill.race()
				Vill.posx() + Vill.largeur() < largeur && 
				Vill.posy() + Vill.hauteur() < hauteur &&
				distance(Vill.posx(), Vill.posy(), HDV.posx(), HDV.posy()) <= 51 &&
				Vill.pointsDeVie() == 100 &&
				\forall Vill.posx() <= x < Vill.posx() + Vill.largeur()
				\forall Vill.posy() <= y < Vill.posy() + Vill.hauteur()
					VILLAGEOIS \in getEntiteAt(x, y)
		 */
		for (IVillageois vill : getListeVillageois()) {
			IHotelVille hdv = null;
			for (IHotelVille h : getListeHotelVille())
				if (h.getEtatAppartenance() == vill.getRace())
					hdv = h;
			if (vill.getX() + vill.getLargeur() >= largeur)
				throw new PostconditionError("Vill.posx() + Vill.largeur() < largeur");
			if (vill.getY() + vill.getHauteur() >= hauteur)
				throw new PostconditionError("Vill.posy() + Vill.hauteur() < hauteur");
			if (Utils.distance(vill.getX(), vill.getY(), hdv.getX(), hdv.getY()) > 51)
				throw new PostconditionError("distance(Vill.posx(), Vill.posy(), HDV.posx(), HDV.posy()) <= 51");
			if (vill.getPointsDeVie() != 100)
				throw new PostconditionError("Vill.pointsDeVie() == 100");
			for (int x = vill.getX(); x < vill.getX() + vill.getLargeur(); x++)
				for (int y = vill.getY(); y < vill.getY() + vill.getHauteur(); y++)
					if ( ! getEntiteAt(x, y).contains(EEntite.VILLAGEOIS))
						throw new PostconditionError("VILLAGEOIS \\in getEntiteAt(x, y)");
		}
		/**
			post: \forall Mi \in getListeMine():
			Mi.posx() + Mi.largeur() < largeur && 
			Mi.posy() + Mi.hauteur() < hauteur &&
			\forall Mi.posx() <= x < Mi.posx() + Mi.largeur()
			\forall Mi.posy() <= y < Mi.posy() + Mi.hauteur()
				MINE \in getEntiteAt(x, y)
		 */
		for (IMine mi : super.getListeMine()) {
			if (mi.getX() + mi.getLargeur() >= largeur)
				throw new PostconditionError("Mi.posx() + Mi.largeur() < largeur");
			if (mi.getY() + mi.getHauteur() >= hauteur)
				throw new PostconditionError("Mi.posy() + Mi.hauteur() < hauteur");
			for (int x = mi.getX(); x < mi.getX() + mi.getLargeur(); x++)
				for (int y = mi.getY(); y < mi.getY() + mi.getHauteur(); y++)
					if ( ! getEntiteAt(x, y).contains(EEntite.MINE))
						throw new PostconditionError("MINE \\in getEntiteAt(x, y)");
		}
		/**
			post: \forall Ro \in getListeRoute():
					Ro.posx() + Ro.largeur() < largeur && 
					Ro.posy() + Ro.hauteur() < hauteur &&
					\forall Ro.posx() <= x < Ro.posx() + Ro.largeur()
					\forall Ro.posy() <= y < Ro.posy() + Ro.hauteur()
						ROUTE \in getEntiteAt(x, y)		
		 */
		for (IRoute ro : super.getListeRoute()) {
			if (ro.getX() + ro.getLargeur() >= largeur)
				throw new PostconditionError("Ro.posx() + Ro.largeur() < largeur");
			if (ro.getY() + ro.getHauteur() >= hauteur)
				throw new PostconditionError("Ro.posy() + Ro.hauteur() < hauteur");
			for (int x = ro.getX(); x < ro.getX() + ro.getLargeur(); x++)
				for (int y = ro.getY(); y < ro.getY() + ro.getHauteur(); y++)
					if ( ! getEntiteAt(x, y).contains(EEntite.ROUTE))
						throw new PostconditionError("ROUTE \\in getEntiteAt(x, y)");
		}
		/**
			post: \forall Mu \in getListeMuraille():
					Mu.posx() + Mu.largeur() < largeur && 
					Mu.posy() + Mu.hauteur() < hauteur &&
					\forall Mu.posx() <= x < Mu.posx() + Mu.largeur()
					\forall Mu.posy() <= y < Mu.posy() + Mu.hauteur()
						MURAILLE \in getEntiteAt(x, y)
		 */
		for (IMuraille mu : super.getListeMuraille()) {
			if (mu.getX() + mu.getLargeur() >= largeur)
				throw new PostconditionError("Mu.posx() + Mu.largeur() < largeur");
			if (mu.getY() + mu.getHauteur() >= hauteur)
				throw new PostconditionError("Mu.posy() + Mu.hauteur() < hauteur");
			for (int x = mu.getX(); x < mu.getX() + mu.getLargeur(); x++)
				for (int y = mu.getY(); y < mu.getY() + mu.getHauteur(); y++)
					if ( ! getEntiteAt(x, y).contains(EEntite.MURAILLE))
						throw new PostconditionError("MURAILLE \\in getEntiteAt(x, y)");
		}
		
		return this;
	}
	
	public void setEntiteAt(EEntite ent, int x, int y, int l, int h) {
		this.checkInvariants();
		
		// pre: estFranchissable(x, y, l, h)
		if ( ! estFranchissable(x, y, l, h))
			throw new PreconditionError("estFranchissable(x, y, l, h");
		
		ArrayList<ArrayList<Set<EEntite>>> getEntite_pre = getEntite_pre(x, y, l, h);
		
		super.setEntiteAt(ent, x, y, l, h);
		
		this.checkInvariants();
		
		/**
			post: \forall j \in [x, x + l[, \forall k \in [y, y + h[
			if getEntiteAt(j, k)@pre == {RIEN} then
				getEntiteAt(j, k) == {ent}
			else
				getEntiteAt(j, k) == getEntiteAt(j, k)@pre \plus {ent}
		 */
		for (int j = x; j < x + l; j++) {
			for (int k = y; k < y + h; k++) {
				Set<EEntite> getEntjk_pre = getEntite_pre.get(j - x).get(k - y);
				Set<EEntite> getEntjk = getEntiteAt(j, k);
				if (getEntjk_pre.contains(EEntite.RIEN) && getEntjk_pre.size() == 1) {
					if ( ! (getEntjk.contains(ent) && getEntjk.size() == 1))
						throw new PostconditionError("getEntiteAt(j, k) == {ent}");
				}
				else
					if ( ! (getEntjk.containsAll(getEntjk_pre) && getEntjk.contains(ent) && getEntjk_pre.size() == getEntjk.size() + 1))
						throw new PostconditionError("getEntiteAt(j, k) == getEntiteAt(j, k)@pre \\plus {ent}");
			}
		}
	}
	
	public void moveVillageoisAt(int numV, int xn, int yn) {
		this.checkInvariants();
		
		/**
			pre: 0 <= numV < getListeVillageois().size() &&
			 	estFranchissable(xn, yn, getListeVillageois().get(numV).largeur(), getListeVillageois().get(numV).hauteur())
		*/
		if (numV < 0 || numV >= getListeVillageois().size())
			throw new PreconditionError("0 <= numV < getListeVillageois().size()");
		IVillageois vill = getListeVillageois().get(numV);
		if ( ! estFranchissable(xn, yn, vill.getLargeur(), vill.getHauteur()))
			throw new PreconditionError("estFranchissable(xn, yn, getListeVillageois().get(numV).largeur(), getListeVillageois().get(numV).hauteur())");
		
		int vill_x_pre = vill.getX();
		int vill_y_pre = vill.getY();
		ArrayList<ArrayList<Set<EEntite>>> getEntite_pre = getEntite_pre(xn, yn, vill.getLargeur(), vill.getHauteur());
		ArrayList<ArrayList<Set<EEntite>>> getEntite_pre2 = getEntite_pre(vill_x_pre, vill_y_pre, vill.getLargeur(), vill.getHauteur());
		
		super.moveVillageoisAt(numV, xn, yn);
		
		this.checkInvariants();
		
		/**
		post: Vill@pre :=  getListeVillageois()@pre.get(numV)
			  \forall x \in [xn, xn + Vill@pre.largeur()[,
			  \forall y \in [yn, yn + Vill@pre.hauteur()[,
					if getEntiteAt(x, y)@pre == {RIEN} then
						getEntiteAt(x, y) == {VILLAGEOIS}
					else
						getEntiteAt(x, y) == getEntiteAt(x, y)@pre \plus {VILLAGEOIS}
			  \forall x \in [Vill@pre.posx(), Vill@pre.posx() + Vill@pre.largeur()[,
			  \forall y \in [Vill@pre.posy(), Vill@pre.posy() + Vill@pre.hauteur()[,
			  		if x \not \in [xn, xn + Vill@pre.largeur()[ \or y \not \in [yn, yn + Vill@pre.hauteur()[ then
						if getEntiteAt(x, y)@pre == {VILLAGEOIS} then
							getEntiteAt(x, y) == {RIEN}
						else
							getEntiteAt(x, y) == getEntiteAt(x, y)@pre \minus {VILLAGEOIS}
		 */
		for (int x = xn; x < xn + vill.getLargeur(); x++)
			for (int y = yn; y < yn + vill.getHauteur(); y++) {
				Set<EEntite> getEntxy_pre = getEntite_pre.get(x - xn).get(y - yn);
				Set<EEntite> getEntxy = getEntiteAt(x, y);
				if (getEntxy_pre.contains(EEntite.RIEN) && getEntxy_pre.size() == 1) {
					if ( ! (getEntxy.contains(EEntite.VILLAGEOIS) && getEntxy.size() == 1))
						throw new PostconditionError("getEntiteAt(x, y) == {VILLAGEOIS}");
				}
				else
					if ( ! (getEntxy.containsAll(getEntxy_pre) && getEntxy.contains(EEntite.VILLAGEOIS) && getEntxy.size() == getEntite_pre.size() + 1))
						throw new PostconditionError("getEntiteAt(x, y) == getEntiteAt(x, y)@pre \\plus {VILLAGEOIS}");
			}
		
		for (int x = vill_x_pre; x < vill_x_pre + vill.getLargeur(); x++) {
			for (int y = vill_y_pre; y < vill_y_pre + vill.getHauteur(); y++) {
				if (x >= xn && x < xn + vill.getLargeur() && y >= yn && y < yn + vill.getHauteur())
					continue;
				
				Set<EEntite> getEntxy_pre = getEntite_pre2.get(x - vill_x_pre).get(y - vill_y_pre);
				Set<EEntite> getEntxy = getEntiteAt(x, y);
				if (getEntxy_pre.contains(EEntite.VILLAGEOIS) && getEntxy_pre.size() == 1) {
					if ( ! (getEntxy.contains(EEntite.RIEN) && getEntxy.size() == 1))
						throw new PostconditionError("getEntiteAt(x, y) == {RIEN}");
				}
				else
					if ( ! (getEntxy_pre.containsAll(getEntxy) && ! getEntxy.contains(EEntite.VILLAGEOIS) && getEntxy.size() == getEntite_pre.size() - 1))
						throw new PostconditionError("getEntiteAt(x, y) == getEntiteAt(x, y)@pre \\minus {VILLAGEOIS}");
			}
		}
	}
	
	public void removeEntiteAt(EEntite ent, int x, int y, int l, int h) {
		this.checkInvariants();
		
		/**
			pre: x >= 0 && y >= 0 && x + l < largeur() && y + h < hauteur() &&
				\forall j \in [x, x + l[, \forall k \in [y, y + h[
					ent \in getEntiteAt(j, k)
		 */
		if (x < 0)
			throw new PreconditionError("x >= 0");
		if (y < 0)
			throw new PreconditionError("y >= 0");
		if (x + l >= super.getLargeur())
			throw new PreconditionError("x + l < largeur()");
		if (y + h >= super.getHauteur())
			throw new PreconditionError("y + h < hauteur()");
		for (int j = x; j < x + l; j++)
			for (int k = y; k < y + h; k++)
				if ( ! getEntiteAt(j, k).contains(ent))
					throw new PreconditionError("ent \\in getEntiteAt(j, k)");
		
		ArrayList<ArrayList<Set<EEntite>>> getEntite_pre = getEntite_pre(x, y, l, h);
		super.removeEntiteAt(ent, x, y, l, h);
		
		this.checkInvariants();
		
		/**
			post: \forall j \in [x, x + l[, \forall k \in [y, y + h[,
					if getEntiteAt(j, k)@pre.size() == 1 then
						getEntiteAt(j, k) == {RIEN}
					else 
						getEntiteAt(j, k) == getEntiteAt(j, k)@pre \minus {ent}
		 */
		for (int j = x; j < x + l; j++)
			for (int k = y; k < y + h; k++) {
				Set<EEntite> getEntjk_pre = getEntite_pre.get(j - x).get(k - y);
				Set<EEntite> getEntjk = getEntiteAt(j, k);
				if (getEntite_pre.size() == 1) {
					if ( ! (getEntjk.contains(EEntite.RIEN) && getEntjk.size() == 1))
						throw new PostconditionError("getEntiteAt(j, k) == {RIEN}");
				}
				else
					if ( ! (getEntjk_pre.containsAll(getEntjk) && ! getEntjk.contains(ent) && getEntjk.size() == getEntite_pre.size() - 1))
						throw new PostconditionError("getEntiteAt(j, k) == getEntiteAt(j, k)@pre \\minus {ent}");
			}
	}
	
	public void reinsertVillageois(int numV) {
		this.checkInvariants();
		
		/**
			pre: 0 <= numV < getListeVillageois().size() && 
				MINE \in getEntiteAt(getListeVillageois().get(numV).posx(),
				 					 getListeVillageois().get(numV).posy())
		 */
		if (numV < 0 || numV >= getListeVillageois().size())
			throw new PreconditionError("0 <= numV < getListeVillageois().size()");
		IVillageois vill = getListeVillageois().get(numV);
		if ( ! (getEntiteAt(vill.getX(), vill.getY()).contains(EEntite.MINE)))
			throw new PreconditionError("MINE \\in getEntiteAt(getListeVillageois().get(numV).posx(), getListeVillageois().get(numV).posy())");
			
		super.reinsertVillageois(numV);
		
		this.checkInvariants();
		
		/**
			post: Vill := getListeVillageois().get(numV)
			\forall x \in [Vill.posx(), Vill.posx() + Vill.largeur()[,
			\forall y \in [Vill.posy(), Vill.posy() + Vill.hauteur()[,
				VILLAGEOIS \in getEntiteAt(x, y)
		 */
		for (int x = vill.getX(); x < vill.getX() + vill.getLargeur(); x++)
			for (int y = vill.getY(); y < vill.getY() + vill.getHauteur(); y++)
				if ( ! (getEntiteAt(x, y).contains(EEntite.VILLAGEOIS)))
					throw new PostconditionError("VILLAGEOIS \\in getEntiteAt(x, y)");
	}
	
	private ArrayList<ArrayList<Set<EEntite>>> getEntite_pre(int x, int y, int l, int h) {
		ArrayList<ArrayList<Set<EEntite>>> getEntite_pre = new ArrayList<ArrayList<Set<EEntite>>>(); 
		for (int j = x; j < x + l; j++) {
			ArrayList<Set<EEntite>> jlist = new ArrayList<Set<EEntite>>();
			for (int k = y; k < y + h; k++)
				jlist.add(getEntiteAt(j, k));
			getEntite_pre.add(jlist);
		}
		return getEntite_pre;
	}
}
