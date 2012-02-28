/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.predicates;

import blockworld.*;
import blockworld.Stackable;
import java.util.Vector;
import java.util.Iterator;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class PredicateVect extends Stackable {
	public Vector<Predicate> vect;

	public PredicateVect(Vector<Predicate> v) {
		type = "PredicateVect";
		name = "PredicateVect"; // ??

		vect = v;
	}


	public String toString() {
		String s = "";

		Iterator<Predicate> itr = vect.iterator();

		while (itr.hasNext()) {
			s += itr.next().toString() + ",";
		}

		return s;
	}
}
