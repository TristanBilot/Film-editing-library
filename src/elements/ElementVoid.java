package elements;

public class ElementVoid implements Element{

	private String value = "_"; /* caractère de fond */
	
	/**
	 * @param value
	 * @brief Cet élément est l'élément de base de la plateforme de dessin
	 * Il correspond au format de fond
	 * Changer l'attribut value pour changer le caractère de fond
	 */
	public ElementVoid(String value) {
		this.value = value;
	}
	
	public ElementVoid() {
		
	}
	
	@Override
	public String évaluer() {
		return value;
	}
	
	@Override
	public void destroyElement() {
		value = "_"; // après suppresion ce caractère sera affiché
	}

	@Override
	public void modifyValue(String newValue) {
		value = newValue;
	}

	@Override
	public void resetCompteur() {
		
	}

	@Override
	public String getLigne() {
		return null;
	}

	@Override
	public int orientation() {
		return 0;
	}
	@Override
	public Element cloner() {
		return new ElementVoid(value);
	}

}
