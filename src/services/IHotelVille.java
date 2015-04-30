package services;

public interface IHotelVille extends IEntite{
	int getOrRestant();
	void depot(int s);
	IHotelVille init(int x, int y, int largeur, int hauteur, int or);
	
}
