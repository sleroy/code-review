package io.github.sleroy.ml.distance;

import io.github.sleroy.ml.dataset.NormalizedRecord;

/**
 * The Interface RecordDistance defines the distance.
 */
public interface RecordDistance {

	/**
	 * Distance.
	 *
	 * @param record1
	 *            the record 1
	 * @param record2
	 *            the record 2
	 * @return the double
	 */
	double distance(NormalizedRecord record1, NormalizedRecord record2);

}
