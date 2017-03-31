/**
 * 
 */
package com.tacocat.lambda.core.system.action;

import com.tacocat.lambda.core.component.Component;

/**
 * Action that executes a Modification
 */
public class ModificationAction implements Action {
	
	/**
	 * Component to modify
	 */
	private final Component target;
	
	/**
	 * Logic for modification
	 */
	public final Modification modification;
	
	/**
	 * @param target component to modify
	 * @param modification logic for modification
	 */
	public ModificationAction(Component target, Modification modification) {
		this.target = target;
		this.modification = modification;
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.Action#getQueueName()
	 */
	@Override
	public String getQueueName() {
		return "Modify " + target.hashCode();
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.Action#startActionQueue()
	 */
	@Override
	public ActionQueue startActionQueue() {
		ActionQueue queue = new ModificationActionQueue(target);
		queue.add(this);
		
		return queue;
	}

}
