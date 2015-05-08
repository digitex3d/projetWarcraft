package implementations;

import java.util.ArrayList;

import services.IGestionDeplacement;
import services.ITerrain;
import services.IVillageois;

public class GestionDeplacementImpl implements IGestionDeplacement {
	private ITerrain terr;
	private boolean estCalcChemin;
	private ArrayList<Integer> cheminX;
	private ArrayList<Integer> cheminY;
	private ArrayList<Integer> getPointArrive;
	private int firstObstacle;
	
	@Override
	public ITerrain getTerr() {
		return this.terr;
	}

	@Override
	public boolean estCalcChemin() {
		return this.estCalcChemin;
	}

	@Override
	public ArrayList<Integer> getCheminX() {
		return this.cheminX;
	}

	@Override
	public ArrayList<Integer> getCheminY() {
		return this.cheminY;
	}
	
	@Override
	public ArrayList<Integer> getPointArrivee() {
		return this.getPointArrive;
	}

	@Override
	public int getFirstObstacle() {
		return this.firstObstacle;
	}

	@Override
	public IGestionDeplacement init() {
		this.estCalcChemin = false;
		
		if (terr == null)
			throw new Error("Le terrain n'est pas bindé");
		
		return this;
	}


	@Override
	public void calcChemin(int numVill, int angle) {
		IVillageois vill = this.terr.getListeVillageois().get(numVill);
		
		this.estCalcChemin = true;
		int bonus = 0;
		
		for (int i = 0; i < getCheminX().size(); i++) {
			bonus += this.terr.getBonusVitesse(	getCheminX().get(i),
												getCheminY().get(i),
												vill.getLargeur(),
												vill.getHauteur());
		}
		
		// Position finale max
		int destX = (int) (vill.getX() + (( bonus + vill.getVitesse() ) * Math.cos( angle )));
		int destY = (int) (vill.getY() + (( bonus + vill.getVitesse() ) * -Math.sin( angle )));
			
		int currentX = vill.getX();
		int currentY = vill.getY();
		
		// Construction liste du chemin
		while( currentX != destX && currentY != destX ){
			if( currentX > destX )
				currentX--;
			else if( currentX < destX )
				currentX++;	
			
			if( currentY > destY )
				currentY--;
			else if( currentY < destY )
				currentY++;	
			
			this.cheminX.add(currentX);
			this.cheminY.add(currentY);
			
		
		
		}
		
		
		// Détection premier obstacle
		this.firstObstacle = -1;
		
		for (int i = 0; i < cheminX.size(); i++) 
			if( !this.terr.estFranchissable(	cheminX.get(i), 
												cheminY.get(i), 
												vill.getLargeur(),
												vill.getHauteur())){
				this.firstObstacle = i;
				this.getPointArrive.add(cheminX.get(i-1),
						cheminY.get(i-1));
					
			}
		
		// Pas d'obstacles, position max atteignable
		if( this.firstObstacle == -1 )
			this.getPointArrive.add(cheminX.get( cheminX.size() - 1),
					cheminY.get( cheminY.size() - 1));
		// Obstacle au premier pas, la position ne change pas
		else if ( this.firstObstacle == 0  )
			this.getPointArrive.add(vill.getX(),vill.getY()	);

	}

	@Override
	public void bindTerrain(ITerrain terr) {
		this.terr = terr;
	}

}
