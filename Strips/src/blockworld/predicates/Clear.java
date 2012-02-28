/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class Clear extends Predicate {
	public Clear (char x) {
		name = "Clear";
		this.x = x;
	}

	public String toString() {
		return name + '(' + x + ')';
	}
}
