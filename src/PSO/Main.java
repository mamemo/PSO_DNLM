package PSO;

import java.io.IOException;

import org.opencv.core.Mat;

import PSO.utilities.images.ImageHandler;

/**
 * Main.java Clase encargada de ejecutar el algoritmo de Particle Swarm Optimization.
 * Fecha de creacion: 31/1/2018
 * 
 * @author Mauro Mendez
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {		
		PSOSettings settings = new PSOSettings();
		ImageHandler imageHandler = new ImageHandler();
		PSO pso = new PSO(settings);

		settings.setLowerW(1);
		settings.setUpperW(21);
		
		settings.setLowerWn(3);
		settings.setUpperWn(3);
		
		settings.setLowerSigmaR(1);
		settings.setUpperSigmaR(500);
		
		settings.setLowerLambda(1);
		settings.setUpperLambda(30);


		String absdir = "";
		if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
			absdir = "/home/jdnlm/DnlmTests/PSO_DNLM/";
		}
		
		String dirOriginal = absdir+"src/PSO/utilities/images/original/";
		String dirGt = absdir+"src/PSO/utilities/images/groundtruth/";

		//ORIGINALES
		Mat imagen = imageHandler.leerImagenGrises(dirOriginal + "mcf-z-stacks-03212011_a13_s2_original.tif");
		settings.addToOriginalImages(imagen);
		
		Mat imagen2 = imageHandler.leerImagenGrises(dirOriginal + "mcf-z-stacks-03212011_b02_s2_original.tif");
		settings.addToOriginalImages(imagen2);
		
		Mat imagen3 = imageHandler.leerImagenGrises(dirOriginal + "mcf-z-stacks-03212011_b06_s1_original.tif");
		settings.addToOriginalImages(imagen3);
		
		Mat imagen4 = imageHandler.leerImagenGrises(dirOriginal + "mcf-z-stacks-03212011_b15_s1_original.tif");
		settings.addToOriginalImages(imagen4);
		
		//GT
		Mat imagengt = imageHandler.leerImagenGrises(dirGt + "mcf-z-stacks-03212011_a13_s2.png");
		settings.addToGroundtruthImages(imagengt);
		
		Mat imagen2gt = imageHandler.leerImagenGrises(dirGt + "mcf-z-stacks-03212011_b02_s2.png");
		settings.addToGroundtruthImages(imagen2gt);
		
		Mat imagen3gt = imageHandler.leerImagenGrises(dirGt + "mcf-z-stacks-03212011_b06_s1.png");
		settings.addToGroundtruthImages(imagen3gt);
		
		Mat imagen4gt = imageHandler.leerImagenGrises(dirGt + "mcf-z-stacks-03212011_b15_s1.png");
		settings.addToGroundtruthImages(imagen4gt);
		
		int individuos = 10;
		individuos = Integer.parseInt(args[0]);
		settings.setNum_individuals(individuos);
		settings.setNum_iterations(50);
		settings.setC_Personal(2);
		settings.setC_Global(2);
		settings.setInertia_min(0.4);
		settings.setInertia_max(0.9);
		settings.setCorridas(1);
		
		pso.runOptimization();
	}
	
	
}
