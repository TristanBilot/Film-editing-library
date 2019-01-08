package elements;

public interface Element {
	
	String évaluer();
	void destroyElement();
	void modifyValue(String newValue);
	void resetCompteur();
	String getLigne();
	int orientation();
	Element cloner();
	
}
