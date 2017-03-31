/**
 * 
 */
package com.tacocat.lambda.core.system.action;

import java.util.ArrayList;
import java.util.List;

/**
 * List of ExecutionActions
 */
public class ExecutionActionQueue implements ActionQueue {
	
	/**
	 * Code blocks to run
	 */
	private List<CodeBlock> blocks;
	
	/**
	 * 
	 */
	public ExecutionActionQueue() {
		blocks = new ArrayList<CodeBlock>();
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.ActionQueue#add(com.tacocat.myengine.core.Action)
	 */
	@Override
	public void add(Action action) {
		// Add the code block to list
		if (action instanceof ExecutionAction) {
			blocks.add(( (ExecutionAction) action).codeBlock);
		} else {
			throw new Error(String.format("Expected ExecutionAction but got %s. Possible collision in queueNames", action.getClass().toString()));
		}
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.ActionQueue#execute()
	 */
	@Override
	public void execute() {
		// Run each code block
		blocks.forEach((block) -> block.apply());
	}

}
