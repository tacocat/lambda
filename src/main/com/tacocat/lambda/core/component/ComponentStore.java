/**
 * 
 */
package com.tacocat.lambda.core.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.core.entity.NamedComponent;

/**
 * Class for storing components by type and name
 */
public class ComponentStore {
	/**
	 * Components by type
	 */
	private Map<Class<?>, List<Component>> byType;
	
	/**
	 * Components by name
	 */
	private Map<NamedComponent, List<Component>> byName;
	
	/**
	 * 
	 */
	public ComponentStore() {
		byType = new HashMap<Class<?>, List<Component>>();
		byName = new HashMap<NamedComponent, List<Component>>();
	}
	
	/**
	 * @param component component to add
	 */
	public void add(Component component) {
		Class<?> key = component.getType();
		addToMap(byType, key, component);
	}
	
	/**
	 * Adds a component along with a reference name for easier lookup
	 * 
	 * @param key name of component
	 * @param component component to add
	 */
	public void addWithName(NamedComponent key, Component component) {
		add(component);
		addToMap(byName, key, component);
	}
	
	/**
	 * @param map
	 * @param key 
	 * @param value
	 */
	private <T> void addToMap(Map<T, List<Component>> map, T key, Component value) {
		List<Component> list;
		
		if (!map.containsKey(key)) {
			list = new ArrayList<Component>();
		} else {
			list = map.get(key);		
		}
		
		list.add(value);
		map.put(key, list);
		value.addStoreList(list);
	}
	
	/**
	 * @param key class of component
	 * @return all components that match the given class
	 */
	public List<Component> find(Class<?> key) {
		return findInMap(byType, key);
	}
	
	/**
	 * @param key name of component
	 * @return all components that were registered with the given name
	 */
	public List<Component> findByName(NamedComponent key) {
		return findInMap(byName, key);
	}
	
	/**
	 * @param map
	 * @param key
	 * @return all components that were registered with the given key
	 */
	private <T> List<Component> findInMap(Map<T, List<Component>> map, T key) {
		if (!map.containsKey(key))
			return new ArrayList<Component>();
		else
			return map.get(key);
	}
	
	/**
	 * @param component component to remove from store
	 */
	public void remove(Component component) {		
		component.getStoreLists().forEach(list -> list.remove(component));
	}
	
	/**
	 * Add all components collected by an entity
	 * @param entity
	 */
	public void registerEntity(Entity entity) {
		entity.registerWith(this);
	}
	
	/**
	 * 
	 */
	public String toString() {
		return byType.toString();
	}
}
