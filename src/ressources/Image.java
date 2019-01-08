package ressources;

import elements.*;

/**
 * @author bilot
 *
 */
public class Image implements Cloneable {
	
	private static int nbCol;
	private static int nbLig;
	/* le plan est un tableau 2D d'éléments qui va constituer la surface 
	 où l'on pourra appliquer toutes les méthodes définies. */
	private Element plan[][];
	
	/**
	 * @param nbCol
	 * @param nbLig
	 * @brief Instancie une image de x largeur et y hauteur
	 * Seul constructeur utile pour la classe Image
	 */
	public Image(int nbCol, int nbLig) {
		this.nbCol = nbCol;
		this.nbLig = nbLig;
		plan = new Element[nbLig][nbCol]; // !!! dimensionne le tableau
		for (int i = 0; i < nbLig; i++)
			for (int j = 0; j < nbCol; j++)
				plan[i][j] = new ElementVoid(); // instancie tous les elem vides
	}

	
	/**
	 * @param imgToClone
	 * @brief permet la copie d'image pour la réalisation du film
	 */
	public void cloneImage(Image imgToClone) {
		Element tabTmp[][] = imgToClone.getElementTab();
		this.plan = tabTmp;
		this.nbCol = imgToClone.getNbCol();
		this.nbLig = imgToClone.getNbLig();
	}
	
	public void addCharac(String valueOfCharac, int x, int y) throws ExceptionUnvalidSize {
		if ( valueOfCharac.length() == 1) {
			ElementCharac element = new ElementCharac(valueOfCharac);
			plan[y][x] = element;
		}
		else
			throw new ExceptionUnvalidSize("1 seul caractère autorisé.");
	}
	
	public void addLigneObliqueNordOuest(String valueOfLigne, int y, int x)  {
		ElementLigneOblique element = new ElementLigneOblique(1, valueOfLigne, x, y);
		for (int i = 0; i < element.characters.size(); i++)
			placerElement(element, element.characters.get(i).getX(), element.characters.get(i).getY());
	}
	
	public void addLigneObliqueNordEst(String valueOfLigne, int y, int x)  {
		ElementLigneOblique element = new ElementLigneOblique(2, valueOfLigne, x, y);
		for (int i = 0; i < element.characters.size(); i++)
			placerElement(element, element.characters.get(i).getX(), element.characters.get(i).getY());
	}
	
	public void addLigneObliqueSudOuest(String valueOfLigne, int y, int x)  {
		ElementLigneOblique element = new ElementLigneOblique(3, valueOfLigne, x, y);
		for (int i = 0; i < element.characters.size(); i++)
			placerElement(element, element.characters.get(i).getX(), element.characters.get(i).getY());
	}
	
	public void addLigneObliqueSudEst(String valueOfLigne, int y, int x) {
		ElementLigneOblique element = new ElementLigneOblique(4, valueOfLigne, x, y);
		for (int i = 0; i < element.characters.size(); i++)
			placerElement(element, element.characters.get(i).getX(), element.characters.get(i).getY());
	}
	
	public void addLigneVerticale(String valueOfLigne, int y, int x) {
		ElementLigneVerticale element = new ElementLigneVerticale(valueOfLigne, x, y);
		for (int i = 0; i < element.characters.size(); i++)
			placerElement(element, element.characters.get(i).getX(), element.characters.get(i).getY());
	}
	
	public void addLigneHorizontale(String valueOfLigne, int y, int x) {
		ElementLigne element = new ElementLigne(valueOfLigne, x, y);
		for (int i = 0; i < element.characters.size(); i++)
			placerElement(element, element.characters.get(i).getX(), element.characters.get(i).getY());
	}
	
	public void addText(String valueOfLigne, int sizeOfLine, int x, int y) {
		ElementText element = new ElementText(valueOfLigne, sizeOfLine, x, y);
		for (int i = 0; i < element.charac.size() - 1; i++) // - 1 obligatoire pour enlever la lettre en trop à la fin du texte
			placerElement(element, element.charac.get(i).getX(), element.charac.get(i).getY());		
	}
	public void addExtraitText(int x, int y, int xHautGauche, int yHautGauche, int xBasDroite, int yBasDroite, int newX, int newY) {
		int sizeOfLine = xBasDroite - xHautGauche + 1;
		resetAllCompteurs();
		String valueOfLigne = "$";
		int sizeOfLineElement = plan[y][x].getLigne().length();
		for (int i = xHautGauche; i <= xBasDroite ; i++)
			for (int j = yHautGauche; j <= yBasDroite + 1; j++) {
				valueOfLigne += plan[j][i].évaluer();
			}
		addText(valueOfLigne, sizeOfLine, newX, newY);
	}
	
	public void déplacerElement(int x, int y, int newX, int newY) throws ExceptionUnvalidSize {
		// le but est de tester chaque element et de créer une copie de cet élément
		// puis de supprimer l'ancien à l'ancienne position x y
		// nous utilisons new afin d'utiliser la réinitialisation du compteur évaluer()
		String oldLigne = plan[y][x].getLigne(); // backup
		deleteElement(y, x);
		if (ligneOblique(x, y) == 1) 
			addLigneObliqueNordOuest(oldLigne, newX, newY);
		
		if (ligneOblique(x, y) == 2) 
			addLigneObliqueNordEst(oldLigne, newX, newY);
		
		if (ligneOblique(x, y) == 3) 
			addLigneObliqueSudOuest(oldLigne, newX, newY);
		
		if (ligneOblique(x, y) == 4) 
			addLigneObliqueSudEst(oldLigne, newX, newY);
		
		if (plan[y][x].getClass() == ElementLigneVerticale.class) 
			addLigneVerticale(oldLigne, newX, newY);
		
		if (plan[y][x].getClass() == ElementLigne.class) 
			addLigneHorizontale(oldLigne, newX, newY);
		
		if (plan[y][x].getClass() == ElementCharac.class) 
			addCharac(oldLigne, newY, newX);
	}
	
	public void modifierElement(String newLigne, int y, int x) throws ExceptionUnvalidSize{
		String oldLigne = plan[y][x].getLigne(); // backup
		deleteElement(y, x);
		if (ligneOblique(x, y) == 1) 
			addLigneObliqueNordOuest(newLigne, y, x);
		
		if (ligneOblique(x, y) == 2) 
			addLigneObliqueNordEst(newLigne, y, x);
		
		if (ligneOblique(x, y) == 3) 
			addLigneObliqueSudOuest(newLigne, y, x);
		
		if (ligneOblique(x, y) == 4) 
			addLigneObliqueSudEst(newLigne, y, x);
		
		if (plan[y][x].getClass() == ElementLigneVerticale.class) 
			addLigneVerticale(newLigne, y, x);
		
		if (plan[y][x].getClass() == ElementLigne.class) 
			addLigneHorizontale(newLigne, y, x);
		
		if (plan[y][x].getClass() == ElementCharac.class) {
			if (newLigne.length() > 1) { // si + qu'un carac : exception
				addCharac(oldLigne, x, y); // remet l'ancien carac
				throw new ExceptionUnvalidSize();
			}
			else
				addCharac(newLigne, x, y);
		}
	}
	
	
	/**
	 * @param x
	 * @param y
	 * @return l'orientation de la ligne oblique (1,2,3,4)
	 */
	public int ligneOblique(int x, int y) {
		assert plan[y][x].getClass() == ElementLigneOblique.class;
		for (int i = 1; i <= 4; i++) {
			if (plan[y][x].orientation() == i)
				return i;
		}
		return 0;
	}
	
	public boolean isPossibleRotation(String angle, String ligne, int y, int x) {
		angle = angle.toUpperCase();
		int longueur = ligne.length();
		if (!angle.equals("H") && !angle.equals("V") && !angle.equals("1") && !angle.equals("2") && !angle.equals("3") && !angle.equals("4")){
			System.err.println("Entrez un angle correct. (H, V, 1, 2, 3, 4)");
			return false;
		}
		if (angle.equals("H"))
			return (x + longueur <= nbCol); // si ça dépasse, faux
		if (angle.equals("V"))
			return (y + longueur <= nbLig);
		
		// test col & lig
		if (angle.equals("1")) // NORD OUEST
			return y -longueur >= 0 && x -longueur >= 0;
			
		if (angle.equals("2")) // NORD EST
			return y - longueur >= 0 && x + longueur <= nbCol;
			
		if (angle.equals("3")) // SUD OUEST
			return y + longueur <= nbLig && x - longueur >= 0;
			
		if (angle.equals("4")) // SUD EST
			return y + longueur <= nbLig && x + longueur <= nbCol;
		
		return false;
	}
	
	
	/**
	 * @param ligne
	 * @param y
	 * @param x
	 * @return true si une rotation est possible sinon false
	 */
	public boolean oneRotationIsPossible(String ligne, int y, int x) {
		return (isPossibleRotation("H", ligne, y, x) ||
				isPossibleRotation("V", ligne, y, x) ||
				isPossibleRotation("1", ligne, y, x) ||
				isPossibleRotation("2", ligne, y, x) ||
				isPossibleRotation("3", ligne, y, x) ||
				isPossibleRotation("4", ligne, y, x));
	}
	/**
	 * @brief Cette version prend en compte le fait que les 2 classes doivent 
	 * être différentes pour ne pas prendre en compte un changement inutile.
	 * @param oldClass
	 * @param ligne
	 * @param y
	 * @param x
	 * @return
	 */
	public boolean oneRotationIsPossible(Class oldClass, String ligne, int y, int x) {
		return ((isPossibleRotation("H", ligne, y, x)  && isNotTheSameClass(oldClass, ElementLigne.class)) ||
				(isPossibleRotation("V", ligne, y, x) && isNotTheSameClass(oldClass, ElementLigneVerticale.class)) ||
				(isPossibleRotation("1", ligne, y, x) && isNotTheSameClass(oldClass, ElementLigneOblique.class)) ||
				(isPossibleRotation("2", ligne, y, x) && isNotTheSameClass(oldClass, ElementLigneOblique.class)) ||
				(isPossibleRotation("3", ligne, y, x) && isNotTheSameClass(oldClass, ElementLigneOblique.class)) ||
				(isPossibleRotation("4", ligne, y, x) && isNotTheSameClass(oldClass, ElementLigneOblique.class)));
		
	}
	
	public boolean isNotTheSameClass(Class a, Class b) {
		return !(a.equals(b));
	}
	
	public boolean isALigne(Element e) {
		if (e.getClass() == ElementLigne.class ||
			e.getClass() == ElementLigneOblique.class ||
			e.getClass() == ElementLigneVerticale.class)
				return true;
		else
			return false;
	}
	
	public void rotationVerticale(int y, int x) throws ExceptionUnvalidSize {
		assert plan[y][x].getClass() == ElementLigne.class; // les rotations sont appliquables seuelement aux lignes
		String ligne = plan[y][x].getLigne();
		if (isALigne(plan[y][x])) {
			deleteElement(y, x);
			addLigneVerticale(ligne, y, x);
		}
		else 
			throw new ExceptionUnvalidSize();
	}
	
	public void rotationHorizontale(int y, int x) throws ExceptionUnvalidSize {
		assert plan[y][x].getClass() == ElementLigne.class; 
		String ligne = plan[y][x].getLigne();
		if (isALigne(plan[y][x])) {
			deleteElement(y, x);
			addLigneHorizontale(ligne, y, x);
		}
		else 
			throw new ExceptionUnvalidSize();
	}
	
	public void rotationOblique(int y, int x) throws ExceptionUnvalidSize {
		assert plan[y][x].getClass() == ElementLigne.class; 
		String ligne = plan[y][x].getLigne();
		if (isALigne(plan[y][x])) {
			deleteElement(y, x);
			addLigneObliqueSudEst(ligne, y, x);
		}
		else 
			throw new ExceptionUnvalidSize();
	}
	
	public void rotationObliqueGauche(int y, int x) throws ExceptionUnvalidSize {
		assert plan[y][x].getClass() == ElementLigne.class; 
		String ligne = plan[y][x].getLigne();
		if (isALigne(plan[y][x])) {
			deleteElement(y, x);
			addLigneObliqueSudOuest(ligne, y, x);
		}
		else 
			throw new ExceptionUnvalidSize();
	}
	
	/**
	 * @param angle {H, V, 1, 2, 3, 4}
	 * @param y
	 * @param x
	 */
	public void rotation(String angle, int y, int x) {
		String ligne = plan[y][x].getLigne();
		angle = angle.toUpperCase();
		if (!angle.equals("H") && !angle.equals("V") && !angle.equals("1") && !angle.equals("2") && !angle.equals("3") && !angle.equals("4")){
			System.err.println("Entrez un angle correct. (H, V, 1, 2, 3, 4)");
			return ;
		}
		if (isALigne(plan[y][x])) {
			deleteElement(y, x);
			if (angle.equals("H")) // horizontale
				addLigneHorizontale(ligne, y, x);
			if (angle.equals("V")) // verticale
				addLigneVerticale(ligne, y, x);
			if (angle.equals("1")) // oblique gauche
				addLigneObliqueNordOuest(ligne, y, x);
			if (angle.equals("2")) // oblique gauche
				addLigneObliqueNordEst(ligne, y, x);
			if (angle.equals("3")) // oblique gauche
				addLigneObliqueSudOuest(ligne, y, x);
			if (angle.equals("4")) // oblique
				addLigneObliqueSudEst(ligne, y, x);
		}
		else 
			System.err.println("L'élément choisi n'est pas une ligne.");
	}
	
	/**
	 * @brief permet de gérer automatiquement la rotation d'un élément
	 * @param y
	 * @param x
	 */
	public void rotationAuto(int y, int x) {
		String ligne = plan[y][x].getLigne();
		Class ligneOblique = ElementLigneOblique.class;
		// vérification que les 2 classes sont différentes & mouvement possible
		Class oldClass = plan[y][x].getClass();
		if ( oneRotationIsPossible(oldClass, ligne, y, x) ) { // il est important de vérifier que la classe n'est pas la même que celle déjà présente, dans le cas contraire cela ne ferai aucun changement
			if (isPossibleRotation("H", ligne, y, x) && isNotTheSameClass(oldClass, ElementLigne.class))
				rotation("H", y, x);
			else if (isPossibleRotation("V", ligne, y, x) && isNotTheSameClass(oldClass, ElementLigneVerticale.class))
				rotation("V", y, x);
			else if (isPossibleRotation("1", ligne, y, x) && isNotTheSameClass(oldClass, ligneOblique))
				rotation("1", y, x);
			else if (isPossibleRotation("2", ligne, y, x) && isNotTheSameClass(oldClass, ligneOblique))
				rotation("2", y, x);
			else if (isPossibleRotation("3", ligne, y, x) && isNotTheSameClass(oldClass, ligneOblique))
				rotation("3", y, x);
			else if (isPossibleRotation("4", ligne, y, x) && isNotTheSameClass(oldClass, ligneOblique))
				rotation("4", y, x);
		}
		else
			System.err.println("Aucune rotation possible.");
	}
	
	public void encadrer(char charac, int y, int x) {
		Class initialClass = checkElement(x, y).getClass();
		int length = checkElement(x, y).getLigne().length();

		if (initialClass == ElementLigneOblique.class) {
			// OK
			if (plan[y][x].orientation() == 4) { // SUD EST
				for (int i = y - 4; i <= y + length- 1 + 4; i++) { 
					for (int j = x - 4 ; j <= x + 4 + length - 1; j++) {
						if (checkElement(j, i).getClass() != initialClass)
							placerElement(new ElementCharac(charac), j, i);
					}
				}
			}
			// OK
			else if (plan[y][x].orientation() == 3) { // SUD OUEST
				for (int i = y - 4; i <= y + length- 1 + 4; i++) { 
					for (int j = x - length + 1 -4 ; j <= x + 4; j++) {
						if (checkElement(j, i).getClass() != initialClass)
							placerElement(new ElementCharac(charac), j, i);
					}
				}
			}
			// OK
			else if (plan[y][x].orientation() == 1) { // NORD OUEST
				for (int i = y - 4 - length + 1; i <= y + 4 ; i++) { 
					for (int j = x -length +1 -4 ; j <= x + 4; j++) {
						if (checkElement(j, i).getClass() != initialClass)
							placerElement(new ElementCharac(charac), j, i);
					}
				}
			}
			// OK
			else if (plan[y][x].orientation() == 2) { // NORD EST
				for (int i = y - 4 - length + 1; i <= y + 4 ; i++) { 
					for (int j = x - 4 ; j <= x + length - 1+ 4; j++) {
						if (checkElement(j, i).getClass() != initialClass)
							placerElement(new ElementCharac(charac), j, i);
					}
				}
			}
		}
		// OK
		if (initialClass == ElementLigneVerticale.class) {
			for (int i = y - 4; i <= y + 4 + length - 1; i++) {
				for (int j = x - 4 ; j <= x + 4 ; j++) {
					if (checkElement(j, i).getClass() != initialClass)
						placerElement(new ElementCharac(charac), j, i);
				}
			}
		}
		// OK
		if (initialClass == ElementLigne.class) {
			for (int i = y - 4; i <= y + 4; i++) {
				for (int j = x - 4 ; j <= x + 4 + length - 1 ; j++) {
					if (checkElement(j, i).getClass() != initialClass)
						placerElement(new ElementCharac(charac), j, i);
				}
			}
		}
		// OK
		if (initialClass == ElementCharac.class) {
			for (int i = y - 4; i <= y + 4 ; i++) {
				for (int j = x - 4; j <= x + 4; j++) {
					if (checkElement(j, i).getClass() != initialClass)
						placerElement(new ElementCharac(charac), j, i);
				}
			}
		}
	}
	
	
	public void moveCharac(int oldX, int oldY, int newX, int newY) {
		Element clone = checkElement(oldX, oldY); // copie de l'élément
		try {
			placerElement(clone, newX, newY); // placement à la new position
		} catch(Exception e) {
			System.err.println("Case déjà occupée par un élement ou élement trop grand."
					+ e.getMessage() + e.getStackTrace());
		}
		plan[oldY][oldX].destroyElement(); // suppresion de l'ancien
	}
	
	public void modifierValCharac(String charac, int x, int y) 
			throws ExceptionUnvalidElement {
		if (checkElement(x, y).getClass() != ElementCharac.class)
			throw new ExceptionUnvalidElement();
		
		plan[y][x].modifyValue(charac);
	}
	
	public void modifierValLigne(String newLigne, int x, int y) throws ExceptionUnvalidElement {
		plan[y][x].modifyValue(newLigne);
	}
	
	public void placerElement(Element e, int x , int y) {
		plan[y][x] = e;
	}
	
	public void deleteElement(int x, int y) {
		plan[x][y].destroyElement();
		// remplace la val de l'élement par une chaîne vide
	}
	
	/* Retourne l'élément voulu */
	public Element checkElement(int x, int y) {
		return plan[y][x];
	}
	
	
	/**
	 * Permet le reset du compteur de ligne oblique a chaque invocation de la méthode
	 * évaluer(). Elle est essentielle à l'affichage des lignes obliques
	 */
	public void resetAllCompteurs() {
		for (int i = 0; i < nbLig; i++)
			for (int j = 0; j < nbCol; j++){
					plan[i][j].resetCompteur();
			}
	}

	/**
	 * @brief Affiche l'image dans la console
	 */
	public void afficherPlan() {
		int cpt = 0; // permet affichage en forme de tableau x/y
		resetAllCompteurs();
		for (int i = 0; i < nbLig; i++)
			for (int j = 0; j < nbCol; j++){
				cpt++;
				System.out.print(plan[i][j].évaluer());
				if (cpt == nbCol) { 
					// quand le compteur arrive a la derniere col
					// on effectue un \n puis on reinitialise le cpt
					System.out.println();
					cpt = 0;
				}
			}
	}
	
	/**
	 * @return l'image dans un String <=> toString()
	 */
	public String getPlan() {
		String img = "";
		resetAllCompteurs();
		int cpt = 0; // permet affichage en forme de tableau x/y
		for (int i = 0; i < nbLig; i++)
			for (int j = 0; j < nbCol; j++){
				cpt++;
				img += plan[i][j].évaluer();
				if (cpt == nbCol) { 
					// quand le compteur arrive a la derniere col
					// on effectue un \n puis on reinitialise le cpt
					// le \n ne marchant pas sur bloc note on utilise cette méthode
					img += System.getProperty("line.separator");
					cpt = 0;
				}
			}
		return img;
	}
	
	public void setPlan(Element[][] plan) {
		this.plan = plan;
	}
	
	public Element[][] getElementTab() {
		return this.plan;
	}

	public int getNbCol() {
		return nbCol;
	}

	public void setNbCol(int nbCol) {
		this.nbCol = nbCol;
	}

	public int getNbLig() {
		return nbLig;
	}

	public void setNbLig(int nbLig) {
		this.nbLig = nbLig;
	}

}
