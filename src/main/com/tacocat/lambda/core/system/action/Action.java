/**
 * 
 */
package com.tacocat.lambda.core.system.action;


/**
 * Parallelizable modification to a game's state that does not depend on execution order
 */
public abstract interface Action {
	/**
	 * @return which ActionQueue this belongs to
	 */
	public String getQueueName();
	
	/**
	 * Begins a new ActionQueue with this action as the first element
	 * 
	 * @return ActionQueue instance
	 */
	public ActionQueue startActionQueue();
	
}
