package io.github.sleroy.ml.distance;

import java.util.Set;

import io.github.sleroy.ml.dataset.NormalizedRecord;

/**
 * The Class EuclidianNormalizedRecordDistance.
 */
public class ManhattanRecordDistance implements RecordDistance {

	/**
	 * Instantiates a new euclidian distance.
	 *
	 * @param keys
	 *            the keys
	 */
	public ManhattanRecordDistance() {
		super();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see io.github.sleroy.ml.classifier.NormalizedRecordDistance#distance(io.
	 * github.sleroy. ml.dataset.NormalizedRecord,
	 * io.github.sleroy.ml.dataset.NormalizedRecord)
	 */
	@Override
	public double distance(NormalizedRecord normalizedRecord1, NormalizedRecord normalizedRecord2) {
		double[] values1 = normalizedRecord1.attributes();
		double[] values2 = normalizedRecord2.attributes();

		return new org.apache.commons.math3.ml.distance.ManhattanDistance().compute(values1, values2);
	}

}
