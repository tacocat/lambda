/**
 * 
 */
package com.tacocat.lambda.core.system.action;

import com.tacocat.lambda.core.component.Component;

/**
 * Function that takes a component and can modify its state
 */
@FunctionalInterface
public interface Modification {
	public void apply(Component arg);
}
