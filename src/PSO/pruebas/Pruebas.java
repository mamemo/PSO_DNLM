package PSO.pruebas;

public class Pruebas{
	
	public static void main(String[] args){
		Filtrar_Dataset prueba1 = new Filtrar_Dataset();
		prueba1.correrPrueba(9,3,14,26.874018);
		Filtrar_Otsu prueba = new Filtrar_Otsu();
		prueba.correrPrueba(9,3,14,26.874018);
		//Comparar_Dice prueba2 = new Comparar_Dice();
		//prueba2.correrPrueba();
	}
	
}
