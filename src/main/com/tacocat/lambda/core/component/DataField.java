/**
 * 
 */
package com.tacocat.lambda.core.component;

/**
 * Description of a component's field
 */
public class DataField<T> {
	/**
	 * 
	 */
	public final T defaultValue;
	
	/**
	 * @param defaultValue initial value data field will have
	 */
	public DataField(T defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * 
	 */
	public DataField() {
		this.defaultValue = null;
	}
	
	/**
	 * @param defaultValue initial value data field will have
	 * @return DataField instance with default value set
	 */
	public static <K> DataField<K> withDefaultValue(K defaultValue) {
		return new DataField<K>(defaultValue);
	}
}
