package services;

public interface IRoute extends IEntite {
	IRoute init(int x, int y, int largeur, int hauteur, int mult);
	int getMult();

}
