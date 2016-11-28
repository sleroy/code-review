package io.github.sleroy.ml.dataset;

/**
 * The Interface NormalizedRecord.
 */
public interface NormalizedRecord {

	/**
	 * Gets the classe.
	 *
	 * @return the classe
	 */
	Object getClasse();

	/**
	 * Attributes.
	 *
	 * @return the double[]
	 */
	double[] attributes();
}
