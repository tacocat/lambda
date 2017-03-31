/**
 * 
 */
package com.tacocat.lambda.core.system.action;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.system.action.ExecutionAction;
import com.tacocat.lambda.core.system.action.ExecutionActionQueue;
import com.tacocat.lambda.core.system.action.ModificationAction;


public class ExecutionActionQueueTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test method for {@link com.tacocat.lambda.core.system.action.ExecutionActionQueue#add(com.tacocat.lambda.core.system.action.Action)}.
	 */
	@Test
	public void testAdd() {
		// Adding an ExecutionAction does not throw an error
		ExecutionActionQueue queue = new ExecutionActionQueue();
		queue.add(new ExecutionAction(null, () -> { }));
		
		// Adding any other type of action throws an error
		thrown.expect(Error.class);
		thrown.expectMessage("Expected ExecutionAction");
		queue.add(new ModificationAction(new Component(Component.class), (component) -> { }));
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.system.action.ExecutionActionQueue#execute()}.
	 */
	@Test
	public void testExecute() {
		Float one = 1f;
		Float two = 2f;
		List<Float> floats = new ArrayList<Float>();
		
		ExecutionActionQueue queue = new ExecutionActionQueue();
		
		queue.add(new ExecutionAction(null, () -> { floats.add(one); }));
		queue.add(new ExecutionAction(null, () -> { floats.add(two); }));
		
		queue.execute();
		
		Float[] expected = {one, two};
		Assert.assertArrayEquals("All added actions are executed",
			expected,
			floats.toArray()
		);
	}

}
