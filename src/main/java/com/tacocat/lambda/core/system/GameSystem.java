/**
 * 
 */
package com.tacocat.lambda.core.system;

import java.util.ArrayList;
import java.util.List;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.ComponentStore;
import com.tacocat.lambda.core.component.DataField;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.core.entity.NamedComponent;
import com.tacocat.lambda.core.system.action.Action;
import com.tacocat.lambda.core.system.action.CodeBlock;
import com.tacocat.lambda.core.system.action.ExecutionAction;
import com.tacocat.lambda.core.system.action.Modification;
import com.tacocat.lambda.core.system.action.ModificationAction;

/**
 * Class for defining reusable units of game logic
 */
public abstract class GameSystem {
	
	/**
	 * Reference to ComponentStore
	 */
	private ComponentStore components;
	
	/**
	 * Reference to list of actions currently being built
	 */
	private List<Action> currentActionList;
	
	/**
	 * Method for defining the System's logic
	 * <pre>
	 * void update() {
	 *     findAll(Player.class).forEach(player -> 
	 *         modify(player, Player.Health, player.get(Player.Health) - 5);
	 *     );
	 * }     
	 * </pre>
	 */
	protected abstract void update();
	
	/**
	 * Builds a new list of actions based on current state in ComponentStore and System's update method
	 * @param components current state of game world
	 * @return list of actions to modify state of game world
	 */
	public List<Action> getActionList(ComponentStore components) {
		this.components = components;
		
		// Start building a new action list
		currentActionList = new ArrayList<Action>();
		
		// Build action list
		update();
		
		return currentActionList; 
	}
	
	/**
	 * @param key class defining component's data
	 * @return all components matching the given type
	 */
	protected List<Component> findAll(Class<?> key) {
		return components.find(key);
	}

	/**
	 * @param key name of component defined in a Behavior
	 * @return all components registered with the given name
	 */
	protected List<Component> findAll(NamedComponent key) {
		return components.findByName(key);
	}
	
	/**
	 * @param key class defining component's data
	 * @return first component matching the given type
	 */
	protected Component findFirst(Class<?> key) {
		return components.find(key).get(0);
	}	
	
	/**
	 * Runs code during the game engine's update cycle
	 * 
	 * @param code code to execute
	 */
	protected void execute(CodeBlock code) {
		currentActionList.add(new ExecutionAction(null, code));
	}
	
	/**
	 * @param target component to modify data
	 * @param modification callback that receives the component and can modify state
	 */
	protected void modify(Component target, Modification modification) {
		currentActionList.add(new ModificationAction(target, modification));
	}
	
	/**
	 * Set a single component property
	 * @param target component to modify data
	 * @param field DataField of component to modify
	 * @param value new value
	 */
	protected void set(Component target, DataField<?> field, Object value) {
		currentActionList.add(new ModificationAction(
			target,
			component -> component.modify(field, value)
		));
	}
	
	/**
	 * Removes a component from the game
	 * @param component
	 */
	protected void destroy(Component component) {
		currentActionList.add(new ExecutionAction(
			"Create/Remove",
			() -> { components.remove(component); }
		));
	}
	
	/**
	 * Removes all components belonging to an entity from the game
	 * @param entity
	 */
	protected void destroy(Entity entity) {
		currentActionList.add(new ExecutionAction(
			"Create/Remove",
			() -> {
				entity.getAllComponents().forEach(component -> components.remove(component));
			}
		));
	}
	
	/**
	 * @param component component to add to game state
	 */
	protected void create(Component component) {
		currentActionList.add(new ExecutionAction(
			"Create/Remove",
			() -> { components.add(component); }
		));
	}
	
	/**
	 * @param entity Entity with component's to add to game state
	 */
	protected void create(Entity entity) {
		currentActionList.add(new ExecutionAction(
			"Create/Remove",
			() -> { 
				components.registerEntity(entity);
			}
		));
	}
}
