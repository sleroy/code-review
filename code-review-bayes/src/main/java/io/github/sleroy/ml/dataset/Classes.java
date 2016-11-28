
package io.github.sleroy.ml.dataset;

import java.util.Set;

/**
 * The Interface Classes defines the container of classes.
 */
public interface Classes {

	/**
	 * Class index.
	 *
	 * @param clazz
	 *            a class instance.
	 * @return the int
	 */
	Integer classIndex(Object clazz);

	/**
	 * The number of classes.
	 *
	 * @return the int
	 */
	int size();

	/**
	 * Gets the class index.
	 *
	 * @return the class index
	 */
	ClassIndex getClassIndex();

	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	Set<Object> getClasses();

	/**
	 * Find class given its index.
	 *
	 * @param i
	 *            the i
	 * @return the object
	 */
	Object findClass(int i);

}
