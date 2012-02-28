/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class On extends Predicate {
	public On (char x, char y) {
		name = "On";
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return name + '(' + x + ',' + y + ')';
	}
}
