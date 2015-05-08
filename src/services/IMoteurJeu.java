package services;

import require.IRequireTerrain;
import enums.ECommande;
import enums.EResultat;

public interface IMoteurJeu extends 
	IRequireTerrain{
	/* ########### Observators ########### */	
	public int getMaxPasJeu();
	public ITerrain getTerrain();
	public int getPasJeuCourant();
	public boolean estFini();
	// pre: estFini()
	public EResultat resultatFinal();
	
    // pre: 0 <= vill < terrain().getListeVillageois().size() 
	public IVillageois getVillageois(int vill);
	
    // pre: 0 <= mi < terrain().getListeMine().size() 	
	public IMine getMine(int mi);

    // pre: 0 <= mu < terrain().getListeMuraille().size() 	
	public IMuraille getMuraille(int mu);
	
    // pre: 0 <= hdv < terrain().getListeMine().size() 	
	public IHotelVille getHotelVille(int hdv);
	
	// pre: ! getVillageois(vilNum).estMort() && 0 <= minNum < terrain().getListeMine().size()
	public boolean peutEntrer(int vilNum, int minNum);
	
	// pre: ! getVillageois(vilNum).estMort() && 0 <= hdv < terrain().getListeMine().size()
	public boolean peutEntrerHotelVille(int vilNum, int hdv);
	
	// pre: ! getVillageois(vilNum).estMort() && ! getMuraille(mur).estDetruite()
    public boolean peutTaperMuraille(int vilNum, int mur);
    
	/* ########### Constructors ########### */	
	
    /**
    	pre: maxPas > 0
    	post: terrain() == terrain
    	post: maxPasJeu() == maxPas
    	post: pasJeuCourant() == 0
     */
	IMoteurJeu init(ITerrain terrain, int maxPas);
		

	/* ########### Operators ########### */
	
	/**
		pre: ! getVillageois(vilNum).estMort() &&
		 	 ! getVillageois(vilNum).estOccupe() && ! estFini() &&
		 	 if command == DEPLACER then
		 	 	0 <= arg <= 360
		 	 if command == ENTRERMINE then
		 	 	peutEntrer(vilNum, arg)
		 	 if command == ENTRERHOTELVILLE then
		 	 	peutEntrerHotelVille(vilNum, arg)
		 	 if command == TAPERMURAILLE then
		 	 	peutTaperMuraille(vilNum, arg)

		 post: pasJeuCourant() == pasJeuCourant@pre + 1
		 post: \forall x \in [0, terrain().getListeVillageois().size()[, 
					if getVillageois(x)@pre.estOccupe() then
						getVillageois(x) == getVillageois(x)@pre.decrCorvee())
					if getVillageois(x)@pre.corvee() == 1 then
					 	terrain() == terrain()@pre.reinsertVillageois(x)
		 post: \forall x \in [0, terrain().getListeMine().size()[, 
		 			if ! getMine(x)@pre.estAbandonee() then
		 				if command != ENTRERMINE || x != arg then
		 					getMine(x) == getMine(x)@pre.abandoned()
		 					
		 Soit pArrivee := init(terrain(), getVillageois(vilNum)@pre, arg).calcChemin().getPointArrivee()
		 Soit Villpre := getVillageois(vilNum)@pre
		 if command == DEPLACER then
		 	getVillageois(vilNum) == Villpre.setXY(pArrivee.get(0), pArrivee.get(1)) && 
			terrain() = terrain()@pre.moveVillageoisAt(vilNum, pArrivee.get(0), pArrivee.get(1))
		 if command == ENTRERHOTELVILLE then
		 	getHDV(arg) == getHDV(arg)@pre.depot(Villpre.quantiteOr()) && 
			getVillageois(vilNum) == Villpre.dechargeOr(Villpre.quantiteOr())
		 if command == ENTRERMINE then
		 	getMine(arg) == getMine(arg)@pre.accueil().retrait() &&
			getVillageois(vilNum) == Villpre.setCorvee(16).chargerOr(1) &&
			terrain() == terrain()@pre.removeEntiteAt(EEntite.VILLAGEOIS, Villpre.posx(), Villpre.posy(), Villpre.largeur(), Villpre.hauteur()) &&
			getVillageois(vilNum).posx() = getMine(arg).posx() && 
			getVillageois(vilNum).posy() = getMine(arg).posy()
		 Soit Mur := getMuraille(arg)@pre
		 if command == TAPERMURAILLE then
		 	getMuraille(arg) == Mur.retrait(Villpre.force()) &&
			if getMuraille(arg).estDetruite() then
				terrain() == terrain()@pre.removeEntiteAt(EEntite.MURAILLE, Mur.posx(), Mur.posy(), Mur.largeur(), Mur.hauteur())
	 */
	void pasJeu(ECommande command, int vilNum, int arg);
	
	/* ########### Invariants ########### */
	// inv: 0 <= pasJeuCourant() <= maxPasJeu()
	// inv: estFini() min= \exist x \verify 0 <= x <= terrain().getListeHotelVille().size() &&
	//						getHDV(x).orRestant() >= 1664 || pasJeuCourant() == maxPasJeu()
	// inv: if \exist x \verify 0 <= x <= terrain().getListeHotelVille().size() && getHDV(x).orRestant() >= 1664 then
	//			if getHDV(x).etatAppartenance() == ORC then resultatFinal() min= ORC_GAGNE
	//			if getHDV(x).etatAppartenance() == HUMAN then resultatFinal() min= HUMAN_GAGNE
	//		else resultatFinal() min= NUL
	
	// inv: getVillageois(vill) min= terrain().getListeVillageois().get(vill)
	// inv: getMine(mi) min= terrain().getListeMine().get(mi)
	// inv: getMuraille(mu) min= terrain().getListeMuraille().get(mu)
	// inv: getHDV(hdv) min= terrain().getListeHotelVille().get(hdv)
	
	// vilposX := getVillageois(vill).posx()
	// vilposY := getVillageois(vill).posy()
	// mineCenterX := (getMine(mi).posx() + getMine(M, mi).largeur()) / 2
	// mineCenterY := (getMine(mi).posy() + getMine(M, mi).hauteur()) / 2
	// hvCenterX := (getHDV(hdv).posx() + getHDV(M, hdv).largeur()) / 2
	// hvCenterY := (getHDV(hdv).posy() + getHDV(M, hdv).hauteur()) / 2
	// murCenterX := (getMuraille(mu).posx() + getMuraille(mu).largeur()) / 2
	// murCenterY := (getMuraille(mu).posy() + getMuraille(mu).hauteur()) / 2
	
	// inv: peutEntrer(vill, mi) min= distance(vilposX, vilposY, mineCenterX, mineCenterY) <= 51 && ! getMine(mi).estLaminee()
	// inv: peutEntrerHotelVille(vill, hdv) min= distance(vilposX, vilposY, hvCenterX,  hvCenterY)) <= 51 && getVillageois(vill).race() == getHDV(hdv).etatAppartenance()
	// inv: peutTaperMuraille(vill,mu) min= distance(vilposX, vilposY, murCenterX, murCenterY) ≤ 51
}
