package démo;

import java.io.IOException;
import ressources.ExceptionUnvalidElement;
import ressources.ExceptionUnvalidSize;
import ressources.Film;
import ressources.Image;

public class Appli {

	public static void main(String... args) throws ExceptionUnvalidElement, ExceptionUnvalidSize {
		
		try {
			
			Film film = new Film("Film", 40, 40);

			/* DEBUT DU FILM */
			
			/* ---------- SEQUENCE N°1 ---------- */
			
			film.filmManager(); /* instancie la première image */
			/* placements et encadrements */
			
			
			film.addLigneHorizontaleV2("Paul", 4, 4);
			film.encadrerV2('+', 4, 4);
			film.addLigneHorizontaleV2("Marc", 32, 4);
			film.encadrerV2('+', 32, 4);
			film.addLigneHorizontaleV2("Alix", 4, 35);
			film.encadrerV2('+', 4, 35);
			film.addLigneHorizontaleV2("José", 32, 35);
			film.encadrerV2('+', 32, 35);
			film.addCharac("o", 19, 19);
			film.insertLastImgIntoFile();
//			film.filmManager();
//			for (int i = 0; i < 8; i++)
//				for (int j = 0; j < 12; j++)
//					film.déplacerElementV2(j, i, j+ 1, i+1);
//			film.insertLastImgIntoFile();
			film.déplacerElement(4, 4, 5, 5);
			film.déplacerElement(32, 4, 31, 5);
			film.déplacerElement(4, 35, 5, 34);
			film.déplacerElement(32, 35, 31, 34);
			
			film.addCharac("O", 9, 5);
			film.addCharac("O", 30, 5);
			
			film.addCharac("O", 9, 34);
			film.addCharac("O", 30, 34);
			for (int i = 0, j = 1; i <= 9; i = i + 1, j++) {
					film.déplacerElement(9 + i, 5 + i, 9 + i + 1, 5 + i + 1);
			}
			
			film.déplacerElement(19, 15, 19, 16);
			film.déplacerElement(19, 16, 19, 17);
			film.déplacerElement(19, 17, 19, 18);
			film.déplacerElement(19, 18, 19, 19);
			
			// séquence n°2
			
			film.addLigneHorizontaleV2("j'ai gagné ",0 , 13);
			film.addLigneHorizontaleV2("vive la pétanque",11 , 13);
			film.insertLastImgIntoFile();
			
			// séquence n°3
			
			film.filmManager();
			film.addLigneObliqueNordEstV2("+++++++", 13, 21);
			film.addLigneObliqueNordOuestV2("+++++++", 25, 21);
			film.addLigneHorizontaleV2("+++++++", 16, 19);
			film.insertLastImgIntoFile();
			
			film.filmManager();
			film.encadrerV2('°', 13, 21);
			film.encadrerV2('°', 25, 21);
			film.encadrerV2('°', 16, 19);
			film.addLigneObliqueNordEstV2("+++++++", 13, 21);
			film.addLigneObliqueNordOuestV2("+++++++", 25, 21);
			film.addLigneHorizontaleV2("+++++++", 16, 19);
			film.insertLastImgIntoFile();
			
			// déplacements à revoir (manque de temps)
			for ( int i = 0, j = 0; i <= 10; i++, j++) {
				film.filmManager();
				film.déplacerElement(13 + i, 21, 13 + i + 1, 21);
				film.déplacerElement(25 + i, 21, 25 + i, 21);
				film.déplacerElement(16 + i, 19, 16 + i, 19);
				film.insertLastImgIntoFile();
			}
			// on rajoute un '+'
			film.modifierElement("++++++++", 16, 19);
			
			
			/* FIN DU FILM */
			
			film.closeFile(); // important
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
