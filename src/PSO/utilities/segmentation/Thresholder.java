package PSO.utilities.segmentation;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Thresholder.java Clase experta en umbralizacion de las imagenes Fecha de
 * modificacion: 31/1/2018
 * 
 * @author Joel Barrantes
 */

public class Thresholder {

	/**
	 * Umbraliza la imagen con OpenCV utilizando el algoritmo de Otsu.
	 * 
	 * @param image
	 *            matriz OpenCV de la imagen
	 */
	public static void applyOtsuThreshold(Mat image) {
		image.convertTo(image, CvType.CV_8UC1);
		Imgproc.threshold(image, image, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);
	}

	/**
	 * Umbraliza la image con OpenCV de forma binaria.
	 * 
	 * @param image
	 *            matriz OpenCV de la imagen
	 * @param t
	 *            valor de umbral para definir blancos y negros
	 */
	public static void applyThreshold(Mat image, int t) {
		Imgproc.threshold(image, image, t, 255, Imgproc.THRESH_BINARY);
	}

	/**
	 * Umbraliza la image con OpenCV de forma binaria e inversa
	 * 
	 * @param image
	 *            matriz OpenCV de la imagen
	 * @param t
	 *            valor de umbral para definir blancos y negros
	 */
	public static void applyInverseThreshold(Mat image, int t) {
		Imgproc.threshold(image, image, t, 255, Imgproc.THRESH_BINARY_INV);
	}
}
