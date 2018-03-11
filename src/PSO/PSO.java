package PSO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

import PSO.utilities.fitness.FitnessEval;

public class PSO {

	private final PSOSettings settings;
	private Hashtable<String, Double> params;
	private final FitnessEval fitEval;
	private ArrayList<ParamIndividual> particulas;

	public PSO(PSOSettings settings) {
		this.settings = settings;
		this.params = new Hashtable<String, Double>();
		this.fitEval = new FitnessEval();
	}

	public void runOptimization() {

		reporte();

		for (int corr = 1; corr <= settings.getCorridas(); corr++) {
			System.out.println("\n###########################################\r\n" + "Corrida: " + String.valueOf(corr)
					+ " Empieza: " + LocalDateTime.now());

			//Inicializacion
			this.particulas = new ArrayList<ParamIndividual>();
			for (int i = 0; i < settings.getNum_individuals(); i++) {
				this.particulas.add(new ParamIndividual(settings));
			}
			for (ParamIndividual p : this.particulas) {
				fitEval.calcularFitness(settings, p, params);
				p.setP_fitness(p.getFitness());
				p.actualizarMejorGlobal();
			}
			
			//Algoritmo
			for (int iter = 0; iter < settings.getNum_iterations(); iter++) {
				System.out.println("Iteracion: " + String.valueOf(iter + 1));

				double inertia = settings.getInertia_max()
						- ((settings.getInertia_max() - settings.getInertia_min()) / (settings.getNum_iterations()))
								* iter;
				for (ParamIndividual p : this.particulas) {
					p.nuevaVelocidad(inertia, settings);
					p.nuevaPosicion(settings);
					fitEval.calcularFitness(settings, p, params);
					p.actualizarMejorLocal();
					p.actualizarMejorGlobal();
					System.out.println(p.toString()+ " Tiempo: " + LocalDateTime.now());
				}
				
				System.out.println("Mejor: "+ParamIndividual.toStringGlobal());
			} //End Iteraciones

		}//End corridas

	}

	private void reporte() {
		
		System.out.println("\n\nMinimo W: " + String.valueOf(settings.getLowerW()) +
				"\n" + "Maximo W: " + String.valueOf(settings.getUpperW()) +
			"\n" + "Minimo W_n: " + String.valueOf(settings.getLowerWn()) +
			"\n" + "Maximo W_n: " + String.valueOf(settings.getUpperWn()) +
			"\n" + "Minimo Sigma_R: " + String.valueOf(settings.getLowerSigmaR()) +
			"\n" + "Maximo Sigma_R: " + String.valueOf(settings.getUpperSigmaR()) +
			"\n" + "Minimo Lambda: " + String.valueOf(settings.getLowerLambda()) +
			"\n" +  "Maximo Lambda: " + String.valueOf(settings.getUpperLambda()) +
			"\n" + "Iteraciones: " + String.valueOf(settings.getNum_iterations())+
			"\n" + "Individuos: " + String.valueOf(settings.getNum_individuals())+
			"\n" + "Corridas: " + String.valueOf(settings.getCorridas()));
	}

}
