/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.actions;
import blockworld.predicates.*;
import java.util.Vector;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class Putdown extends Action {
	public Putdown(char x) {
		name = "Putdown";
		this.x = x;

		preVect.add(new Holding(x));

		delVect.add(new Holding(x));

		addVect.add(new OnTable(x));
		addVect.add(new ArmEmpty());
	}

	public String toString() {
		return name + '(' + x + ')';
	}

}