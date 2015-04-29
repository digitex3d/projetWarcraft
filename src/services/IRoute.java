package services;

public interface IRoute extends IEntite {
	public IRoute init(int x, int y, int largeur, int hauteur, int mult);
	int getMult();

}
