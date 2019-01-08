package elements;

import java.util.ArrayList;

public class ElementLigne implements Element{
	
	// 1 LIGNE CONTIENT DES CARACTERES
	public ArrayList<ElementCharac> characters;
	private int cptOfInvocations = -1;

	public ElementLigne() {
		characters = new ArrayList<ElementCharac>();
	}

	public ElementLigne(String valueOfLigne) {
		this();
		for (int i = 0; i < valueOfLigne.length(); i++){
			characters.add(new ElementCharac(valueOfLigne.charAt(i)));
		}
	}
	
	public ElementLigne(String valueOfLigne, int x, int y) {
		this();
		for (int i = 0; i < valueOfLigne.length(); i++){
			characters.add(new ElementCharac(valueOfLigne.charAt(i)));
			characters.get(i).setX(x + i); // horizontal
			characters.get(i).setY(y);
		}
	}
	
	public ElementLigne(ArrayList<ElementCharac> characters) {
		this.characters = characters;
	}
	
	public void addCharac(ElementCharac e) {
		characters.add(e);
	}
	
	public void addCharac(char c) {
		characters.add(new ElementCharac(c));
	}
	
	public void addCharac(char c, int x, int y) {
		characters.add(new ElementCharac(c, x, y));
	}


	public int nombreDeCaractères() {
		return characters.size();
	}
	
	public ArrayList<ElementCharac> getCharacters() {
		return this.characters;
	}
	
	@Override
	public void destroyElement() {
		for (ElementCharac e : characters)
			e.destroyElement();
	}
	
	public ElementCharac getChar(int index) {
		return characters.get(index);
	}
	
	public void newCharac(ElementCharac charac) {
		characters.add(charac);
	}
	
	public void newCharac(char charac) {
		ElementCharac element = new ElementCharac(charac);
		characters.add(element);
	}
	
	public void removeCharac(int index) {
		characters.remove(index);
	}
	
	@Override
	public String évaluer() {
		cptOfInvocations ++;
//		if (cptOfInvocations == characters.size() - 1) 
//			cptOfInvocations --;
		String returnValue = characters.get(cptOfInvocations).getValue();
		return returnValue;
	}
	@Override
	public void modifyValue(String newValue) {
		characters.clear(); // suppression puis ajout du nouveau
		for (int i = 0; i < newValue.length(); i++)
				characters.add(new ElementCharac(newValue.charAt(i))); 
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

	@Override
	public int orientation() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Element cloner() {
		return new ElementLigne(characters);
	}
}
