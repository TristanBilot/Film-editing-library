package elements;

import java.util.ArrayList;

public class ElementLigneOblique implements Element{

	private int cptOfInvocations = -1;
	private int orientation;
	public ArrayList<ElementCharac> characters; 
	// il aurait fallu mettre cet attribut en privé mais nous utiliserons
	// ici directement l'attribut pour une meilleure visibilité dans le code
	
	public ElementLigneOblique() {
		characters = new ArrayList<ElementCharac>();
	}
	
	public ElementLigneOblique(ArrayList<ElementCharac> characters, int orientation) {
		this.characters = characters;
		this.orientation = orientation;
	}
	
	
	/**
	 * @param orientation
	 * @param value
	 * @param x
	 * @param y
	 * @brief à l'instance, choix entre les 4 directions possibles de lignes obliques
	 */
	public ElementLigneOblique(int orientation, String value, int x, int y) {
		this();
		resetCompteur();
		if (orientation == 1) {
			value = reverse(value); // inverser la string pour affichage correct
			this.orientation = 1;
			for (int i = 0; i < value.length(); i++) {
				characters.add(new ElementCharac(value.charAt(i)));
				characters.get(i).setX(x - i); /* -i pour décalage */
				characters.get(i).setY(y - i);
			}
		}
		if (orientation == 2) {
			value = reverse(value);// inverser la string pour affichage correct
			this.orientation = 2;
			for (int i = 0; i < value.length(); i++) {
				characters.add(new ElementCharac(value.charAt(i)));
				characters.get(i).setX(x + i); /* -i pour décalage */
				characters.get(i).setY(y - i);
			}
		}
		if (orientation == 3) {
			this.orientation = 3;
			for (int i = 0; i < value.length(); i++) {
				characters.add(new ElementCharac(value.charAt(i)));
				characters.get(i).setX(x - i); /* -i pour décalage */
				characters.get(i).setY(y + i);
			}
		}
		if (orientation == 4) {
			this.orientation = 4;
			for (int i = 0; i < value.length(); i++) {
				characters.add(new ElementCharac(value.charAt(i)));
				characters.get(i).setX(x + i);
				characters.get(i).setY(y + i);
			}
		}
		
	}
	
	/**
	 * @param input
	 * @return l'inverse de la string en paramètre
	 */
	public static String reverse(String input){
	    char[] in = input.toCharArray();
	    int begin=0;
	    int end=in.length-1;
	    char temp;
	    while(end>begin){
	        temp = in[begin];
	        in[begin]=in[end];
	        in[end] = temp;
	        end--;
	        begin++;
	    }
	    return new String(in);
	}
	
	@Override
	public String évaluer() {
		cptOfInvocations ++;
		String returnValue = characters.get(cptOfInvocations).getValue();
		return  returnValue;
	}

	public int getCptOfInvocations() {
		return cptOfInvocations;
	}
	
	public String getLigne() {
		String text = "";
		for (ElementCharac e : characters)
			text += e.évaluer();
		return text;
	}

	public void setCptOfInvocations(int cptOfInvocations) {
		this.cptOfInvocations = cptOfInvocations;
	}
	
	public int nombreDeCaractères() {
		return characters.size();
	}
	
	public ArrayList<ElementCharac> getCharacters() {
		return this.characters;
	}
	
	@Override
	public void resetCompteur() {
		this.cptOfInvocations = -1;
	}

	@Override
	public void destroyElement() {
		for (ElementCharac e : characters)
			e.destroyElement();
	}

	@Override
	public void modifyValue(String newValue) {

	}
	
	@Override
	public int orientation() {
		return this.orientation;
	}
	@Override
	public Element cloner() {
		return new ElementLigneOblique(characters, orientation);
	}
}
