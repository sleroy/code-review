package io.github.sleroy.ml.classifier.knn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import io.github.sleroy.ml.dataset.Dataset;
import io.github.sleroy.ml.dataset.NormalizedDataset;
import io.github.sleroy.ml.dataset.NormalizedRecord;
import io.github.sleroy.ml.dataset.Record;
import io.github.sleroy.ml.distance.RecordDistance;

public class NearestNeighbors {

	/**
	 * Compute the k nearest neighbors.
	 *
	 * @param dataset
	 *            the dataset
	 * @param instance
	 *            the instance
	 * @param distance
	 *            the NormalizedRecord distance
	 * @param k
	 *            the k
	 * @return the list
	 */
	public List<Pair<NormalizedRecord, Double>> compute(NormalizedDataset dataset, NormalizedRecord instance,
			RecordDistance distance, int k) {
		List<Pair<NormalizedRecord, Double>> distances = new ArrayList<>();

		Iterator<NormalizedRecord> normalizedRecords = dataset.records();

		while (normalizedRecords.hasNext()) {
			NormalizedRecord otherNormalizedRecord = (NormalizedRecord) normalizedRecords.next();
			distances.add(new Pair<>(otherNormalizedRecord, distance.distance(instance, otherNormalizedRecord)));
		}
		Collections.sort(distances, new Comparator<Pair<NormalizedRecord, Double>>() {

			@Override
			public int compare(Pair<NormalizedRecord, Double> o1, Pair<NormalizedRecord, Double> o2) {

				return o1.getValue().compareTo(o2.getValue());
			}
		});
		return distances.subList(0, Math.min(distances.size(), k));
	}

}
