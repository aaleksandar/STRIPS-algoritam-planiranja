/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class White extends Predicate {
	public White (char x) {
		name = "White";
		this.x = x;
	}

	public String toString() {
		return name + '(' + x + ')';
	}

}
