package contracts;

import services.IMine;
import decorators.MineDecorator;
import enums.ERace;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class MineContract extends MineDecorator {

	public MineContract(IMine delegate) {
		super(delegate);
	}
	
	public void checkInvariants(){
		// inv: estLaminee() min= orRestant() <= 0
		if (super.estLaminee() && super.getOrRestant() > 0)
			throw new InvariantError("estLaminee() min= orRestant() <= 0");

		// inv: estAbandonnee() min= abandonCompteur == 51
		if (super.estAbandonne() && super.getAbandonCompteur() != 51)
			throw new InvariantError("estAbandonnee() min= abandonCompteur == 51");

		// inv: 0 <= abandonCompteur() <= 51
		if (super.getAbandonCompteur() > 51 || super.getAbandonCompteur() < 0)
			throw new InvariantError("0 <= abandonCompteur() <= 51");
	}
	
	public IMine init(int x, int y, int l, int h){
		/** 
		 * pre l % 2 = 1 && h % 2 = 1 && x > 0 && y > 0
		 */
		if (l % 2 != 1) 
			throw new PreconditionError("l % 2 = 1");
		if (h % 2 != 1) 
			throw new PreconditionError("h % 2 = 1");

		super.init(x, y, l, h);

		this.checkInvariants();
		
		// post: posx() == x
		if (super.getX() != x)
			throw new PostconditionError("posx() == x");
		// post: posy() == y
		if (super.getY() != y)
			throw new PostconditionError("posy() == y");
		// post: largeur() == l
		if( super.getLargeur() != l)
			throw new PostconditionError("largeur() == l");
		// post: hauteur() == h
		if( super.getHauteur() != h)
			throw new PostconditionError("hauteur() == h");
		// post: orRestant() == 51
		if( super.getOrRestant() != 51)
			throw new PostconditionError("orRestant() == 51");
		// post: abandonCompteur() == 51
		if( super.getAbandonCompteur() != 51)
			throw new PostconditionError("abandonCompteur() == 51");
		// post: etatAppartenance() == ORC
		if (super.getEtatAppartenance() != ERace.ORC)
			throw new PostconditionError("etatAppartenance() == ORC");
		
		return this;
	}
	
	public void retrait(int s){
		this.checkInvariants();

		// pre: ! estLaminee() && s > 0
		if( super.estLaminee() || s <= 0)
			throw new PreconditionError(" ! estLaminee() && s > 0");

		int abandonCompteur_pre = super.getAbandonCompteur();
		int OrRestant_pre = super.getOrRestant();
		ERace etatAppartenance_pre = super.getEtatAppartenance();
		
		super.retrait(s);
		
		this.checkInvariants();
		
		// post: orRestant() == orRestant()@pre - s 
		if (super.getOrRestant() != OrRestant_pre - s)                                     
			throw new PostconditionError("orRestant() == orRestant()@pre - s");  
			
		// post: abandonCompteur() == abandonCompteur()@pre 
		if (super.getAbandonCompteur() != abandonCompteur_pre)                                     
			throw new PostconditionError("abandonCompteur() == abandonCompteur()@pre");  

		// post: etatAppartenance() == etatAppartenance()@pre
		if (super.getEtatAppartenance() != etatAppartenance_pre)
			throw new PostconditionError("etatAppartenance() == etatAppartenance()@pre");
	}
	
	public void acceuil(ERace race){
		this.checkInvariants();

		// pre: estAbandonnee() || etatAppartenance() == race
		if ( ! (super.estAbandonne() || super.getEtatAppartenance() == race)) 
			throw new PreconditionError("estAbandonnee() || etatAppartenance() == race");

		int orRestant_pre = super.getOrRestant();
		
		super.acceuil(race);
		
		this.checkInvariants();

		// post: orRestant() == orRestant()@pre  
		if (super.getOrRestant() != orRestant_pre)                                     
			throw new PostconditionError("orRestant() == orRestant()@pre"); 
		
		// post: abandonCompteur() == 0 
		if (super.getAbandonCompteur() != 0)                                     
			throw new PostconditionError("abandonCompteur() == 0");
		
		// post: etatAppartenance() == race
		if (super.getEtatAppartenance() != race)
			throw new PostconditionError("etatAppartenance() == race");
	}
	
	public void abandoned(){
		this.checkInvariants();
		
		// pre: ! estAbandonne()
		if (super.estAbandonne())
			throw new PreconditionError(" ! estAbandonne()");

		int orRestant_pre = super.getOrRestant();
		int abandonCompteur_pre = super.getAbandonCompteur();
		ERace etatAppartenance_pre = super.getEtatAppartenance();
		
		super.abandoned();
		
		this.checkInvariants();
		
		// post: orRestant() == orRestant()@pre
		if (super.getOrRestant() != orRestant_pre)                                     
			throw new PostconditionError("orRestant() == orRestant()@pre"); 

		// post: abandonCompteur() == abandonCompteur()@pre + 1
		if (super.getAbandonCompteur() != abandonCompteur_pre + 1)                                     
			throw new PostconditionError("abandonCompteur() == abandonCompteur()@pre + 1");
		
		// post: etatAppartenance() == etatAppartenance()@pre
		if (super.getEtatAppartenance() != etatAppartenance_pre)
			throw new PostconditionError("etatAppartenance() == etatAppartenance()@pre");
	}
	
	public ERace getEtatAppartenance() {
		// pre: ¬estAbandonnee()
		if (super.estAbandonne())
			throw new PreconditionError("¬estAbandonnee()");
		
		return super.getEtatAppartenance();
	}
}
