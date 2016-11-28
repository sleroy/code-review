package io.github.sleroy.ml.distance;

import org.apache.commons.math3.ml.distance.EuclideanDistance;

import io.github.sleroy.ml.dataset.NormalizedRecord;

/**
 * The Class EuclidianRecordDistance.
 */
public class EuclidianRecordDistance implements RecordDistance {

	/**
	 * Instantiates a new euclidian distance.
	 *
	 * @param keys
	 *            the keys
	 */
	public EuclidianRecordDistance() {
		super();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * io.github.sleroy.ml.classifier.RecordDistance#distance(io.github.sleroy.
	 * ml.dataset.Record, io.github.sleroy.ml.dataset.Record)
	 */
	@Override
	public double distance(NormalizedRecord record1, NormalizedRecord record2) {

		double[] values1 = record1.attributes();
		double[] values2 = record2.attributes();

		return new EuclideanDistance().compute(values1, values2);
	}

}
