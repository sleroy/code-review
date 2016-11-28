package io.github.sleroy.ml.dataset;

import java.util.List;

/**
 * The Interface Record defines a list of key/pair values.
 */
public interface Record {

	/**
	 * Attributes.
	 *
	 * @return the map
	 */
	List<Object> attributes();

	/**
	 * Gets the classe.
	 *
	 * @return the classe
	 */
	Object getClasse();

}
