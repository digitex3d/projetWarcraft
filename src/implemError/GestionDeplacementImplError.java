package implemError;

import java.util.ArrayList;

import services.IGestionDeplacement;
import services.ITerrain;
import services.IVillageois;

public class GestionDeplacementImplError implements IGestionDeplacement {
	private ITerrain terr;
	private IVillageois vill;
	private int angle;
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
	public IVillageois getVill() {
		return this.vill;
	}

	@Override
	public int getAngle() {
		return this.angle+1; // BUG 1
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
		return this.cheminX; // BUG 2
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
	public IGestionDeplacement init(int angle) {
		this.estCalcChemin = false;
		this.angle = angle;
		
		if (vill == null)
			throw new Error("Le villageois n'est pas bindé");
		if (terr == null)
			throw new Error("Le terrain n'est pas bindé");

		return this;
	}


	@Override
	public void calcChemin() {
		this.estCalcChemin = true;
		int bonus = 2; // BUG 4
		
		for (int i = 0; i < getCheminX().size(); i++) {
			bonus += this.terr.getBonusVitesse(getCheminX().get(i), getCheminX().get(i),2,3 ); //BUG
		}
		
		// Position finale max
		int destX = (int) (vill.getX() + (( bonus + vill.getVitesse() ) * Math.cos( angle )));
		int destY = (int) (vill.getY() + (( bonus + vill.getVitesse() ) * Math.sin( angle ))); // BUG 5
			
		int currentX = vill.getX();
		int currentY = vill.getY();
		
		// Construction liste du chemin
		while( currentX != destX && currentY != destX ){
			if( currentX > destX )
				currentX--;
			else if( currentX < destX )
				currentY++;				// BUG 6
			
			if( currentY > destY )
				currentY--;
			else if( currentY < destY )
				currentY++;	
			
			this.cheminX.add(currentX);
			// BUG 7
			
		
		
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
		if( this.firstObstacle == -1 ){ // BUG 7
			this.getPointArrive.add(cheminX.get( cheminX.size() - 1),
					cheminY.get( cheminY.size() - 1));
			// Obstacle au premier pas, la position ne change pas
			if ( this.firstObstacle == 0  )
				this.getPointArrive.add(vill.getX(),vill.getY()	);
		}

	}

	@Override
	public void bind(IVillageois vill) {
		this.vill = vill;
	}

	@Override
	public void bindTerrain(ITerrain terr) {
		this.terr = terr;
	}
}
