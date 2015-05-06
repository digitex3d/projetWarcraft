package implementations;

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

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<IMuraille> getListeMuraille() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRoute> getListeRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IVillageois> getListeVillageois() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMine> getListeMine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IHotelVille> getListeHotelVille() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean estFranchissable(int x, int y, int l, int h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<EEntite> getEntiteAt(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBonusVitesse(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IRoute getRouteAt(int x, int y, int l, int h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITerrain init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntiteAt(EEntite ent, int x, int y, int l, int h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveVillageoisAt(int numV, int xn, int yn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEntiteAt(EEntite ent, int x, int y, int l, int h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reinsertVillageois(int numV) {
		// TODO Auto-generated method stub

	}

}
