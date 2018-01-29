package br.com.glima.bakingapp.business;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public enum MeasurementUnit {

	CUP("Cup"),
	TBLSP("Table Spoon"),
	TSP("Tea Spoon"),
	G("Gram"),
	K("Kilogram"),
	OZ("Oz"),
	UNIT("Unit");

	private String measurement;

	MeasurementUnit(String measurement) {
		this.measurement = measurement;
	}

	public String getMeasurement() {
		return measurement;
	}
}
