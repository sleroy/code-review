package io.github.sleroy.ml.classifier.votes;

import java.util.List;

import org.apache.commons.math3.util.Pair;

import io.github.sleroy.ml.dataset.Classes;
import io.github.sleroy.ml.dataset.NormalizedRecord;

/**
 * The Class VoteResponse.
 * http://machinelearningmastery.com/tutorial-to-implement-k-nearest-neighbors-
 * in-python-from-scratch/
 */
public interface VoteResponse {

	/**
	 * Vote for the  best class..
	 *
	 * @param neighbors
	 *            the neighbors
	 * @param classes
	 *            the classes
	 * @return the class that has been voted
	 */
	public Object vote(List<Pair<NormalizedRecord, Double>> neighbors, Classes classes);
}
