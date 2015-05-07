package decorators;

import java.util.List;
import java.util.Set;

import enums.EEntite;
import services.IHotelVille;
import services.IMine;
import services.IMuraille;
import services.IRoute;
import services.ITerrain;
import services.IVillageois;

public class TerrainDecorator implements ITerrain {
	private ITerrain delegate;
	
	public TerrainDecorator(ITerrain delegate) {
		super();
		this.delegate = delegate;
	}
	
	@Override
	public int getLargeur() {
		return delegate.getLargeur();
	}

	@Override
	public int getHauteur() {
		return delegate.getHauteur();
	}

	@Override
	public List<IMuraille> getListeMuraille() {
		return delegate.getListeMuraille();
	}

	@Override
	public List<IRoute> getListeRoute() {
		return delegate.getListeRoute();
	}

	@Override
	public List<IVillageois> getListeVillageois() {
		return delegate.getListeVillageois();
	}

	@Override
	public List<IMine> getListeMine() {
		return delegate.getListeMine();
	}

	@Override
	public List<IHotelVille> getListeHotelVille() {
		return delegate.getListeHotelVille();
	}

	@Override
	public boolean estFranchissable(int x, int y, int l, int h) {
		return delegate.estFranchissable(x, y, l, h);
	}

	@Override
	public Set<EEntite> getEntiteAt(int x, int y) {
		return delegate.getEntiteAt(x, y);
	}

	@Override
	public int getBonusVitesse(int x, int y, int l, int h) {
		return delegate.getBonusVitesse(x, y, l, h);
	}

	@Override
	public IRoute getRouteAt(int x, int y, int l, int h) {
		return delegate.getRouteAt(x, y, l, h);
	}

	@Override
	public ITerrain init(int largeur, int hauteur) {
		return delegate.init(largeur, hauteur);
	}

	@Override
	public void setEntiteAt(EEntite ent, int x, int y, int l, int h) {
		delegate.setEntiteAt(ent, x, y, l, h);
	}

	@Override
	public void moveVillageoisAt(int numV, int xn, int yn) {
		delegate.moveVillageoisAt(numV, xn, yn);
	}

	@Override
	public void removeEntiteAt(EEntite ent, int x, int y, int l, int h) {
		delegate.removeEntiteAt(ent, x, y, l, h);
	}

	@Override
	public void reinsertVillageois(int numV) {
		delegate.reinsertVillageois(numV);
	}

}
