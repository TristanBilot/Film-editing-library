package ressources;

public class ExceptionUnvalidElement extends Exception {

private static final long serialVersionUID = 1L;

	public ExceptionUnvalidElement() {
		super("\n\nL'élément n'est pas compatible.\n");
	}
	
	public ExceptionUnvalidElement(String message) {
		super("\n" + message + "\n");
	}
}