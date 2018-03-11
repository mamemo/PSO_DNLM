package PSO;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;

import PSO.utilities.segmentation.Thresholder;

/**
 * Clase para setear valores del algoritmo. Fecha de creacion: 31/1/2018
 * 
 * @author Mauro Mendez.
 *
 */

public class PSOSettings {

	/** Initial parameter ranges **/

	private int lowerSigmaR;
	private int upperSigmaR;

	private int lowerW;
	private int upperW;

	private int lowerWn;
	private int upperWn;
	
	private float lowerLambda;
    private float upperLambda;
	
    private int num_iterations;
    private int num_individuals;
    private int C_Personal;
    private int C_Global;
    private double Inertia_min;
    private double Inertia_max;
    
	private int corridas;
	private String nombreArchivo;

	// images to filter and check against the ground truth
	private List<Mat> originalImages;

	// the ground truth
	private List<Mat> groundtruthImages;

	public PSOSettings() {
		originalImages = new ArrayList<Mat>();
		groundtruthImages = new ArrayList<Mat>();
	}

	public int getSampleCount() {
		return originalImages.size();
	}

	public Mat getOriginalImage(int index) {
		return originalImages.get(index);
	}

	public void setOriginalImages(List<Mat> oList) {
		this.originalImages = oList;
	}

	public void addToOriginalImages(Mat imagen) {
		originalImages.add(imagen);
	}

	public Mat getGroundtruthImage(int index) {
		return groundtruthImages.get(index);
	}

	public void setGroundtruthImages(List<Mat> gList) {
		this.groundtruthImages = gList;
	}

	public void addToGroundtruthImages(Mat imagen) {
		Thresholder.applyThreshold(imagen, 1);
		groundtruthImages.add(imagen);
	}

	public int getLowerSigmaR() {
		return lowerSigmaR;
	}

	public void setLowerSigmaR(int lowerSigmaR) {
		this.lowerSigmaR = lowerSigmaR;
	}

	public int getUpperSigmaR() {
		return upperSigmaR;
	}

	public void setUpperSigmaR(int upperSigmaR) {
		this.upperSigmaR = upperSigmaR;
	}

	public int getLowerW() {
		return lowerW;
	}

	public void setLowerW(int lowerW) {
		this.lowerW = lowerW;
	}

	public int getUpperW() {
		return upperW;
	}

	public void setUpperW(int upperW) {
		this.upperW = upperW;
	}

	public int getLowerWn() {
		return lowerWn;
	}

	public void setLowerWn(int lowerWn) {
		this.lowerWn = lowerWn;
	}

	public int getUpperWn() {
		return upperWn;
	}

	public void setUpperWn(int upperWn) {
		this.upperWn = upperWn;
	}

	public float getLowerLambda() {
		return lowerLambda;
	}

	public void setLowerLambda(float lowerLambda) {
		this.lowerLambda = lowerLambda;
	}

	public float getUpperLambda() {
		return upperLambda;
	}

	public void setUpperLambda(float upperLambda) {
		this.upperLambda = upperLambda;
	}

	public int getCorridas() {
		return corridas;
	}

	public void setCorridas(int corridas) {
		this.corridas = corridas;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public int getNum_iterations() {
		return num_iterations;
	}

	public void setNum_iterations(int num_iterations) {
		this.num_iterations = num_iterations;
	}

	public int getNum_individuals() {
		return num_individuals;
	}

	public void setNum_individuals(int num_individuals) {
		this.num_individuals = num_individuals;
	}

	public int getC_Personal() {
		return C_Personal;
	}

	public void setC_Personal(int c_Personal) {
		C_Personal = c_Personal;
	}

	public int getC_Global() {
		return C_Global;
	}

	public void setC_Global(int c_Global) {
		C_Global = c_Global;
	}

	public double getInertia_min() {
		return Inertia_min;
	}

	public void setInertia_min(double inertia_min) {
		Inertia_min = inertia_min;
	}

	public double getInertia_max() {
		return Inertia_max;
	}

	public void setInertia_max(double inertia_max) {
		Inertia_max = inertia_max;
	}	
}