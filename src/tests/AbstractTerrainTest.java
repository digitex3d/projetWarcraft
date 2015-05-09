package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.EEntite;
import exceptions.ContractError;
import services.ITerrain;
import services.IVillageois;

public abstract class AbstractTerrainTest extends AbstractAssertion {
	protected ITerrain terrain;
	
	@Before
	public abstract void before();

	@After
	public void after() {
		terrain = null;
	}
	
	@Test
	public void testInit1() {
		terrain.init(600, 500);
		
		// oracle
		assertPerso("init, Le terrain est créé avec une mauvaise largeur", terrain.getLargeur() == 600);
		assertPerso("init, Le terrain est créé avec une mauvaise hauteur", terrain.getHauteur() == 500);
	}

	@Test
	public void testInit2() {
		terrain.init(800, 400);
		
		// oracle
		assertPerso("init, Le terrain est créé avec une mauvaise largeur", terrain.getLargeur() == 800);
		assertPerso("init, Le terrain est créé avec une mauvaise hauteur", terrain.getHauteur() == 400);
	}

	@Test
	public void testInit3() {
		terrain.init(800, 600);
		
		// oracle
		assertPerso("init, Le terrain est créé avec une mauvaise largeur", terrain.getLargeur() == 800);
		assertPerso("init, Le terrain est créé avec une mauvaise hauteur", terrain.getHauteur() == 600);
	}

	@Test
	public void testInit4() {
		boolean exc = false;
		try {
			terrain.init(500, 600);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}
	
	@Test
	public void testInit5() {
		boolean exc = false;
		try {
			terrain.init(800, 210);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testSetEntiteAt1() {
		terrain.init(800, 600);
		terrain.setEntiteAt(EEntite.ROUTE, 0, 10, 21, 21);
		
		// oracle
		for (int x = 0; x < 21; x++)
			for (int y = 10; y < 31; y++)
				assertPerso("setEntiteAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.ROUTE));
	}
	
	@Test
	public void testSetEntiteAt2() {
		terrain.init(800, 600);
		terrain.setEntiteAt(EEntite.ROUTE, 10, 0, 21, 21);
		
		// oracle
		for (int x = 10; x < 31; x++)
			for (int y = 0; y < 21; y++)
				assertPerso("setEntiteAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.ROUTE));
	}
	
	@Test
	public void testSetEntiteAt3() {
		terrain.init(800, 600);
		terrain.setEntiteAt(EEntite.ROUTE, 790, 590, 10, 5);
		
		// oracle
		for (int x = 790; x < 800; x++)
			for (int y = 590; y < 595; y++)
				assertPerso("setEntiteAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.ROUTE));
	}

	@Test
	public void testSetEntiteAt4() {
		terrain.init(800, 600);
		terrain.setEntiteAt(EEntite.ROUTE, 790, 590, 5, 10);
		
		// oracle
		for (int x = 790; x < 795; x++)
			for (int y = 590; y < 600; y++)
				assertPerso("setEntiteAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.ROUTE));
	}

	@Test
	public void testSetEntiteAt5() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.setEntiteAt(EEntite.ROUTE, 0, 0, 800, 600);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setEntiteAt, Pas d'exception levée", exc);
	}

	@Test
	public void testSetEntiteAt6() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.setEntiteAt(EEntite.ROUTE, -1, 10, 31, 31);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setEntiteAt, Pas d'exception levée", exc);
	}

	@Test
	public void testSetEntiteAt7() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.setEntiteAt(EEntite.ROUTE, 10, -1, 31, 31);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setEntiteAt, Pas d'exception levée", exc);
	}

	@Test
	public void testSetEntiteAt8() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.setEntiteAt(EEntite.ROUTE, 790, 590, 31, 5);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setEntiteAt, Pas d'exception levée", exc);
	}

	@Test
	public void testSetEntiteAt9() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.setEntiteAt(EEntite.ROUTE, 790, 590, 5, 31);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setEntiteAt, Pas d'exception levée", exc);
	}

	@Test
	public void testMoveVillageoisAt1() {
		terrain.init(800, 600);
		terrain.moveVillageoisAt(0, 50, 40);
		
		// oracle
		for (int x = 50; x < 61; x++)
			for (int y = 40; y < 51; y++)
				assertPerso("moveVillageoisAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
		for (int x = 40; x < 51; x++)
			for (int y = 40; y < 51; y++)
				assertPerso("moveVillageoisAt, Le terrain contient l'entité", ! terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
	}

	@Test
	public void testMoveVillageoisAt2() {
		terrain.init(800, 600);
		terrain.moveVillageoisAt(0, 40, 50);
		
		// oracle
		for (int x = 40; x < 51; x++)
			for (int y = 50; y < 61; y++)
				assertPerso("moveVillageoisAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
		for (int x = 40; x < 51; x++)
			for (int y = 40; y < 51; y++)
				assertPerso("moveVillageoisAt, Le terrain contient l'entité", ! terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
	}

	@Test
	public void testMoveVillageoisAt3() {
		terrain.init(800, 600);
		terrain.moveVillageoisAt(0, 50, 50);
		
		// oracle
		for (int x = 50; x < 61; x++)
			for (int y = 50; y < 61; y++)
				assertPerso("moveVillageoisAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
		for (int x = 40; x < 51; x++)
			for (int y = 40; y < 51; y++)
				assertPerso("moveVillageoisAt, Le terrain contient l'entité", ! terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
	}

	@Test
	public void testMoveVillageoisAt4() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.moveVillageoisAt(-1, 60, 50);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("moveVillageoisAt, Pas d'exception levée", exc);
	}

	@Test
	public void testMoveVillageoisAt5() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.moveVillageoisAt(0, -1, 50);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("moveVillageoisAt, Pas d'exception levée", exc);
	}

	@Test
	public void testMoveVillageoisAt6() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.moveVillageoisAt(0, 50, -1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("moveVillageoisAt, Pas d'exception levée", exc);
	}


	@Test
	public void testMoveVillageoisAt7() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.moveVillageoisAt(0, 795, 50);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("moveVillageoisAt, Pas d'exception levée", exc);
	}
	
	@Test
	public void testMoveVillageoisAt8() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.moveVillageoisAt(0, 50, 595);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("moveVillageoisAt, Pas d'exception levée", exc);
	}

	@Test
	public void testMoveVillageoisAt9() {
		boolean exc = false;
			terrain.init(800, 600);
			terrain.setEntiteAt(EEntite.MURAILLE, 0, 0, 21, 21);
		try {
			terrain.moveVillageoisAt(0, 10, 10);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("moveVillageoisAt, Pas d'exception levée", exc);
	}

	@Test
	public void testRemoveEntiteAt1() {
		terrain.init(800, 600);
		terrain.removeEntiteAt(EEntite.VILLAGEOIS, 40, 40, 11, 11);
		
		// oracle
		for (int x = 40; x < 51; x++)
			for (int y = 40; y < 51; y++)
				assertPerso("removeEntiteAt, Le terrain contient l'entité", ! terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
	}


	@Test
	public void testRemoveEntiteAt2() {
		terrain.init(800, 600);
		terrain.setEntiteAt(EEntite.ROUTE, 10, 10, 11, 11);
		terrain.removeEntiteAt(EEntite.ROUTE, 10, 10, 11, 11);
		
		// oracle
		for (int x = 10; x < 21; x++)
			for (int y = 10; y < 21; y++)
				assertPerso("removeEntiteAt, Le terrain contient l'entité", ! terrain.getEntiteAt(x, y).contains(EEntite.ROUTE));
	}

	@Test
	public void testRemoveEntiteAt3() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.removeEntiteAt(EEntite.VILLAGEOIS, 10, 10, 11, 11);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("removeEntiteAt, Pas d'exception levée", exc);
	}


	@Test
	public void testRemoveEntiteAt4() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.removeEntiteAt(EEntite.VILLAGEOIS, -1, 10, 11, 11);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("removeEntiteAt, Pas d'exception levée", exc);
	}


	@Test
	public void testRemoveEntiteAt5() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.removeEntiteAt(EEntite.VILLAGEOIS, 10, -1, 11, 11);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("removeEntiteAt, Pas d'exception levée", exc);
	}


	@Test
	public void testRemoveEntiteAt6() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.removeEntiteAt(EEntite.VILLAGEOIS, 10, 10, 799, 11);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("removeEntiteAt, Pas d'exception levée", exc);
	}


	@Test
	public void testRemoveEntiteAt7() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.removeEntiteAt(EEntite.VILLAGEOIS, 10, 10, 11, 599);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("removeEntiteAt, Pas d'exception levée", exc);
	}
	
	@Test
	public void testReinsertVillageoisAt1() {
		terrain.init(800, 600);
		terrain.getListeVillageois().get(0).setXY(80, 80);
		terrain.removeEntiteAt(EEntite.VILLAGEOIS, 40, 40, 11, 11);
		
		terrain.reinsertVillageois(0);
		// oracle
		IVillageois vill = terrain.getListeVillageois().get(0);
		for (int x = vill.getX(); x < vill.getX() + 11; x++)
			for (int y = vill.getY(); y < vill.getY() + 11; y++)
				assertPerso("removeEntiteAt, Le terrain ne contient pas l'entité", terrain.getEntiteAt(x, y).contains(EEntite.VILLAGEOIS));
	}

	@Test
	public void testReinsertVillageoisAt2() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.reinsertVillageois(0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("reinsertVillageois, Pas d'exception levée", exc);
	}
	
	@Test
	public void testReinsertVillageoisAt3() {
		boolean exc = false;
			terrain.init(800, 600);
		try {
			terrain.reinsertVillageois(-1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("reinsertVillageois, Pas d'exception levée", exc);
	}

}
