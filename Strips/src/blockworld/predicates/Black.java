/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class Black extends Predicate {
	public Black (char x) {
		name = "Black";
		this.x = x;
	}

	public String toString() {
		return name + '(' + x + ')';
	}

}
