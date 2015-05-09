package tests.implemError;

import implemError.HotelVilleImplError;

import org.junit.Before;

import tests.AbstractHotelVilleTest;
import contracts.HotelVilleContract;

public class HotelVilleTestError extends AbstractHotelVilleTest{
	@Override
	@Before
	public void before() {
		hdv = new HotelVilleContract(new HotelVilleImplError());
	}
	
	

}
