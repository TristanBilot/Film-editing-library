package elements;

import ressources.ExceptionUnvalidSize;

public class ElementCharac implements Element{

	private String value;
	private int x, y; // positions primordiales aux placements
	
	public ElementCharac(String valueOfCharac) throws ExceptionUnvalidSize {
		value = valueOfCharac;
	}
	
	// cast d'un char en String pour initialiser value
	public ElementCharac(char charAt) {
		String s = "" + charAt;
		value = s;
	}
	
	public ElementCharac(String charAt, int x, int y) {
		this.value = charAt;
		this.x = x;
		this.y = y;
	}
	
	public ElementCharac(char charAt, int x, int y) {
		this(charAt);
		this.x = x;
		this.y = y;
	}
	public ElementCharac() {
		
	}
	
	@Override
	public String évaluer() {
		return this.value;
	}
	
	@Override
	public void destroyElement() {
		this.value = "_"; // caractère affiché après suppression d'un élément
	}
	
	public void modifyValue(String newValue) {
		value = newValue;
	}
	
	public void modifyValue(char newValue) {
		value = "" + newValue; // cast
	}

	@Override
	public void resetCompteur() {
	}

	@Override
	public String getLigne() {
		return this.value;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int orientation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Element cloner() {
		return new ElementCharac(value, x , y);
	}

}
