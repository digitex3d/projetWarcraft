package tests.implem;

import implemError.HotelVilleImplError;
import implementations.HotelVilleImpl;

import org.junit.Before;

import tests.AbstractHotelVilleTest;
import contracts.HotelVilleContract;

public class HotelVilleTestError extends AbstractHotelVilleTest{
	@Override
	@Before
	public void before() {
		hdv = new HotelVilleImpl();
	}

}
