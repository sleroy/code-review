package io.github.sleroy.ml.classifier.votes;

import java.util.Map;
import java.util.concurrent.atomic.DoubleAccumulator;

import org.apache.commons.math3.util.Pair;

import io.github.sleroy.ml.dataset.Classes;
import io.github.sleroy.ml.dataset.NormalizedRecord;

public class WeightedVoteResponse extends MajorityVoteResponse {

	public WeightedVoteResponse() {
		super();
	}

	@Override
	protected void vote(Map<Object, DoubleAccumulator> votes, Classes classes,
			Pair<NormalizedRecord, Double> neighbor) {
		votes.get(neighbor.getFirst()).accumulate(1 / (neighbor.getValue() + 1));
	}

}
