/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld;

import blockworld.actions.Action;
import blockworld.predicates.Predicate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Stack;
import javax.swing.DefaultComboBoxModel;

import blockworld.actions.*;
import blockworld.predicates.*;
import javax.swing.JLabel;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class BlockWorld {
	public Vector<Predicate> currentState;
	public Stack<Stackable> desiredStack;
	public Stack<Stackable> originalDesiredStack;
	public LinkedList<Action> actionsList;
	public LinkedList<Block> blocksList;

	public BlockWorld() {
		currentState = new Vector<Predicate>();
		desiredStack = new Stack<Stackable>();
		originalDesiredStack = new Stack<Stackable>();
		actionsList = new LinkedList<Action>();
		blocksList = new LinkedList<Block>();

	}

	public boolean addState(Predicate p) {
		Iterator<Predicate> itr = currentState.iterator();

		while (itr.hasNext()) {
			if (itr.next().toString().equals(p.toString())) {
				return false;
			}
		}

		currentState.add(p);
		return true;

	}
	public boolean removeState(Predicate p) {
		Iterator<Predicate> itr = currentState.iterator();

		while (itr.hasNext()) {
			if (itr.next().toString().equals(p.toString())) {
				itr.remove();
				return true;
			}
		}
		return false;
	}

	public void addAction(Action a) {
		actionsList.addLast(a);
	}

	public void addBlock() {
		blocksList.addLast(new Block());
	}

	public void removeBlock() {
		blocksList.removeLast();
		Block.decrementC();
	}

	public String getAllBlocks() {
		Iterator<Block> itr = blocksList.iterator();
		String s = "";
		while(itr.hasNext()) s += itr.next().getCaption()+",";
		s = s.substring(0, s.length()-1);
		return s;
	}

	public DefaultComboBoxModel getComboBoxModel() {
		Iterator<Block> itr = blocksList.iterator();

		DefaultComboBoxModel dcbm = new DefaultComboBoxModel();

		while (itr.hasNext()) dcbm.addElement(itr.next().getCaption());

		return dcbm;
	}

	public String getInitialContent() {
		Iterator<Predicate> itr = currentState.iterator();
		String s = "";
		while(itr.hasNext()) s += itr.next().toString() + "\n";
		return s;
	}

	public String getDesiredContent() {
		Iterator<Stackable> itr = desiredStack.iterator();
		String s = "";
		while(itr.hasNext()) s += itr.next().toString() + "\n";
		return s;
	}

	public boolean isInCurrentState(Predicate p) {
		Iterator<Predicate> itr = currentState.iterator();

		while (itr.hasNext()) {
			if (itr.next().toString().equals(p.toString())) {
				return true;
			}
		}
		return false;
	}

	public String getActions() {
		Iterator<Action> itr = actionsList.iterator();
		String s = "";
		while(itr.hasNext()) s += itr.next().toString() + "\n";
		return s;
	}

	public void reset() {
		currentState = new Vector<Predicate>();
		desiredStack = new Stack<Stackable>();
		originalDesiredStack = new Stack<Stackable>();
		actionsList = new LinkedList<Action>();
		blocksList = new LinkedList<Block>();
	}

	public boolean findSolution(Predicate p) {
		Iterator<Block> itrX = blocksList.iterator();

		while (itrX.hasNext()) {
			char x = itrX.next().getCaption();

			//
			Putdown pd = new Putdown(x);
			if (pd.possibleAction(p, this)) { return true; }

			//
			Pickup pu = new Pickup(x);
			if (pu.possibleAction(p, this)) { return true; }

			Iterator<Block> itrY = blocksList.iterator();
			while (itrY.hasNext()) {
				char y = itrY.next().getCaption();

				if (x == y) continue;

				// CHECK
				Unstack us = new Unstack(x,y);
				//if (currentState.contains(new On(x,y)))
					if (us.possibleAction(p, this)) { return true; }

				// a morali su "stack" da ga nazovu... -.-
				blockworld.actions.Stack st = new blockworld.actions.Stack(x,y);
				if (st.possibleAction(p, this)) { return true; }

				//
				Assimilate as = new Assimilate(x,y);
				//if (currentState.contains(new Black(x)))
				//if (currentState.contains(new On(x,y)))
					if (as.possibleAction(p, this)) { return true; }
			}
		}

		Iterator<Block> itrX2 = blocksList.iterator();
		while (itrX2.hasNext()) {
			char x = itrX2.next().getCaption();

			//
			Putdown pd = new Putdown(x);
			if (pd.possibleStepAction(p, this)) { return true; }

			//
			Pickup pu = new Pickup(x);
			if (pu.possibleStepAction(p, this)) { return true; }

			Iterator<Block> itrY2 = blocksList.iterator();
			while (itrY2.hasNext()) {
				char y = itrY2.next().getCaption();

				if (x == y) continue;

				// CHECK
				Unstack us = new Unstack(x,y);
				if (currentState.contains(new On(x,y)))
					if (us.possibleStepAction(p, this)) { return true; }

				// a morali su "stack" da ga nazovu... -.-
				blockworld.actions.Stack st = new blockworld.actions.Stack(x,y);
				if (st.possibleStepAction(p, this)) { return true; }

				//
				Assimilate as = new Assimilate(x,y);
				//if (currentState.contains(new Black(x)))
				//if (currentState.contains(new On(x,y)))
					if (as.possibleStepAction(p, this)) { return true; }
			}
		}

		return false;

	}
}
