package io.github.sleroy.ml.dataset;

import java.util.HashMap;

/**
 * The Class ClassIndex indexes all classes by their index into the hashmap.
 */
public class ClassIndex extends HashMap<Object, Integer> {

	@Override
	public String toString() {
		return "ClassIndex []";
	}

	/**
	 * Instantiates a new class index.
	 */
	public ClassIndex() {
		super();
	}
}
