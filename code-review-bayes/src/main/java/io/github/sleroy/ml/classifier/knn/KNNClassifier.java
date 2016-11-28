package io.github.sleroy.ml.classifier.knn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.util.Pair;

import io.github.sleroy.ml.classifier.Classifier;
import io.github.sleroy.ml.classifier.TrainingRequiredException;
import io.github.sleroy.ml.classifier.votes.VoteResponse;
import io.github.sleroy.ml.dataset.Dataset;
import io.github.sleroy.ml.dataset.NormalizedDataset;
import io.github.sleroy.ml.dataset.NormalizedRecord;
import io.github.sleroy.ml.distance.EuclidianRecordDistance;
import io.github.sleroy.ml.distance.RecordDistance;

public class KNNClassifier implements Classifier {

	private NormalizedDataset trainingData;
	private NearestNeighbors nearestNeighbors;
	private int k;
	private RecordDistance distance;
	private VoteResponse voteResponse;

	/**
	 * Instantiates a new KNN classifier.
	 *
	 * @param k
	 *            the k
	 * @param recordDistance
	 *            the NormalizedRecord distance
	 * @param voteResponse
	 *            the vote response
	 */
	public KNNClassifier(int k, RecordDistance recordDistance, VoteResponse voteResponse) {
		super();
		this.k = k;
		this.voteResponse = voteResponse;
		this.distance = new EuclidianRecordDistance();
		nearestNeighbors = new NearestNeighbors();
	}

	@Override
	public void buildClassifier(NormalizedDataset data) {
		this.trainingData = data;

	}

	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public RecordDistance getDistance() {
		return new EuclidianRecordDistance();
	}

	@Override
	public Map<Object, Double> classDistribution(NormalizedRecord instance) {
		if (trainingData == null)
			throw new TrainingRequiredException();

		/* Get nearest neighbors */
		List<Pair<NormalizedRecord, Double>> neighbors = nearestNeighbors.compute(trainingData, instance, distance, k);

		/* Build distribution map */
		HashMap<Object, Double> out = new HashMap<Object, Double>();
		for (Object o : trainingData.getClasses().getClasses())
			out.put(o, 0.0);

		for (Pair<NormalizedRecord, Double> i : neighbors) {
			out.put(i.getFirst().getClasse(), out.get(i.getFirst().getClasse()) + 1);
		}

		double min = k;
		double max = 0;
		for (Object key : out.keySet()) {
			double val = out.get(key);
			if (val > max)
				max = val;
			if (val < min)
				min = val;
		}
		/* Normalize distribution map */
		if (max != min) {
			for (Object key : out.keySet()) {
				out.put(key, (out.get(key) - min) / (max - min));
			}
		}

		return out;

	}

	@Override
	public Object classify(NormalizedRecord instance) {
		List<Pair<NormalizedRecord, Double>> neighbors = this.nearestNeighbors.compute(trainingData, instance, distance,
				k);

		return voteResponse.vote(neighbors, trainingData.getClasses());
	}

}
