package io.github.sleroy.ml.dataset;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

public class MapNormalizedRecord implements NormalizedRecord {

	private double[] array= null;

	private List<Double> attributes = new ArrayList<>();

	private Object classe;

	/**
	 * Instantiates a new map normalized record.
	 *
	 * @param values
	 *            the values
	 * @param clazz
	 *            the clazz
	 */
	public MapNormalizedRecord(List<? extends Number> values, Object clazz) {
		for (Number number : values) {
			attributes.add(number.doubleValue());
		}
		this.classe = clazz;
		Validate.notNull(values);
		Validate.notNull(clazz);
	}

	/**
	 * Instantiates a new map normalized record.
	 *
	 * @param clazz the clazz
	 */
	public MapNormalizedRecord(Object clazz) {
		super();
		this.classe = clazz;
	}

	/**
	 * Adds the attribute.
	 *
	 * @param doubleValue
	 *            the double value
	 */
	public void addAttribute(double doubleValue) {
		array = null;
		attributes.add(doubleValue);

	}

	@Override
	public double[] attributes() {
		if (array == null) {
			array = new double[attributes.size()];
			for (int i = 0; i < attributes.size(); i++) {
				Double d = attributes.get(i);
				array[i] = d;
			}
		}
		return array;
	}

	@Override
	public Object getClasse() {

		return classe;
	}

	public void setClasse(Object classe) {
		this.classe = classe;

	}

	@Override
	public String toString() {
		return "MapNormalizedRecord [attributes=" + attributes + ", classe=" + classe + "]";
	}

}
