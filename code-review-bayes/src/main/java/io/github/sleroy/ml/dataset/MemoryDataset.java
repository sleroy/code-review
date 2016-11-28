/*
 *
 */
package io.github.sleroy.ml.dataset;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import groovy.lang.Closure;

/**
 * The Class MemoryDataset defines a basic implementation of a dataset.
 */
public class MemoryDataset implements Dataset {

	private List<Record> records = new ArrayList<>();

	private Classes classes;

	/**
	 * Instantiates a new memory dataset.
	 *
	 * @param classes
	 *            the classes
	 * @param keys
	 *            the keys as an array.
	 */
	public MemoryDataset(Classes classes) {
		super();
		this.classes = classes;

	}

	/**
	 * Adds the record.
	 *
	 * @param values
	 *            the values
	 * @return the record
	 */
	public Record addRecord(Object clazz, List<Object> values) {
		MapRecord record = newRecord(clazz, values);
		add(record);
		return record;
	}

	/**
	 * New record.
	 *
	 * @param values
	 *            the values
	 * @return the map record
	 */
	public MapRecord newRecord(Object clazz, List<Object> values) {
		MapRecord record = new MapRecord(values, clazz);
		return record;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see io.github.sleroy.ml.dataset.Dataset#add(io.github.sleroy.ml.dataset.
	 * Record)
	 */
	@Override
	public void add(Record record) {
		records.add(record);

	}

	@Override
	public Iterator<Record> records() {

		return records.iterator();
	}

	/**
	 * Foreach.
	 *
	 * @param closure
	 *            the closure
	 */
	public void each(Closure<Record> closure) {
		Iterator<Record> iterator = records();
		while (iterator.hasNext()) {
			Record type = (Record) iterator.next();
			closure.call(type);
		}
	}

	public int recordSize() {
		return records.size();
	}

	@Override
	public Classes getClasses() {

		return classes;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see io.github.sleroy.ml.dataset.Dataset#normalize()
	 */
	@Override
	public NormalizedDataset normalize() {
		MemoryNormalizedDataset normalizedDataset = new MemoryNormalizedDataset(this.classes);
		Iterator<Record> iterator = records();
		while (iterator.hasNext()) {
			Record record = (Record) iterator.next();

			normalizedDataset.add(normalize(record));
		}
		return normalizedDataset;
	}

	private NormalizedRecord normalize(Record record) {
		MapNormalizedRecord normalizedRecord = new MapNormalizedRecord(record.getClasse());

		for (Object attr : record.attributes()) {
			if (Number.class.isAssignableFrom(attr.getClass())) {
				normalizedRecord.addAttribute(((Number) attr).doubleValue());
			}
		}

		normalizedRecord.setClasse(record.getClasse());

		return normalizedRecord;
	}

}
