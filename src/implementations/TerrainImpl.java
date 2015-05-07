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
	private ArrayList<IMuraille> listeMuraille;
	private ArrayList<IRoute> listeRoute;
	private ArrayList<IMine> listeMine;
	private ArrayList<IVillageois> listeVillageois;
	private ArrayList<IHotelVille> listeHotelVille;
	private ArrayList<ArrayList<Set<EEntite>>> terrain;
	
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
					bv = getRouteAt(x, y, l, h).getBonusVitesse();
					break;
				}
		return bv;
	}

	@Override
	public IRoute getRouteAt(int x, int y, int l, int h) {
		for (IRoute route : listeRoute)
			if (route.getX() >= x && route.getX() < x + l)
				if (route.getY() >= y && route.getY() < y + h)
					return route;
		return null;
	}

	@Override
	public ITerrain init(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		terrain = new ArrayList<ArrayList<Set<EEntite>>>();
		for (int x = 0; x < largeur; x++) {
			terrain.add(new ArrayList<Set<EEntite>>());
			for (int y = 0; y < hauteur; y++) {
				Set<EEntite> s = new HashSet<EEntite>();
				s.add(EEntite.RIEN);
				terrain.get(x).add(s);
			}
		}
				
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
	public void moveVillageoisAt(int numV, int xn, int yn) {
		IVillageois vill = listeVillageois.get(numV);
		setEntiteAt(EEntite.VILLAGEOIS, xn, yn, vill.getLargeur(), vill.getHauteur());
		for (int j = vill.getX(); j < vill.getX() + vill.getLargeur(); j++)
			for (int k = vill.getY(); k < vill.getY() + vill.getHauteur(); k++) {
				if (j >= xn && j < xn + vill.getLargeur())
					if (k >= yn && k < yn + vill.getHauteur())
						continue;
				Set<EEntite> jk = terrain.get(j).get(k);
				jk.remove(EEntite.VILLAGEOIS);
				if (jk.size() == 0)
					jk.add(EEntite.RIEN);
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
		boolean placed = false;
		int side = 1;
		
		while ( ! placed) {
			ArrayList<Integer[]> ring = getRing(vill.getX(), vill.getY(), side);
			int skipped = 0;
			for (Integer[] coord : ring) {
				if (coord[0] < 0 || coord[1] < 0 || coord[0] + vill.getLargeur() >= largeur || coord[1] + vill.getHauteur() >= hauteur) {
					skipped++;
					continue;
				}
				
				if (estFranchissable(coord[0], coord[1], vill.getLargeur(), vill.getHauteur())) {
					setEntiteAt(EEntite.VILLAGEOIS, coord[0], coord[1], vill.getLargeur(), vill.getHauteur());
					vill.setXY(coord[0], coord[1]);
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
}
