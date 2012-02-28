/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class Holding extends Predicate {
	public Holding (char x) {
		name = "Holding";
		this.x = x;
	}

	public String toString() {
		return name + '(' + x + ')';
	}
}
