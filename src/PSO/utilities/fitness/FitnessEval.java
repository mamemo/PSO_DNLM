package PSO.utilities.fitness;

import java.util.Hashtable;

import org.opencv.core.Mat;
import PSO.utilities.filter.DnlmFilter;
import PSO.utilities.scoring.Dice;
import PSO.utilities.segmentation.Thresholder;
import PSO.ParamIndividual;
import PSO.PSOSettings;

public class FitnessEval {

	public void calcularFitness(PSOSettings settings, ParamIndividual p, Hashtable<String, Double> params) {
		double score = 0;
		String key = p.toString();
		Double value = params.get(key);

		if (value != null) {
			// if the value is in the table, don't calculate it again
			score = value.doubleValue();
		} else {
			// calculate fitness score for every (image, ground_truth) pair
			// provided
			double fit = 0.0;
			System.out.println("\n");
			for (int index = 0; index < settings.getSampleCount(); index++) {
				System.out.print("Indice "+Integer.toString(index)+": ");
				fit = evaluate(p, settings.getOriginalImage(index), settings.getGroundtruthImage(index));
				System.out.println(Double.toString(fit));
				score += fit;
				
			}
			// calculate the mean score for the samples
			score = score / (double) settings.getSampleCount();

			// save it to the params set
			params.put(key, score);
		}
		p.setFitness(score);
	}

	private double evaluate(ParamIndividual p, Mat pOriginal, Mat pGroundtruth) {

		int w = p.getW();
		int w_n = p.getW_n();
		int sigma_r = p.getSigma_r();
		float lambda = p.getLambda();

		Mat original = new Mat();
		pOriginal.copyTo(original);

		// filter the image with DNLM-IDFT
		DnlmFilter filter = new DnlmFilter();
		Mat filteredImage = filter.filter(original, w, w_n, sigma_r, lambda);

		// cut black borders and apply same transformation to groundtruth
		int snipping = w + w_n;
		filteredImage = filteredImage.submat(snipping, filteredImage.rows() - snipping - 2, snipping,
				filteredImage.cols() - snipping - 2);
		pGroundtruth = pGroundtruth.submat(snipping, pGroundtruth.rows() - snipping - 2, snipping,
				pGroundtruth.cols() - snipping - 2);

		// segmentation of the filtered image
		filteredImage = applySegmentation(filteredImage);

		// calculate fitness with the specified similarity check function
		double fitness = getFitnessResult(filteredImage, pGroundtruth);

		original.release();
		filteredImage.release();

		return fitness;
	}

	private Mat applySegmentation(Mat image) {
		Thresholder.applyOtsuThreshold(image);
		return image;
	}

	private double getFitnessResult(Mat image, Mat groundtruth) {
		return Dice.calculateDice(image, groundtruth);
	}
}
