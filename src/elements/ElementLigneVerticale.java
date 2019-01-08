package elements;

import java.util.ArrayList;

public class ElementLigneVerticale extends ElementLigne implements Element {
	
	public ArrayList<ElementCharac> characters;
	private int cptOfInvocations = - 1;
	
	public ElementLigneVerticale() {
		characters = new ArrayList<ElementCharac>();
	}
	
	public ElementLigneVerticale(String valueOfLigne, int x, int y) {
		this();
		for (int i = 0; i < valueOfLigne.length(); i++){
			characters.add(new ElementCharac(valueOfLigne.charAt(i)));
			characters.get(i).setX(x);
			characters.get(i).setY(y + i); // vertical
		}
	}
	
	@Override
	public String évaluer() {
		cptOfInvocations  ++;
		String returnValue = characters.get(cptOfInvocations).getValue();
		return  returnValue;
	}
	
	@Override
	public void destroyElement() {
		for (ElementCharac e : characters)
			e.destroyElement();
	}
	
	@Override
	public void resetCompteur() {
		this.cptOfInvocations = -1;
	}
	
	@Override
	public String getLigne() {
		String text = "";
		for (ElementCharac e : characters)
			text += e.évaluer();
		return text;
	}
}
