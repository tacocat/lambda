/**
 * 
 */
package com.tacocat.lambda.core.system.action;


/**
 * List of Actions
 */
public interface ActionQueue {
	/**
	 * @param action
	 */
	public void add(Action action);
	
	/**
	 * Runs all Actions in queue
	 */
	public void execute();
}
