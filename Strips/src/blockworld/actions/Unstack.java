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
public class Unstack extends Action {
	public Unstack(char x, char y) {
		name = "Unstack";
		this.x = x;
		this.y = y;

		preVect.add(new On(x,y));
		preVect.add(new ArmEmpty());
		preVect.add(new Clear(x));

		delVect.add(new On(x,y));
		delVect.add(new ArmEmpty());

		addVect.add(new Clear(y));
		addVect.add(new Holding(x));
	}

	public String toString() {
		return name + '(' + x + ',' + y + ')';
	}
}
