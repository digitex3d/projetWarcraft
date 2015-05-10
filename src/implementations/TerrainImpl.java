package implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enums.EEntite;
import services.IHotelVille;
import services.IMine;
import services.IMuraille;
import services.IRoute;
import services.ITerrain;
import services.IVillageois;

public class TerrainImpl implements ITerrain {
	private int largeur;
	private int hauteur;
	private List<IMuraille> listeMuraille;
	private List<IRoute> listeRoute;
	private List<IMine> listeMine;
	private List<IVillageois> listeVillageois;
	private List<IHotelVille> listeHotelVille;
	private List<List<Set<EEntite>>> terrain;
	
	@Override
	public int getLargeur() {
		return largeur;
	}

	@Override
	public int getHauteur() {
		return hauteur;
	}

	@Override
	public List<IMuraille> getListeMuraille() {
		return listeMuraille;
	}

	@Override
	public List<IRoute> getListeRoute() {
		return listeRoute;
	}

	@Override
	public List<IVillageois> getListeVillageois() {
		return listeVillageois;
	}

	@Override
	public List<IMine> getListeMine() {
		return listeMine;
	}

	@Override
	public List<IHotelVille> getListeHotelVille() {
		return listeHotelVille;
	}

	@Override
	public boolean estFranchissable(int x, int y, int l, int h) {
		for (int j = x; j < x + l; j++)
			for (int k = y; k < y + h; k++) {
				Set<EEntite> atjk = getEntiteAt(j, k);
				if (atjk.size() > 1 || ! (atjk.contains(EEntite.RIEN) || atjk.contains(EEntite.ROUTE)))
					return false;
			}
		return true;
	}

	@Override
	public Set<EEntite> getEntiteAt(int x, int y) {
		return terrain.get(x).get(y);
	}

	@Override
	public int getBonusVitesse(int x, int y, int l, int h) {
		int bv = 0;
		for (int j = x; j < x + l; j++)
			for (int k = y; k < y + h; k++)
				if (getEntiteAt(j, k).contains(EEntite.ROUTE)) {
					IRoute route = getRouteAt(x, y, l, h);
					if (route != null)
						bv = route.getBonusVitesse();
					break;
				}
		return bv;
	}
	
	@Override
	public IRoute getRouteAt(int x, int y, int l, int h) {
		for (IRoute  r: this.getListeRoute()){
			if ( x < x + r.getLargeur() &&  x > r.getX() )
				if ( y < y + r.getHauteur() &&  y > r.getY() )
					return r;
		}
		return null;
		
	}
	

	@Override
	public ITerrain init(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		if (listeHotelVille == null)
			throw new Error("Les hdv ne sont pas bindé!");
		if (listeMine == null)
			throw new Error("Les mines ne sont pas bindé!");
		if (listeMuraille == null)
			throw new Error("Les murailles ne sont pas bindé!");
		if (listeRoute == null)
			throw new Error("Les routes ne sont pas bindé!");
		if (listeVillageois == null)
			throw new Error("Les villageois ne sont pas bindé!");
		
		terrain = new ArrayList<List<Set<EEntite>>>();

		for (int x = 0; x < largeur; x++) {
			terrain.add(new ArrayList<Set<EEntite>>());
			for (int y = 0; y < hauteur; y++) {
				Set<EEntite> s = new HashSet<EEntite>();
				s.add(EEntite.RIEN);
				terrain.get(x).add(s);
			}
		}
 		
		for (IHotelVille hdv : listeHotelVille)
			setEntiteAt(EEntite.HDV, hdv.getX(), hdv.getY(), hdv.getLargeur(), hdv.getHauteur());
		for (IMine mine : listeMine)
			setEntiteAt(EEntite.MINE, mine.getX(), mine.getY(), mine.getLargeur(), mine.getHauteur());
		for (IMuraille mur : listeMuraille)
			setEntiteAt(EEntite.MURAILLE, mur.getX(), mur.getY(), mur.getLargeur(), mur.getHauteur());
		for (IRoute route : listeRoute)
			setEntiteAt(EEntite.ROUTE, route.getX(), route.getY(), route.getLargeur(), route.getHauteur());
		for (IVillageois vill : listeVillageois)
			setEntiteAt(EEntite.VILLAGEOIS, vill.getX(), vill.getY(), vill.getLargeur(), vill.getHauteur());
		
		return this;
	}

	@Override
	public void setEntiteAt(EEntite ent, int x, int y, int l, int h) {
		for (int j = x; j < x + l; j++)
			for (int k = y; k < y + h; k++) {
				Set<EEntite> jk = terrain.get(j).get(k);
				jk.remove(EEntite.RIEN);
				jk.add(ent);
			}
	}

	@Override
	public void removeEntiteAt(EEntite ent, int x, int y, int l, int h) {
		for (int j = x; j < x + l; j++)
			for (int k = y; k < y + h; k++) {
				Set<EEntite> jk = terrain.get(j).get(k);
				jk.remove(ent);
				if (jk.size() == 0)
					jk.add(EEntite.RIEN);
			}
	}

	@Override
	public void reinsertVillageois(int numV) {
		IVillageois vill = listeVillageois.get(numV);
		int side = 1;
		
		while (true) {
			ArrayList<Integer[]> ring = getRing(vill.getX(), vill.getY(), side);
			int skipped = 0;
			for (Integer[] coord : ring) {
				if (coord[0] < 0 || coord[1] < 0 || coord[0] + vill.getLargeur() > largeur || coord[1] + vill.getHauteur() > hauteur) {
					skipped++;
					continue;
				}
				
				if (estFranchissable(coord[0], coord[1], vill.getLargeur(), vill.getHauteur())) {
					setEntiteAt(EEntite.VILLAGEOIS, coord[0], coord[1], vill.getLargeur(), vill.getHauteur());
					vill.setXY(coord[0], coord[1]);
					return;
				}
			}
			
			side++;
			if (skipped == ring.size())
				return;
		}
	}

	public ArrayList<Integer[]> getRing(int x, int y, int side) {
		ArrayList<Integer[]> ring = new ArrayList<>();
		for (int j = x - side; j <= x + side; j++)
			for (int k = y - side; k <= y + side; k++)
				if (j == x - side || j == x + side || k == y - side || k == y + side) {
					Integer coord[] = {j, k};
					ring.add(coord);
				}
		return ring;
	}

	@Override
	public void bindHDV(List<IHotelVille> hdvs) {
		this.listeHotelVille = hdvs;
	}

	@Override
	public void bindMine(List<IMine> mines) {
		this.listeMine = mines;
	}

	@Override
	public void bindMur(List<IMuraille> murailles) {
		this.listeMuraille = murailles;
	}

	@Override
	public void bindRoute(List<IRoute> routes) {
		this.listeRoute = routes;
	}

	@Override
	public void bindVill(List<IVillageois> villageois) {
		this.listeVillageois = villageois;
	}

	
	
}
