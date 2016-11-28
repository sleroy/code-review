package io.github.sleroy.ml.dataset;

import java.util.List;

/**
 * The Class MapRecord defines the record.
 */
public class MapRecord implements Record {

	private Object clazz;
	private List<Object> values;

	/**
	 * Instantiates a new map record.
	 *
	 * @param recordSchema
	 *            the record schema
	 * @param values2
	 *            the values
	 */
	public MapRecord(List<Object> values2, Object clazz) {
		this.values = values2;
		this.clazz = clazz;
	}

	@Override
	public Object getClasse() {

		return clazz;
	}

	@Override
	public List<Object> attributes() {

		return values;
	}

}
