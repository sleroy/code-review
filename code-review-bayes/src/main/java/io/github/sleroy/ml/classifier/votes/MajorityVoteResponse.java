package io.github.sleroy.ml.classifier.votes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.function.DoubleBinaryOperator;

import org.apache.commons.math3.util.Pair;

import io.github.sleroy.ml.dataset.Classes;
import io.github.sleroy.ml.dataset.NormalizedRecord;

public class MajorityVoteResponse implements VoteResponse {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * io.github.sleroy.ml.classifier.VoteResponse#voteNormalizedRecord(java.util.List,
	 * io.github.sleroy.ml.dataset.Classes)
	 */
	@Override
	public Object vote(List<Pair<NormalizedRecord, Double>> neighborhood, Classes classes) {

		if (neighborhood.isEmpty())
			// Nothing to vote
			return null;

		Map<Object, DoubleAccumulator> votes = new HashMap<>();
		for (Object clazz : classes.getClasses()) {
			votes.put(clazz, new DoubleAccumulator(new DoubleBinaryOperator() {

				@Override
				public double applyAsDouble(double left, double right) {

					return left + right;
				}

			}, 0d));
		}

		for (Pair<NormalizedRecord, Double> neighbor : neighborhood) {
			vote(votes, classes, neighbor);
		}

		return findBestVote(votes);
	}

	/**
	 * Find the most voted classification index.
	 *
	 * @param votes
	 *            the votes
	 * @return the index of the most voted classification.
	 */
	private Object findBestVote(Map<Object, DoubleAccumulator> votes) {
		Entry<Object, DoubleAccumulator> bestIndex = null;
		for (Entry<Object, DoubleAccumulator> entry : votes.entrySet()) {

			if (bestIndex == null) {
				bestIndex = entry;
			} else if (entry.getValue().get() > bestIndex.getValue().get()) {
				bestIndex = entry;
			}
		}
		return bestIndex.getKey();
	}

	/**
	 * Vote.
	 *
	 * @param votes
	 *            the votes
	 * @param classes
	 *            the classes
	 * @param neighborO
	 *            the neighbor
	 */
	protected void vote(Map<Object, DoubleAccumulator> votes, Classes classes,
			Pair<NormalizedRecord, Double> neighbor) {
		votes.get(neighbor.getFirst().getClasse()).accumulate(1);

	}

}
