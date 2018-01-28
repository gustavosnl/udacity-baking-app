package br.com.glima.bakingapp.business;

/**
 * Created by gustavo.lima on 27/01/18.
 */

enum MeasurementUnit {

	CUP("CUP"),
	TABLE_SPOON("TBLSP"),
	TEA_SPOON("TSP"),
	GRAMS("G"),
	KILOGRAMS("K"),
	OUNCE("OZ"),
	UNIT("UNIT");

	private String measurement;

	MeasurementUnit(String measurement) {
		this.measurement = measurement;
	}
}
