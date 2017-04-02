/**
 * 
 */
package com.tacocat.lambda.util;


/**
 * Keeps track of time, accurate to the nanosecond. The timer regularly appears ready at specified intervals.
 */
public class Timer {

	private long lastTime;
	private long interval;
	
	/**
	 * Constructs a new timer that is set to use the given interval length. Negative intervals are not allowed and will become 0.
	 * @param newInterval - the timer will be flagged as ready every time this amount of milliseconds has passed
	 */
	public Timer(long newInterval) {
		setInterval(newInterval);
		lastTime = currentTime();
	}
	
	/**
	 * Tells if the time interval has passed since the last successful call to this function.
	 * @return true if interval has passed
	 */
	public boolean ready() {
		if (currentTime() - lastTime >= interval) {
			lastTime = currentTime();
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true once for every time the interval has passed since the last unsuccessful call to this function.
	 * @return true if interval has passed
	 */
	public boolean stillReady() {
		if (currentTime() - lastTime >= interval) {
			lastTime += interval;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the current system time in milliseconds.
	 * @return current time
	 */
	private long currentTime() {
		return System.nanoTime() / 1000000L;
	}
	
	/**
	 * Sets the interval parameter for the timer. Negative intervals are not allowed.
	 * @param newInterval - interval length in milliseconds
	 */
	public void setInterval(long newInterval) {
		if (newInterval < 0) {
			throw new Error("Interval cannot be negative");
		}
		
		interval = newInterval;
	}
	
	/**
	 * Getter
	 */
	public long getInterval() { return interval; };
}
