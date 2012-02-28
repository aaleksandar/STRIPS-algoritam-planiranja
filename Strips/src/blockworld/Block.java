/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public class Block {
	public static char c = 'A';
	private char caption;

	public Block() {
		caption = c++;
	}

	public char getCaption() {
		return caption;
	}

	public static void decrementC() {
		c--;
	}

	public String toString() {
		return "Block(" + caption + ')';
	}
}
