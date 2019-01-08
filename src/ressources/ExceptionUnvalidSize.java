package ressources;

public class ExceptionUnvalidSize extends Exception {

	
	private static final long serialVersionUID = 1L;

	public ExceptionUnvalidSize() {
		super("\n\nLa taille utilisée est incorrecte.\n");
	}
	
	public ExceptionUnvalidSize(String message) {
		super("\n\n" + message + "\n");
	}
}