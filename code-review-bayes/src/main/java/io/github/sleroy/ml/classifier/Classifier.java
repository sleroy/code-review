package io.github.sleroy.ml.classifier;

import io.github.sleroy.ml.dataset.Dataset;
import io.github.sleroy.ml.dataset.NormalizedDataset;
import io.github.sleroy.ml.dataset.NormalizedRecord;
import io.github.sleroy.ml.dataset.Record;

/**
 * The Interface Classifier defines an component performs a classification.
 */
public interface Classifier {

	/**
	 * Creates a classifier from the given dataset.
	 *
	 * @param data
	 *            the dataset
	 */
	void buildClassifier(NormalizedDataset data);

	/**
	 * Create a classifier from the given data set.
	 *
	 * @param instance
	 * @return
	 */
	java.util.Map<java.lang.Object, java.lang.Double> classDistribution(NormalizedRecord instance);

	/**
	 * Generate the membership distribution for this instance using this
	 * classifier.
	 *
	 * @param instance
	 *            Classify the instance according to this classifier.
	 * @return the classification
	 */
	java.lang.Object classify(NormalizedRecord instance);

}
