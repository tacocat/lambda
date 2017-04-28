/**
 * 
 */
package com.tacocat.lambda.core.component;

import java.util.ArrayList;
import java.util.List;

import com.tacocat.lambda.core.entity.Entity;


/**
 * Class for defining and storing game data
 */
public class Component {

	/**
	 * Data stored in component
	 */
	private Data data;
	
	/**
	 * Class defining component's data
	 */
	private final Class<?> type;
	
	/**
	 * Entity component belongs to
	 */
	private Entity parent = null;
	
	/**
	 * Reference to all ComponentStore lists component is currently being kept in
	 * <br><br>
	 * This reference allows for quicker component removal.
	 */
	private List<List<Component>> storeLists = new ArrayList<List<Component>>();
	
	/**
	 * Ex: 
	 *  new Component(Position.class, Position.X, 10, Position.Y, 50)
	 *  
	 * @param type class defining component's data
	 * @param arguments pairs of DataField and corresponding value
	 */
	public Component(Class<?> type, Object ... arguments) {
		this.type = type;
		
		data = new Data(arguments);
	}
	
	/**
	 * @return data stored in component
	 */
	public Data getData() {
		return data;
	}
	
	/**
	 * @param data new data for component
	 */
	public void replaceData(Data data) {
		this.data = data;
	}
	
	/**
	 * @param field
	 * @return value for given field
	 */
	public <T> T get(DataField<T> field) {
		return data.get(field);
	}
	
	/**
	 * Modify data values without altering original Data object
	 * @param arguments pairs of DataField and corresponding value
	 */
	public void modify(Object ... arguments) {
		replaceData(data.modify(arguments));
	}
	
	/**
	 * @return class defining component's data
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * @return Entity component belongs to, or null if independent
	 */
	public Entity getEntity() {
		return parent;
	}

	/**
	 * @param entity
	 */
	public void setEntity(Entity entity) {
		this.parent = entity;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return String.format("%s: %s", type.getName(), data.toString());
	}

	/**
	 * @return reference to all ComponentStore lists component is currently being kept in
	 */
	public List<List<Component>> getStoreLists() {
		return storeLists;
	}

	/**
	 * @param newList additional list component is being kept in
	 */
	public void addStoreList(List<Component> newList) {
		storeLists.add(newList);
	}
}
