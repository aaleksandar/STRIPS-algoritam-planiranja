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
public class Stack extends Action {
	public Stack(char x, char y) {
		name = "Stack";
		this.x = x;
		this.y = y;

		preVect.add(new Holding(x));
		preVect.add(new Clear(y));

		delVect.add(new Clear(y));
		delVect.add(new Holding(x));

		addVect.add(new ArmEmpty());
		addVect.add(new On(x,y));
	}

	public String toString() {
		return name + '(' + x + ',' + y + ')';
	}

}
