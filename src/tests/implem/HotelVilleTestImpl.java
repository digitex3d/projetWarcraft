package tests.implem;


import implementations.HotelVilleImpl;

import org.junit.Before;

import tests.AbstractHotelVilleTest;

public class HotelVilleTestImpl extends AbstractHotelVilleTest{
	@Override
	@Before
	public void before() {
		hdv = new HotelVilleImpl();
	}

}
