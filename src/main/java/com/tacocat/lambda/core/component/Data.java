/**
 * 
 */
package com.tacocat.lambda.core.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for holding arbitrary (key, value) pairs
 */
public class Data {
	/**
	 * Map for storing data fields
	 */
	private Map<DataField<?>, Object> data;
	
	/**
	 * Ex: 
	 *  new Data(Position.X, 10, Position.Y, 50)
	 *  
	 * @param arguments pairs of DataField and corresponding value
	 */
	public Data(Object ... arguments) {
		data = new HashMap<DataField<?>, Object>();
		setProperties(arguments);
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param dataToCopy data field of Data class to copy
	 * @param arguments pairs of DataField and corresponding value
	 */
	private Data(Map<DataField<?>, Object> dataToCopy, Object ... arguments) {
		data = new HashMap<DataField<?>, Object>(dataToCopy);
		setProperties(arguments);
	}

	/**
	 * Merge constructor
	 * <br>
	 * Creates new Data by combining a + b
	 * 
	 * @param a
	 * @param b
	 */
	private Data(Data a, Data b) {
		// Merge two data sets by adding all of B's data into a clone of A
		data = new HashMap<DataField<?>, Object>(a.data);
		data.putAll(b.data);
	}
	
	/**
	 * Set data values
	 * @param arguments pairs of DataField and corresponding value
	 */
	private void setProperties(Object ... arguments) {
		if (arguments.length % 2 != 0) {
			throw new Error("Always provide arguments in (field, value) pairs");
		}
		
		for (int i = 0 ; i < arguments.length; i += 2) {
			if (!(arguments[i] instanceof DataField)) {
				throw new Error("DataField must come before value");
			}
			
			DataField<?> key = (DataField<?>) arguments[i];
			data.put(key, arguments[i + 1]);
		}
	}
	
	/**
	 * Modify data values
	 * @param arguments pairs of DataField and corresponding value
	 * @return new Data object with updated values
	 */
	public Data modify(Object ... arguments) {
		 return new Data(data, arguments);
	}
	
	/**
	 * @param dataToMerge
	 * @return new Data object
	 */
	public Data merge(Data dataToMerge) {
		return new Data(this, dataToMerge);
	}
	
	/**
	 * @param field name of field to get data for
	 * @return data for given field
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(DataField<T> field) {
		return data.containsKey(field) ? (T) data.get(field) : field.defaultValue;
	}
	
	/**
	 * @return all data values
	 */
	public Collection<Object> getValues() {
		return data.values();
	}
	
	/**
	 * 
	 */
	public String toString() {
		return data.toString();
	}
}
