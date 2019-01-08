package test;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import ressources.ExceptionUnvalidSize;
import ressources.Film;

public class UnitTest {

	@Test
	public void test() throws IOException, ExceptionUnvalidSize {
		
		Film film = new Film("Test", 60, 60); 
		
		film.addCharac("O", 41, 41);
		film.addLigneVerticale("verticale", 15, 15);
		film.encadrer('0', 15, 15);
		film.addLigneHorizontale("hori", 30, 30);
		film.encadrer('-', 30, 30);
		film.encadrer('^', 41, 41);
		film.deleteElement(30, 30);
		
		film.addLigneVerticale("test", 12, 12);
		film.addLigneVerticale("test2", 5, 11);
		film.addLigneVerticale("test3", 11, 11);
		film.addLigneObliqueSudEst("oblique", 40, 10);
		film.déplacerElement(40, 10, 30, 3);
		film.rotationAuto(30, 3);
		film.rotation("4", 30, 3);
		film.addCharac("v", 20, 20);
		film.encadrer('a', 20, 20);
		
		film.addLigneObliqueSudOuest("gauche", 18, 18);
		film.addLigneObliqueNordEst("nordEst", 40, 10);
		film.addLigneObliqueNordOuest("Ouest", 59, 10);
		film.addLigneObliqueSudEst("TEST",30, 30);
		film.rotation("2", 30, 30); 
		film.encadrer('°', 30, 30);
		
		film.addCharac("a", 5, 2);
		film.addLigneHorizontale("horiz", 5, 4);
		film.rotationAuto(5, 4);
		film.addLigneObliqueNordEst("nord-est", 10, 10);
		film.modifierElement("new", 10, 10);

		assertTrue(film.lastImgOfFilm().oneRotationIsPossible("rotation", 10, 10));
		film .addLigneVerticale("Impossible", 59, 49);
		assertFalse(film.lastImgOfFilm().isPossibleRotation("H", "rotation", 49, 59));
		
		
		film.closeFile();

	}
}
