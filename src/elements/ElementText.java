package elements;

import java.util.ArrayList;

public class ElementText implements Element{

	// 1 TEXTE CONTIENT DES LIGNES
	public ArrayList<ElementLigne> lignes;
	private int cptOfLignes = 0;
	private int cptOfCharac = -1; // pour compter chaque carac de chaque ligne
	private int nbOfLignes;
	private int sizeMaxLigne;
	private int lengthText;
	public ArrayList<ElementCharac> charac;
	
	public ElementText() {
		lignes = new ArrayList<ElementLigne>();
		charac = new ArrayList<ElementCharac>();
	}
	
	public ElementText(ArrayList<ElementLigne> lignes) {
		this.lignes = lignes;
	}
	
	public ElementText(String value, int sizeOfLigne, int x, int y) {
		this();
		this.lengthText = value.length();
		this.sizeMaxLigne = sizeOfLigne;

		int cptY = 0, cptX = 0;
		for (int i = 0; i < value.length(); i++){
			charac.add(new ElementCharac(value.charAt(i)));
			
			charac.get(i).setX(x + cptX); 
			charac.get(i).setY(y + cptY);
			
			cptX++;
			if (cptX == sizeOfLigne) {
				cptY++;
				cptX = 0;
			}
		}
	}

	public int sizeOfArray() {
		return this.lignes.size();
	}
	
	public void addCharacToLastLigne(char c) {
		lignes.get(lignes.size() - 1).addCharac(c);
	}
	// better
	public void addCharacToLastLigne(char c, int x, int y) {
		lignes.get(lignes.size() - 1).addCharac(c, x, y);
	}
	
	@Override
	public void destroyElement() {
		for (ElementCharac e : charac)
			e.destroyElement();
	}
	
	public void newLigne(ElementLigne ligne) {
		lignes.add(ligne);
	}
	
	public void removeLigne(int index) {
		lignes.remove(index);
	}
	
	@Override
	public String évaluer() {
		cptOfCharac++;
		if (cptOfCharac == lengthText)
			cptOfCharac -= 1;
		return charac.get(cptOfCharac).évaluer();
	}

	public void test() {
		for (int ligne = 0; ligne < nbOfLignes; ligne ++)
			for (int charac = 0; charac < sizeMaxLigne; charac++) {
				System.out.println(lignes.get(ligne).getChar(charac).getValue());
			}
	}
	
	@Override
	public void modifyValue(String newValue) {
		
	}

	@Override
	public void resetCompteur() {
		this.cptOfCharac = 0;
		this.cptOfLignes = 0;
	}

	@Override
	public String getLigne() {
		String text = "";
		for (ElementLigne l : lignes)
			text += l.getLigne();
		return text;
	}
	
	public int getCptOfInvocations() {
		return cptOfLignes;
	}
	
	
	public int getNbOfLignes() {
		return nbOfLignes;
	}

	public void setNbOfLignes(int nbOfLignes) {
		this.nbOfLignes = nbOfLignes;
	}

	@Override
	public int orientation() {
		return 0;
	}
	
	@Override
	public Element cloner() {
		return new ElementText(lignes);
	}

}
