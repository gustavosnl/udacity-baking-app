package br.com.glima.bakingapp.business;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class Ingredient implements Parcelable {

	private Double quantity;
	private String measure;
	private String ingredient;

	public Ingredient(String ingredient) {
		this.ingredient = ingredient;
	}

	protected Ingredient(Parcel in) {
		if (in.readByte() == 0) { quantity = null; } else { quantity = in.readDouble(); }
		measure = in.readString();
		ingredient = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (quantity == null) { dest.writeByte((byte) 0); } else {
			dest.writeByte((byte) 1);
			dest.writeDouble(quantity);
		}
		dest.writeString(measure);
		dest.writeString(ingredient);
	}
	@Override
	public int describeContents() {
		return 0;
	}
	public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
		@Override
		public Ingredient createFromParcel(Parcel in) {
			return new Ingredient(in);
		}

		@Override
		public Ingredient[] newArray(int size) {
			return new Ingredient[size];
		}
	};
	public Double getQuantity() {
		return quantity;
	}

	public int getIntQuantity() {
		return quantity.intValue();
	}
	public MeasurementUnit getMeasure() {
		return MeasurementUnit.valueOf(measure);
	}
	public String getIngredient() {
		return ingredient;
	}
}

