package elements;

public interface Element {
	
	String �valuer();
	void destroyElement();
	void modifyValue(String newValue);
	void resetCompteur();
	String getLigne();
	int orientation();
	Element cloner();
	
}
