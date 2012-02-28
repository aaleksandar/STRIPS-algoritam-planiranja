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
public class Assimilate extends Action {
	public Assimilate(char x, char y) {
		name = "Assimilate";
		this.x = x;
		this.y = y;

		preVect.add(new On(x,y));
		preVect.add(new Black(x));
		preVect.add(new White(y));

		delVect.add(new White(y));

		addVect.add(new Black(y));
	}

	public String toString() {
		return name + '(' + x + ',' + y + ')';
	}
}

