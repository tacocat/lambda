/**
 * 
 */
package com.tacocat.lambda.common.simplephysics;

import com.tacocat.lambda.common.simplephysics.component.Velocity;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.system.GameSystem;
import com.tacocat.lambda.core.system.action.Modification;

/**
 * System that adds vectors. Each call to update will add all Velocities to their targets.
 */
public class SimplePhysics extends GameSystem {

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.GameSystem#update(com.tacocat.myengine.core.ComponentStore)
	 */
	@Override
	public void update() {
		// Add all velocities to their target
		findAll(Velocity.class).forEach((velocity) -> {
			modify(velocity.get(Velocity.TARGET), add(velocity));
		});
	}

	/**
	 * @param b Component with (x, y, z) fields
	 * @return Modification that accepts a component with (x, y, z) and adds b to it
	 */
	public Modification add(Component b) {
		return (a) -> {
			a.modify(Velocity.X, a.get(Velocity.X) + b.get(Velocity.X),
					 Velocity.Y, a.get(Velocity.Y) + b.get(Velocity.Y),
					 Velocity.Z, a.get(Velocity.Z) + b.get(Velocity.Z));
		};
	}
}
