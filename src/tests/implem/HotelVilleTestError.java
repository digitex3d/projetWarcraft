package tests.implem;


import implementations.HotelVilleImpl;

import org.junit.Before;

import tests.AbstractHotelVilleTest;

public class HotelVilleTestError extends AbstractHotelVilleTest{
	@Override
	@Before
	public void before() {
		hdv = new HotelVilleImpl();
	}

}
