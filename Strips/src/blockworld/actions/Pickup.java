/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.actions;
import blockworld.predicates.*;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class Pickup extends Action {
	public Pickup(char x) {
		name = "Pickup";
		this.x = x;

		preVect.add(new ArmEmpty());
		preVect.add(new OnTable(x));
		preVect.add(new Clear(x));

		delVect.add(new OnTable(x));
		delVect.add(new ArmEmpty());

		addVect.add(new Holding(x));
	}

	public String toString() {
		return name + '(' + x + ')';
	}
}
