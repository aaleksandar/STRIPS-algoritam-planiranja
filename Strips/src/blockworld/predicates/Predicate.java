/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;
import blockworld.Stackable;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public abstract class Predicate extends Stackable {
	public Predicate() { type = "Predicate"; }

	//public abstract String toString();


	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (this.getClass() != other.getClass())return false;
		Predicate p = (Predicate)other;
		if (this.x == p.x && this.y == p.y && this.name == p.name) return true;
		else return false;
	}
}
