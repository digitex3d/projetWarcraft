package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;

public final class Utils {
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static int distance(int x1, int y1, int x2, int y2) {
		int xdiff = x2 - x1;
		int ydiff = y2 - y1;
		return (int) Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}
	
	public static HashMap<String, Object> getServiceAtPre(Object service) {
		HashMap<String, Object> obs = new HashMap<String, Object>();
		Class<? extends Object> cl = service.getClass();
		for (Method m : cl.getMethods()) {
			if ( ! m.getReturnType().equals(Void.TYPE) &&
					! m.getName().equals("init") &&
					m.getDeclaringClass().getName().equals(cl.getName()))
				try {
					obs.put(m.getName(), m.invoke(service));
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
		}
		return obs;
	}
}

