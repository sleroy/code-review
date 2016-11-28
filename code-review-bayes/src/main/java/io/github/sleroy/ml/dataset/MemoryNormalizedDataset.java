package io.github.sleroy.ml.dataset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.Validate;

import groovy.lang.Closure;

public class MemoryNormalizedDataset implements NormalizedDataset {

	private List<NormalizedRecord> records = new ArrayList<>();
	private Classes classes;

	public MemoryNormalizedDataset(Classes _classes) {
		super();
		this.classes = _classes;
		Validate.notNull(classes);
	}

	@Override
	public void add(NormalizedRecord record) {
		records.add(record);

	}

	@Override
	public Iterator<NormalizedRecord> records() {

		return records.iterator();
	}

	@Override
	public Classes getClasses() {

		return classes;
	}

	@Override
	public int recordSize() {

		return records.size();
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@Override
	public void each(Closure<NormalizedRecord> closure) {
		Iterator<NormalizedRecord> iterator = records();
		while (iterator.hasNext()) {
			NormalizedRecord type = (NormalizedRecord) iterator.next();
			closure.call(type);
		}

	}

	/**
	 * Adds the record.
	 *
	 * @param values
	 *            the values
	 * @return the record
	 */
	public NormalizedRecord addRecord(Object clazz, List<? extends Number> values) {
		NormalizedRecord record = newRecord(clazz, values);
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
	public NormalizedRecord newRecord(Object clazz, List<? extends Number> values) {
		NormalizedRecord record = new MapNormalizedRecord(values, clazz);
		return record;
	}

	/**
	 * New record.
	 *
	 * @param values
	 *            the values
	 * @return the map record
	 */
	public NormalizedRecord newRecord(Object clazz, Number... values) {
		NormalizedRecord record = new MapNormalizedRecord(Arrays.asList(values), clazz);
		return record;
	}
}
