/**
 * 
 */
package com.tacocat.lambda.core.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.tacocat.lambda.core.component.Component;

/**
 * Defines a reusable and composable collection of components, that can be referenced by name
 */
public class Behavior {
	
	/**
	 * Collection of named components
	 */
	private Map<NamedComponent, Component> components;
	
	/**
	 * Collection of callbacks for defining each named component
	 */
	private Map<NamedComponent, Function<Entity, Component>> callbacks;
	
	{
		components = new HashMap<NamedComponent, Component>();
		callbacks = new HashMap<NamedComponent, Function<Entity, Component>>();
	}
	
	/**
	 * @return map of names to components
	 */
	public Map<NamedComponent, Component> getComponents() {
		return components;
	}

	/**
	 * Sets each component as a child of the given Entity
	 * and resolves callbacks to initiate components
	 * 
	 * @param entity
	 */
	public void registerWith(Entity entity) {
		// Mark all components as a child of entity
		components.values().forEach(component -> component.setParent(entity));
		
		// Handle dependency callbacks
		callbacks.forEach((field, callback) -> {
			// Resolve dependencies and add the result to the component map
			Component component = components.get(field);
			component.replaceData(callback.apply(entity).getData());
			components.put(field, component);
		});
	}
	
	/**
	 * Define a component and initial value that relates to the Behavior.
	 * <br>
	 * The defined component will be added to any Entity using this Behavior.
	 * 
	 * @param name reference to component
	 * @param type class defining component's data
	 * @param component instance of component that represents the initial state
	 */
	protected void defineComponent(NamedComponent name, Class<?> type, Component component) {
		components.put(name, component);
	}
	
	/**
	 * <p>
	 * Define a component whose initial value depends on other component's included in the Entity using this Behavior.
	 * The defined component will be added to any Entity using this Behavior.
	 * </p>
	 * <pre>
	 *  defineComponent(PoisonDamage, Damage.class, entity -> {
	 *      return new Component(
	 *          Damage.class,
	 *          Damage.HealthTarget, entity.get(Character.Health)
	 *      );
	 *  });
	 * </pre>
	 * @param name reference to component
	 * @param type class defining component's data
	 * @param callback function that takes the Entity and returns an instance of the component that represents the initial state
	 */
	protected void defineComponent(NamedComponent name, Class<?> type, Function<Entity, Component> callback) {
		components.put(name, new Component(type));
		callbacks.put(name, callback);
	}
}
