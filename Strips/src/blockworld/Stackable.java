/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blockworld;

/**
 *
 * @author Aleksandar Abu-Samra
 */
public abstract class Stackable {
	protected String type;
	protected String name;

	protected char x = '*', y = '*';
	public char getX() { return x; }
	public char getY() { return y; }

	public String getType() { return type; }
	public String getName() { return name; }
	public abstract String toString();
}
