package PSO.utilities.scoring;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;

/**
 * Dice.java Clase para calcular la metrica de Sorensen-Dice. 2 * |A intersect
 * B| / ( |A| + |B| ) Fecha de modificacion: 31/1/2018
 * 
 * @author Joel Barrantes
 * 
 */

public class Dice {

	/**
	 * Metodo para calcular el indice de similitud de Dice entre dos imagenes
	 * Mat. Segun formula original:
	 * wikipedia.org/wiki/Coeficiente_de_Sorensen-Dice
	 * 
	 * @param umbralized
	 *            Mat con imagen umbralizada
	 * @param groundtruth
	 *            Mat con imagen groundtruth
	 * @return resultado de indice Sorensen-Dice
	 */
	public static double calculateDice(Mat umbralized, Mat groundtruth) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		float intersection = 0;
		int cardinalityA = umbralized.rows() * umbralized.cols();
		int cardinalityB = groundtruth.rows() * groundtruth.cols();
		
		Size sizeGroundTruth = groundtruth.size();
		int sizeX = (int) sizeGroundTruth.width;
		int sizeY = (int) sizeGroundTruth.height;

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				double[] pixelU = umbralized.get(y, x);
				double[] pixelG = groundtruth.get(y, x);
				if (pixelU[0] == pixelG[0]) {
					intersection++;
				}
			}
		}
		return (2.0 * intersection) / ((double) cardinalityA + (double) cardinalityB);
	}
}
