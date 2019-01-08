package elements;

public class ElementVoid implements Element{

	private String value = "_"; /* caract�re de fond */
	
	/**
	 * @param value
	 * @brief Cet �l�ment est l'�l�ment de base de la plateforme de dessin
	 * Il correspond au format de fond
	 * Changer l'attribut value pour changer le caract�re de fond
	 */
	public ElementVoid(String value) {
		this.value = value;
	}
	
	public ElementVoid() {
		
	}
	
	@Override
	public String �valuer() {
		return value;
	}
	
	@Override
	public void destroyElement() {
		value = "_"; // apr�s suppresion ce caract�re sera affich�
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
