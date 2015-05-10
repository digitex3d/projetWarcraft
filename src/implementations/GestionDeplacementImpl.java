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
		
		// init les listes
		this.cheminX = new ArrayList<Integer>();
		this.cheminY = new ArrayList<Integer>();
		this.getPointArrive = new ArrayList<Integer>();
					
		int cosO= (int) Math.cos( Math.toRadians(angle)); 
		int sinO= (int) Math.sin( Math.toRadians(angle));
		// Position finale sans bonus
		int destX =   (int) (vill.getX() + (vill.getVitesse() * cosO)) ;
		int destY =   (int) (vill.getY() + (vill.getVitesse() * -sinO));
			
		int currentX = vill.getX();
		int currentY = vill.getY();
		
		// Construction liste du chemin
		while( true ){
			
			if( currentX > destX )
				currentX--;
			else if( currentX < destX )
				currentX++;	
			
			if( currentY > destY )
				currentY--;
			else if( currentY < destY )
				currentY++;	
			
			if( currentX <= this.terr.getLargeur() && currentX >= 0  )
				this.cheminX.add(currentX);
			if( currentY <= this.terr.getHauteur() && currentY >= 0  )
				this.cheminY.add(currentY);
			
			if( currentX == destX && currentY== destY) break;
		}
		
		
		int bonus = 0;
		
		for (int i = 0; i < getCheminX().size(); i++) {
			bonus += this.terr.getBonusVitesse(	getCheminX().get(i),
												getCheminY().get(i),
												vill.getLargeur(),
												vill.getHauteur());
		}
		
		// Position finale avec bonus
		
		
		destX =   (int) (vill.getX() + (bonus + vill.getVitesse()) * cosO);
		destY =   (int) (vill.getY() + (bonus + vill.getVitesse()) * -sinO);
			
		this.cheminX = new ArrayList<Integer>();
		this.cheminY = new ArrayList<Integer>();
		
		currentX = vill.getX();
		currentY = vill.getY();
		
		// Construction liste du chemin avec bonus
		while( true ){
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

			if( currentX == destX && currentY== destY) break;
		}
		
	
		
		
		// Détection premier obstacle
		this.firstObstacle = -1;
		
		for (int i = 0; i < cheminX.size(); i++) 
			if( !this.terr.estFranchissable(	cheminX.get(i), 
												cheminY.get(i), 
												vill.getLargeur(),
												vill.getHauteur())){
				this.firstObstacle = i;
				if(i == 0){
					this.getPointArrive.add(vill.getX()	);
					this.getPointArrive.add(vill.getY() );
				}else{
					this.getPointArrive.add(cheminX.get(i-1));
					this.getPointArrive.add(cheminY.get(i-1));
				}
			}
		
		// Pas d'obstacles, position max atteignable
		if( this.firstObstacle == -1 ){
			this.getPointArrive.add( getCheminX().get(getCheminX().size()-1));
			this.getPointArrive.add( getCheminY().get(getCheminY().size()-1));
		}
		
		this.estCalcChemin = true;

	}

	@Override
	public void bindTerrain(ITerrain terr) {
		this.terr = terr;
	}

}
