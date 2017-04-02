/**
 * 
 */
package com.tacocat.lambda.core.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.ComponentStore;

/**
 * Collection of behaviors that define a collection of components
 * TODO redo register concept to make it easier to add/remove behaviors mid game
 */
public class Entity {
	
	/**
	 * Behaviors that define this entity 
	 */
	private final Behavior[] behaviors;
	
	/**
	 * Reference to this entity's components by name
	 */
	private Map<NamedComponent, Component> components;
	
	/**
	 * @param behaviors list of behaviors that define the Entity
	 */
	public Entity(Behavior ... behaviors) {
		this.behaviors = behaviors;
	}
	
	/**
	 * @param key component name defined in an included behavior
	 * @return component with given name
	 */
	public Component get(NamedComponent key) {
		if (components == null) {
			throw new Error("Can't get components from an Entity without calling `registerWith`");
		}
		
		return components.get(key);
	}
	
	/**
	 * Adds all the components defined within this entity to the given store
	 * @param store
	 */
	public void registerWith(ComponentStore store) {
		components = new HashMap<NamedComponent, Component>();
		
		// Create all components
		for (int i = 0; i < behaviors.length; i++) {
			components.putAll(behaviors[i].getComponents());
		}
		
		// Link dependencies
		for (int i = 0; i < behaviors.length; i++) {
			behaviors[i].registerWith(this);
		}
		
		// Add to store
		components.forEach((name, value) -> {
			store.addWithName(name, value);
		});
	}
	
	/**
	 * @return every component associated with this Entity
	 */
	public Collection<Component> getAllComponents() {
		return components.values();
	}
}
