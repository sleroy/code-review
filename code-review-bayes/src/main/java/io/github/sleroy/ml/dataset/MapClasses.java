package io.github.sleroy.ml.dataset;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapClasses implements Classes {

	private ClassIndex classIndex = new ClassIndex();

	/**
	 * Instantiates a new map classes.
	 */
	public MapClasses() {
		super();
	}

	/**
	 * Instantiates a new map classes.
	 *
	 * @param classes
	 *            the classes
	 */
	public MapClasses(Map<Object, Integer> classes) {
		super();
		classIndex.putAll(classes);
	}

	/**
	 * Instantiates a new map classes.
	 *
	 * @param classes
	 *            the classes
	 */
	public MapClasses(Object... classes) {
		super();
		for (int i = 0; i < classes.length; i++) {
			Object object = classes[i];
			classIndex.put(object, i);
		}
	}

	@Override
	public Integer classIndex(Object clazz) {

		return classIndex.get(clazz);
	}

	@Override
	public Set<Object> getClasses() {

		return classIndex.keySet();
	}

	@Override
	public ClassIndex getClassIndex() {

		return classIndex;
	}

	@Override
	public int size() {

		return classIndex.size();
	}

	@Override
	public String toString() {
		return "MapClasses [classIndex=" + classIndex + "]";
	}

	/* (non-Javadoc)
	 * @see io.github.sleroy.ml.dataset.Classes#findClass(int)
	 */
	@Override
	public Object findClass(int i) {
		for (Entry<Object, Integer> entry : this.classIndex.entrySet()) {
			if (i == entry.getValue()) {
				return entry.getKey();
			}
		}
		return null;
	}

}
