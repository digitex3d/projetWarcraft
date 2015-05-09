package tests.implemError;

import implemError.GestionDeplacementImplError;

import org.junit.Before;

import tests.AbstractGestionDeplacementTests;
import contracts.GestionDeplacementContract;

public class GestionDeplacementTestError extends AbstractGestionDeplacementTests{
	@Override
	@Before
	public void before() {
		gd = new  GestionDeplacementContract(new GestionDeplacementImplError());
	}
	
	
	
}
