/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class OnTable extends Predicate {
	public OnTable (char x) {
		name = "OnTable";
		this.x = x;
	}

	public String toString() {
		return name + '(' + x + ')';
	}
}
