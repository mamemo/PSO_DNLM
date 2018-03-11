package PSO;

import java.util.Random;


/**
 * Clase que contiene el individuo a crearse para calibrar los parametros. Fecha
 * de creacion: 30/1/2018
 * 
 * @author Mauro Mendez.
 *
 */

public class ParamIndividual implements Comparable<ParamIndividual> {

	private static int g_w;
	private static int g_w_n;
	private static int g_sigma_r;
	private static float g_lambda;
	private static double g_fitness;

	private int w;
	private int w_n;
	private int sigma_r;
	private float lambda;
	private double fitness;

	private int p_w;
	private int p_w_n;
	private int p_sigma_r;
	private float p_lambda;
	private double p_fitness;

	private int v_w;
	private int v_w_n;
	private int v_sigma_r;
	private float v_lambda;

	private final Random random = new Random();

	// Crea Parametros Aleatorios
	public ParamIndividual(PSOSettings settings) {
		this.setFitness(0);
		this.setW(getRandomIntegerBetween(settings.getLowerW(), settings.getUpperW()));
		this.w = (this.getW() % 2 == 0) ? this.getW() + 1 : this.getW();
		this.setW_n(getRandomIntegerBetween(settings.getLowerWn(), settings.getUpperWn()));
		this.w_n = (this.getW_n() % 2 == 0) ? this.getW_n() + 1 : this.getW_n();
		this.setSigma_r(getRandomIntegerBetween(settings.getLowerSigmaR(), settings.getUpperSigmaR()));
		this.setLambda(getRandomFloatBetween(settings.getLowerLambda(), settings.getUpperLambda()));

		this.setP_w(this.getW());
		this.setP_w_n(this.getW_n());
		this.setP_sigma_r(this.getSigma_r());
		this.setP_lambda(this.getLambda());
		this.setP_fitness(this.getFitness());

		this.setV_w(getRandomIntegerBetween(settings.getLowerW(), settings.getUpperW()));
		this.v_w = (this.getV_w() % 2 == 0) ? this.getV_w() + 1 : this.getV_w();
		this.setV_w_n(getRandomIntegerBetween(settings.getLowerWn(), settings.getUpperWn()));
		this.v_w_n = (this.getV_w_n() % 2 == 0) ? this.getV_w_n() + 1 : this.getV_w_n();
		this.setV_sigma_r(getRandomIntegerBetween(settings.getLowerSigmaR(), settings.getUpperSigmaR()));
		this.setV_lambda(getRandomFloatBetween(settings.getLowerLambda(), settings.getUpperLambda()));
	}

	/*
	 * public ParamIndividual(PSOSettings settings, ParamIndividual p) {
	 * this.setFitness(0); this.setW_n(p.getW_n()); int opcion =
	 * getRandomIntegerBetween(1, 3); switch (opcion) { case 1:
	 * this.setW(valorCercanoPorc(settings.getCambioW(), p.getW(),
	 * settings.getLowerW(), settings.getUpperW())); this.w = (this.getW() % 2
	 * == 0) ? this.getW() + 1 : this.getW(); this.setSigma_r(p.getSigma_r());
	 * this.setLambda(p.getLambda()); break; case 2:
	 * this.setSigma_r(valorCercanoPorc(settings.getCambioSigmaR(),
	 * p.getSigma_r(), settings.getLowerSigmaR(), settings.getUpperSigmaR()));
	 * this.setW(p.getW()); this.setLambda(p.getLambda()); break; case 3:
	 * this.setLambda(valorCercanoPorc(settings.getCambioLambda(),
	 * p.getLambda(), settings.getLowerLambda(), settings.getUpperLambda()));
	 * this.setW(p.getW()); this.setSigma_r(p.getSigma_r()); break; } }
	 * 
	 * private int valorCercanoPorc(int PorcCambio, int valor, int minimo, int
	 * maximo) { int signo = getRandomIntegerBetween(-1, 1); while (signo == 0)
	 * { signo = getRandomIntegerBetween(-1, 1); } signo = (valor==minimo)? 1 :
	 * signo; signo = (valor==maximo)? -1 : signo; float normalizado =(float)
	 * (valor - minimo) / (maximo - minimo); float cambio =
	 * getRandomIntegerBetween(1, PorcCambio)/(float)100; normalizado =
	 * normalizado + signo * cambio; int nuevo_v = (int) ((maximo - minimo) *
	 * normalizado + minimo); nuevo_v = (nuevo_v < minimo) ? minimo : nuevo_v;
	 * nuevo_v = (nuevo_v > maximo) ? maximo : nuevo_v; return nuevo_v; }
	 * 
	 * private float valorCercanoPorc(float PorcCambio, float valor, float
	 * minimo, float maximo) { int signo = getRandomIntegerBetween(-1, 1); while
	 * (signo == 0) { signo = getRandomIntegerBetween(-1, 1); } signo =
	 * (valor==minimo)? 1 : signo; signo = (valor==maximo)? -1 : signo; float
	 * normalizado = (valor - minimo) / (maximo - minimo); normalizado =
	 * normalizado + signo * (getRandomFloatBetween(1, PorcCambio)/100); float
	 * nuevo_v = (maximo - minimo) * normalizado + minimo; nuevo_v = (nuevo_v <
	 * minimo) ? minimo : nuevo_v; nuevo_v = (nuevo_v > maximo) ? maximo :
	 * nuevo_v; return nuevo_v; }
	 * 
	 * 
	 * private int valorCercano(int cambio, int valor, int minimo, int maximo) {
	 * int alteracion = getRandomIntegerBetween(-cambio, cambio); while
	 * (alteracion == 0) { alteracion = getRandomIntegerBetween(-cambio,
	 * cambio); } int nuevo_v = alteracion + valor; nuevo_v = (nuevo_v < minimo)
	 * ? minimo : nuevo_v; nuevo_v = (nuevo_v > maximo) ? maximo : nuevo_v;
	 * return nuevo_v; }
	 * 
	 * private float valorCercano(float cambio, float valor, float minimo, float
	 * maximo) { float alteracion = getRandomFloatBetween(-cambio, cambio);
	 * while (alteracion == 0.0) { alteracion = getRandomFloatBetween(-cambio,
	 * cambio); } float nuevo_v = alteracion + valor; nuevo_v = (nuevo_v <
	 * minimo) ? minimo : nuevo_v; nuevo_v = (nuevo_v > maximo) ? maximo :
	 * nuevo_v; return nuevo_v; }
	 */
	private int getRandomIntegerBetween(int lower, int upper) {
		return random.nextInt(upper - lower + 1) + lower;
	}

	private float getRandomFloatBetween(float lower, float upper) {
		return random.nextFloat() * (upper - lower + 1) + lower;
	}

	public void actualizarMejorGlobal() {
		if (this.fitness > ParamIndividual.getG_fitness()) {
			ParamIndividual.setG_w(this.getW());
			ParamIndividual.setG_w_n(this.getW_n());
			ParamIndividual.setG_sigma_r(this.getSigma_r());
			ParamIndividual.setG_lambda(this.getLambda());
			ParamIndividual.setG_fitness(this.getFitness());
		}
	}

	public void actualizarMejorLocal() {
		if (this.fitness > this.p_fitness) {
			this.setP_w(this.getW());
			this.setP_w_n(this.getW_n());
			this.setP_sigma_r(this.getSigma_r());
			this.setP_lambda(this.getLambda());
			this.setP_fitness(this.getFitness());
		}
	}

	public void nuevaVelocidad(double inertia, PSOSettings settings) {
		this.v_w = (int) ((inertia * this.v_w) + (settings.getC_Personal() * (this.p_w - this.w) * random.nextFloat())
				+ (settings.getC_Global() * (ParamIndividual.g_w - this.w) * random.nextFloat()));
		// this.v_w_n = (int) ((inertia * this.v_w_n) +
		// (settings.getC_Personal() * (this.p_w_n - this.w_n) *
		// random.nextFloat()) + (settings.getC_Global() * (this.g_w_n -
		// this.w_n) * random.nextFloat()));
		this.v_sigma_r = (int) ((inertia * this.v_sigma_r)
				+ (settings.getC_Personal() * (this.p_sigma_r - this.sigma_r) * random.nextFloat())
				+ (settings.getC_Global() * (ParamIndividual.g_sigma_r - this.sigma_r) * random.nextFloat()));
		this.v_lambda = (float) ((inertia * this.v_lambda)
				+ (settings.getC_Personal() * (this.p_lambda - this.lambda) * random.nextFloat())
				+ (settings.getC_Global() * (ParamIndividual.g_lambda - this.lambda) * random.nextFloat()));
	}

	public void nuevaPosicion(PSOSettings settings) {
		this.w += this.v_w;
		this.w = (this.getW() % 2 == 0) ? this.getW() + 1 : this.getW();
		// this.w_n += this.v_w_n;
		// this.w_n = (this.getW_n() % 2 == 0) ? this.getW_n() + 1 :
		// this.getW_n();
		this.sigma_r += this.v_sigma_r;
		this.lambda += this.v_lambda;

		this.w = (this.w < settings.getLowerW()) ? settings.getLowerW() : this.w;
		this.w = (this.w > settings.getUpperW()) ? settings.getUpperW() : this.w;
		// this.w_n = (this.w_n < settings.getLowerW_n()) ?
		// settings.getLowerW_n() : this.w_n;
		// this.w_n = (this.w_n > settings.getUpperW_n()) ?
		// settings.getUpperW_n() : this.w_n;
		this.sigma_r = (this.sigma_r < settings.getLowerSigmaR()) ? settings.getLowerSigmaR() : this.sigma_r;
		this.sigma_r = (this.sigma_r > settings.getUpperSigmaR()) ? settings.getUpperSigmaR() : this.sigma_r;
		this.lambda = (this.lambda < settings.getLowerLambda()) ? settings.getLowerLambda() : this.lambda;
		this.lambda = (this.lambda > settings.getUpperLambda()) ? settings.getUpperLambda() : this.lambda;

	}

	/**
	 * Implement Comparator. Individuals with a higher fitness will be ordered
	 * higher.
	 */

	@Override
	public int compareTo(ParamIndividual other) {
		return Double.compare(this.fitness, other.fitness);
	}

	@Override
	public String toString() {
		return String.valueOf(this.getW()) + "," + String.valueOf(this.getW_n()) + ","
				+ String.valueOf(this.getSigma_r()) + "," + String.valueOf(this.getLambda()) + " | "
				+ String.valueOf(this.getFitness());

	}
	
	public static String toStringGlobal(){
		return String.valueOf(ParamIndividual.getG_w()) + "," + String.valueOf(ParamIndividual.getG_w_n()) + ","
				+ String.valueOf(ParamIndividual.getG_sigma_r()) + "," + String.valueOf(ParamIndividual.getG_lambda()) + " | "
				+ String.valueOf(ParamIndividual.getG_fitness());
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getW_n() {
		return w_n;
	}

	public void setW_n(int w_n) {
		this.w_n = w_n;
	}

	public int getSigma_r() {
		return sigma_r;
	}

	public void setSigma_r(int sigma_r) {
		this.sigma_r = sigma_r;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public float getLambda() {
		return lambda;
	}

	public void setLambda(float lambda) {
		this.lambda = lambda;
	}

	public int getP_w() {
		return p_w;
	}

	public void setP_w(int p_w) {
		this.p_w = p_w;
	}

	public int getP_w_n() {
		return p_w_n;
	}

	public void setP_w_n(int p_w_n) {
		this.p_w_n = p_w_n;
	}

	public int getP_sigma_r() {
		return p_sigma_r;
	}

	public void setP_sigma_r(int p_sigma_r) {
		this.p_sigma_r = p_sigma_r;
	}

	public float getP_lambda() {
		return p_lambda;
	}

	public void setP_lambda(float p_lambda) {
		this.p_lambda = p_lambda;
	}

	public double getP_fitness() {
		return p_fitness;
	}

	public void setP_fitness(double p_fitness) {
		this.p_fitness = p_fitness;
	}

	public int getV_w() {
		return v_w;
	}

	public void setV_w(int v_w) {
		this.v_w = v_w;
	}

	public int getV_w_n() {
		return v_w_n;
	}

	public void setV_w_n(int v_w_n) {
		this.v_w_n = v_w_n;
	}

	public int getV_sigma_r() {
		return v_sigma_r;
	}

	public void setV_sigma_r(int v_sigma_r) {
		this.v_sigma_r = v_sigma_r;
	}

	public float getV_lambda() {
		return v_lambda;
	}

	public void setV_lambda(float v_lambda) {
		this.v_lambda = v_lambda;
	}

	public static int getG_w() {
		return g_w;
	}

	public static void setG_w(int g_w) {
		ParamIndividual.g_w = g_w;
	}

	public static int getG_w_n() {
		return g_w_n;
	}

	public static void setG_w_n(int g_w_n) {
		ParamIndividual.g_w_n = g_w_n;
	}

	public static int getG_sigma_r() {
		return g_sigma_r;
	}

	public static void setG_sigma_r(int g_sigma_r) {
		ParamIndividual.g_sigma_r = g_sigma_r;
	}

	public static float getG_lambda() {
		return g_lambda;
	}

	public static void setG_lambda(float g_lambda) {
		ParamIndividual.g_lambda = g_lambda;
	}

	public static double getG_fitness() {
		return g_fitness;
	}

	public static void setG_fitness(double g_fitness) {
		ParamIndividual.g_fitness = g_fitness;
	}

}
