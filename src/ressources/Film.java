package ressources;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import elements.Element;

public class Film {
	
	private ArrayList<Image> images;
	PrintWriter file;
	private int nbCol;
	private int nbLig;
	
	public Film(String fileName) throws IOException {
		images = new ArrayList<Image>();
		newFile(fileName); // nouveau fichier
		// file.close(); // !!!
	}
	
	/**
	 * @param fileName
	 * @param nbLig
	 * @param nbCol
	 * @throws IOException
	 * @brief Créer directement le film dans le fichier voulu
	 */
	public Film(String fileName, int nbLig, int nbCol) throws IOException {
		this(fileName);
		this.nbCol = nbCol;
		this.nbLig = nbLig;
		// afficher le nb de lignes et de col en début de fichier
		file.println(this.nbLig + " " + this.nbCol);
	}
	
	/**
	 * Ajouter tout le film au fichier
	 */
	public void save() {
		for (Image i : images) {
			file.println(i.getPlan());
			file.println("\\newframe");
		}
		file.close(); // !!!
	}
	
	public void newFile(String fileName) throws IOException {
		// création du fichier avec ajout de l'extension .txt par défaut
		file = new PrintWriter(fileName + ".txt");
	}
	
	
	public void newImage(int nbCol, int nbLig) {
		images.add(new Image(nbCol, nbLig));
	}
	
	public Image getImage(int index) {
		return images.get(index);
	}
	
	/**
	 * @param image l'image a ajouter a l'arraylist
	 * Permet d'ajouter une image dans le film
	 */
	public void addImage(Image image) {
		images.add(image);
	}
	
	/**
	 * @param image l'image a ajouter a l'arraylist
	 * Permet d'ajouter une image dans le fichier
	 */
	public void addImageToFile(Image image) {
		file.println(image);
	}
	
	public void afficherImages() {
		for (Image i : images){
			System.out.println(i.getPlan());
			System.out.println("\\newframe");
		}
	}
	
	public void afficherImage(int index) {
		System.out.println(images.get(index).getPlan());
	}
	
	public int getNbOfImages() {
		return images.size();
	}
	
	
	// cette méthode gère le cas de la première image de la liste
	// vu qu'on utilise l'image précédente, il y a erreur s'il n'y en existe pas
	public boolean firstImageIsEmpty() {
		return (images.isEmpty());
	}
	
	public void checkFirstImage() {
		if (firstImageIsEmpty())
			newImage(nbCol, nbLig);
	}
	
	public Image beforeLastImgOfFilm() {
		return images.get(images.size() - 2);
	}
	
	public Image lastImgOfFilm() {
		return images.get(images.size() - 1);
	}
	
	public void addImgToFilm() {
		images.add(new Image(nbCol, nbLig));
		lastImgOfFilm().cloneImage(beforeLastImgOfFilm());
	}
	
	public void insertLastImgIntoFile() {
		file.println(lastImgOfFilm().getPlan());
		file.println("\\newframe");
	}
	
	public void closeFile() {
		file.close();
	}
	
	public void filmManager() {
		checkFirstImage();
		addImgToFilm();
	}
	/* méthodes pour plusieurs modifications par image (manuel) */
	public void ligneVerticaleV2(String value, int x, int y) {
		lastImgOfFilm().addLigneVerticale(value, y, x);
	}
	public void characV2(String value, int y, int x) throws ExceptionUnvalidSize {
		lastImgOfFilm().addCharac(value, y, x);
	}
	public void encadrerV2(char charac, int x, int y) {
		lastImgOfFilm().encadrer(charac, y, x);
	}
	public void déplacerElementV2(int y, int x, int newY, int newX) throws ExceptionUnvalidSize {
		lastImgOfFilm().déplacerElement(y, x, newX, newY);
	}
	public void addLigneHorizontaleV2(String value, int x, int y) {
		lastImgOfFilm().addLigneHorizontale(value, y, x);
	}
	public void addLigneObliqueNordOuestV2(String value, int x, int y) {
		lastImgOfFilm().addLigneObliqueNordOuest(value, y, x);
	}
	public void addLigneObliqueNordEstV2(String value, int x, int y) {
		lastImgOfFilm().addLigneObliqueNordEst(value, y, x);
	}
	/* méthodes à une modification par image (auto) */
	public void addLigneVerticale(String value, int x, int y) {
		filmManager();
		lastImgOfFilm().addLigneVerticale(value, y, x);
		insertLastImgIntoFile();
	}
	public void addLigneHorizontale(String value, int x, int y) {
		filmManager();
		lastImgOfFilm().addLigneHorizontale(value, y, x);
		insertLastImgIntoFile();
	}
	public void addLigneObliqueNordOuest(String value, int x, int y) {
		filmManager();
		lastImgOfFilm().addLigneObliqueNordOuest(value, y, x);
		insertLastImgIntoFile();
	}
	public void addLigneObliqueNordEst(String value, int x, int y) {
		filmManager();
		lastImgOfFilm().addLigneObliqueNordEst(value, y, x);
		insertLastImgIntoFile();
	}
	public void addLigneObliqueSudOuest(String value, int x, int y) {
		filmManager();
		lastImgOfFilm().addLigneObliqueSudOuest(value, y, x);
		insertLastImgIntoFile();
	}
	public void addLigneObliqueSudEst(String value, int x, int y) {
		filmManager();
		lastImgOfFilm().addLigneObliqueSudEst(value, y, x);
		insertLastImgIntoFile();
	}
	public void addCharac(String value, int y, int x) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().addCharac(value, y, x);
		insertLastImgIntoFile();
	}
	public void addText(String value, int sizeOfLine, int y, int x) throws ExceptionUnvalidSize {
		value = "$" + value; // pour supprimer le premier caractère en trop : obligatoire
		filmManager();
		lastImgOfFilm().addText(value, sizeOfLine, y, x);
		insertLastImgIntoFile();
	}
	public void addExtraitText(int x, int y, int xHautGauche, int yHautGauche, int xBasDroite, int yBasDroite, int newX, int newY) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().addExtraitText( x,  y,  xHautGauche,  yHautGauche,  xBasDroite,  yBasDroite,  newX,  newY);
		insertLastImgIntoFile();
	}
	public void déplacerElement(int y, int x, int newY, int newX) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().déplacerElement(y, x, newX, newY);
		insertLastImgIntoFile();
	}
	public void modifierElement(String newLigne, int x, int y) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().modifierElement(newLigne, y, x);
		insertLastImgIntoFile();
	}
	public void deleteElement(int y, int x) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().deleteElement(x, y);
		insertLastImgIntoFile();
	}
	public void rotationHorizontale(int x, int y) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().rotationHorizontale(y, x);
		insertLastImgIntoFile();
	}
	public void rotationVerticale(int x, int y) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().rotationVerticale(y, x);
		insertLastImgIntoFile();
	}
	public void rotationOblique(int x, int y) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().rotationOblique(y, x);
		insertLastImgIntoFile();
	}
	public void rotationObliqueGauche(int x, int y) throws ExceptionUnvalidSize {
		filmManager();
		lastImgOfFilm().rotationOblique(y, x);
		insertLastImgIntoFile();
	}
	public void rotation(String angle, int x, int y) {
		filmManager();
		lastImgOfFilm().rotation(angle, y, x);
		insertLastImgIntoFile();
	}
	public void rotationAuto(int x, int y) {
		filmManager();
		lastImgOfFilm().rotationAuto(y, x);
		insertLastImgIntoFile();
	}
	public void encadrer(char charac, int x, int y) {
		filmManager();
		lastImgOfFilm().encadrer(charac, y, x);
		insertLastImgIntoFile();
	}
	
	public Element checkElement(int x, int y) {
		return lastImgOfFilm().checkElement(x, y);
	}
	
	public void modifierValCharac(String charac, int x, int y) throws ExceptionUnvalidElement {
		lastImgOfFilm().modifierValCharac(charac, x, y);
	}

}
