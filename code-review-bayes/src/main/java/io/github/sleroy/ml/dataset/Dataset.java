package io.github.sleroy.ml.dataset;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import groovy.lang.Closure;


/**
 * The Interface Dataset.
 */
public interface Dataset {

	/**
	 * Adds a new record to the dataset.
	 *
	 * @param record
	 *            the record
	 */
	void add(Record record);

	/**
	 * Iteration on the records.
	 *
	 * @return the list of records.
	 */
	Iterator<Record> records();

	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	Classes getClasses();


	/**
	 * Recordsize.
	 *
	 * @return the int
	 */
	int recordSize();


	/**
	 * Normalize.
	 *
	 * @return the normalized dataset
	 */
	NormalizedDataset normalize();

	/**
	 * Foreach.
	 *
	 * @param closure
	 *            the closure
	 */
	void each(Closure<Record> closure);
}
