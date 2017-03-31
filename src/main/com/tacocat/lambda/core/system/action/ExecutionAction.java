/**
 * 
 */
package com.tacocat.lambda.core.system.action;


/**
 * Action that executes a CodeBlock
 */
public class ExecutionAction implements Action {
	/**
	 * Code to execute 
	 */
	public final CodeBlock codeBlock;
	
	/**
	 * Name of queue to run code in
	 */
	private String queueName;
	
	/**
	 * 
	 * @param queueName came of queue to run code in
	 * @param codeBlock code to execute
	 */
	public ExecutionAction(String queueName, CodeBlock codeBlock) {
		this.queueName = queueName;
		this.codeBlock = codeBlock;
	}
	
	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.Action#getQueueName()
	 */
	@Override
	public String getQueueName() {
		return queueName;
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.Action#startActionQueue()
	 */
	@Override
	public ActionQueue startActionQueue() {
		ActionQueue queue = new ExecutionActionQueue();
		queue.add(this);
		
		return queue;
	}

}
