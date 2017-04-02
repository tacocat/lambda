/**
 * 
 */
package com.tacocat.lambda.core.system.action;

import java.util.ArrayList;
import java.util.List;

import com.tacocat.lambda.core.component.Component;

/**
 * List of Modifications
 */
public class ModificationActionQueue implements ActionQueue {
	/**
	 * Component being modified
	 */
	private final Component component;
	
	/**
	 * List of Modifications to run
	 */
	private List<Modification> modifications;
	
	/**
	 * @param component component being modified
	 */
	public ModificationActionQueue(Component component) {
		this.component = component;
		modifications = new ArrayList<Modification>();
	}
	
	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.ActionQueue#add(com.tacocat.myengine.core.Action)
	 */
	@Override
	public void add(Action action) {
		// Add the modification to list
		if (action instanceof ModificationAction) {
			modifications.add(( (ModificationAction) action).modification);
		} else {
			throw new Error(String.format("Expected ModificationAction but got %s. Possible collision in queueNames.", action.getClass().toString()));
		}
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.ActionQueue#execute()
	 */
	@Override
	public void execute() {
		// Transform the target components data by applying all the modifications
		modifications.stream().sequential().forEach(modification -> modification.apply(component));
	}

}
