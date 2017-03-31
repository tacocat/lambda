/**
 * 
 */
package com.tacocat.lambda.core.system.action;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.DataField;
import com.tacocat.lambda.core.system.action.ExecutionAction;
import com.tacocat.lambda.core.system.action.ModificationAction;
import com.tacocat.lambda.core.system.action.ModificationActionQueue;


public class ModificationActionQueueTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	private static class Position {
		static final DataField<Float> X = DataField.withDefaultValue(3f);
	}
	
	/**
	 * Test method for {@link com.tacocat.lambda.core.system.action.ModificationActionQueue#add(com.tacocat.lambda.core.system.action.Action)}.
	 */
	@Test
	public void testAdd() {
		// Adding a ModificationAction does not throw an error
		ModificationActionQueue queue = new ModificationActionQueue(new Component(Component.class));
		queue.add(new ModificationAction(new Component(Component.class), (component) -> { }));
		
		// Adding any other type of action throws an error
		thrown.expect(Error.class);
		thrown.expectMessage("Expected ModificationAction");
		queue.add(new ExecutionAction(null, () -> { }));
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.system.action.ModificationActionQueue#execute()}.
	 */
	@Test
	public void testExecute() {
		Component position = new Component(Position.class, Position.X, 1f);
		ModificationActionQueue queue = new ModificationActionQueue(position);
		
		queue.add(new ModificationAction(position, p -> p.modify(Position.X, p.get(Position.X) + 10)));
		queue.add(new ModificationAction(position, p -> p.modify(Position.X, p.get(Position.X) + 100)));
		
		queue.execute();
		
		Assert.assertEquals("All modifications are run on component",
			new Float(111f),
			position.get(Position.X)
		);
	}

}
