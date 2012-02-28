/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld.actions;

import blockworld.*;
import blockworld.predicates.*;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public abstract class Action extends Stackable {
	public Vector<Predicate> preVect;
	public Vector<Predicate> delVect;
	public Vector<Predicate> addVect;

	public Action() {
		type = "Action";

		preVect = new Vector<Predicate>();
		delVect = new Vector<Predicate>();
		addVect = new Vector<Predicate>();
	}

	public boolean isInAddVect(Predicate p) {
		Iterator<Predicate> itr = addVect.iterator();

		while (itr.hasNext()) {
			if (itr.next().toString().equals(p.toString())) {
				return true;
			}
		}
		return false;
	}

	public boolean canDo(Vector<Predicate> vect) {
		Iterator<Predicate> itr = preVect.iterator();
		while (itr.hasNext()) {
			if (vect.contains(itr.next()) == false) {
				return false;
			}
		}
		return true;
	}

	public boolean possibleAction(Predicate peek, BlockWorld blockWorld) {
		if (isInAddVect(peek) && canDo(blockWorld.currentState)) {
			PredicateVect pv = new PredicateVect(preVect);

			// zabrane
			/*
			if (getName().equals("Pickup")) {
				White w = new White(peek.getX());
				OnTable ot = new OnTable(peek.getX());

				if (blockWorld.desiredStack.contains(w) && blockWorld.desiredStack.contains(ot)) return false;
			}
			 *
			 */

			/*
			// avoid loop
			if (getName().equals("Putdown") && blockWorld.actionsList.size() > 0) {
				if (blockWorld.actionsList.getLast().getName().equals("Pickup") &&
						blockWorld.actionsList.getLast().getX() == getX()) {
					return false;
				}
			}
			if (getName().equals("Stack") && blockWorld.actionsList.size() > 0) {
				if (blockWorld.actionsList.getLast().getName().equals("Unstack") &&
						blockWorld.actionsList.getLast().getX() == getX() &&
						blockWorld.actionsList.getLast().getY() == getY()) {
					return false;
				}
			}
			if (getName().equals("Unstack") && blockWorld.actionsList.size() > 0) {
				if (blockWorld.actionsList.getLast().getName().equals("Stack") &&
						blockWorld.actionsList.getLast().getX() == getX() &&
						blockWorld.actionsList.getLast().getY() == getY()) {
					return false;
				}
			}
			 *
			 */

			blockWorld.desiredStack.push(this);
			blockWorld.desiredStack.push(pv);

			Iterator<Predicate> i = preVect.iterator();
			while (i.hasNext()) blockWorld.desiredStack.push(i.next());

			return true;
		}
		else return false;
	}

	// possibleStepAction i possibleAction u jednu funkciju?
	public boolean possibleStepAction(Predicate peek, BlockWorld blockWorld) {
		if (isInAddVect(peek)) {

			// provera da ne prefarba belu ako je ciljna
			if (getName().equals("Assimilate")) {
				White w = new White(y);
				if (blockWorld.originalDesiredStack.contains(w)) return false;
				w = new White(x);
				if (blockWorld.originalDesiredStack.contains(w)) return false;
				if (blockWorld.currentState.contains(w)) return false;
			}


			// zabrane


			// ne uzimaj pickup ako je On()
			if (getName().equals("Pickup")) {
				Iterator<Block> itr = blockWorld.blocksList.iterator();
				while (itr.hasNext()) {
					On on = new On(x, itr.next().getCaption());
					if (blockWorld.currentState.contains(on)) return false;

				}
			}


			/*
			if (getName().equals("Pickup")) {
				White w = new White(peek.getX());
				OnTable ot = new OnTable(peek.getX());

				if (blockWorld.desiredStack.contains(w) && blockWorld.desiredStack.contains(ot)) return false;
			}
			 *
			 */

			/*
			// avoid loop
			if (getName().equals("Putdown") && blockWorld.actionsList.size() > 0) {
				if (blockWorld.actionsList.getLast().getName().equals("Pickup") &&
						blockWorld.actionsList.getLast().getX() == getX()) {
					return false;
				}
			}
			if (getName().equals("Stack") && blockWorld.actionsList.size() > 0) {
				if (blockWorld.actionsList.getLast().getName().equals("Unstack") &&
						blockWorld.actionsList.getLast().getX() == getX() &&
						blockWorld.actionsList.getLast().getY() == getY()) {
					return false;
				}
			}
			if (getName().equals("Unstack") && blockWorld.actionsList.size() > 0) {
				if (blockWorld.actionsList.getLast().getName().equals("Stack") &&
						blockWorld.actionsList.getLast().getX() == getX() &&
						blockWorld.actionsList.getLast().getY() == getY()) {
					return false;
				}
			}
			 *
			 */

			PredicateVect pv = new PredicateVect(preVect);

			blockWorld.desiredStack.push(this);
			blockWorld.desiredStack.push(pv);

			Iterator<Predicate> i = preVect.iterator();
			while (i.hasNext()) blockWorld.desiredStack.push(i.next());

			return true;
		}
		else return false;
	}

	public void applyAction(BlockWorld blockWorld) {
		blockWorld.currentState.removeAll(delVect);
		blockWorld.currentState.addAll(addVect);
		blockWorld.actionsList.addLast(this);
	}
}