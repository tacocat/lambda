/**
 * 
 */
package com.tacocat.lambda.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tacocat.lambda.util.Timer;

public class TimerTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	Timer testTimer;
	
	@Before
	public void setUp() throws Exception {
		testTimer = new Timer(50);
	}

	/**
	 * Case where the interval hasn't elapsed
	 */
	@Test
	public void testNotReady() {
		assertFalse(testTimer.ready());
	}

	/**
	 * Case where the interval has elapsed
	 */
	@Test
	public void testReady() {
		try {
			Thread.sleep(100);
			assertTrue(testTimer.ready());
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail("Could not sleep.");
		}
	}
	
	/**
	 * Case where the interval is negative
	 */
	@Test
	public void testNegInterval() {
		thrown.expect(Error.class);
		thrown.expectMessage("Interval cannot be negative");
		testTimer.setInterval(-10);
	}
	
	/**
	 * Case where the interval has changed
	 */
	@Test
	public void testReadyWithIntervalChange() {
		try {
			Thread.sleep(25);
			assertFalse(testTimer.ready());
			testTimer.setInterval(25);
			assertTrue(testTimer.ready());
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail("Could not sleep.");
		}
	}

	/**
	 * Case where the interval hasn't elapsed
	 */
	@Test
	public void testStillNotReady() {
		assertFalse(testTimer.stillReady());
	}
	
	/**
	 * Case where the interval has elapsed once
	 */
	@Test
	public void testStillReadyOnce() {
		try {
			Thread.sleep(60);
			assertTrue(testTimer.stillReady());
			assertFalse(testTimer.stillReady());
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail("Could not sleep.");
		}
	}
	
	/**
	 * Case where the interval has elapsed several times
	 */
	@Test
	public void testStillReadyMany() {
		try {
			Thread.sleep(150);
			assertTrue(testTimer.stillReady());
			assertTrue(testTimer.stillReady());
			assertTrue(testTimer.stillReady());
			assertFalse(testTimer.stillReady());
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail("Could not sleep.");
		}
	}

}
