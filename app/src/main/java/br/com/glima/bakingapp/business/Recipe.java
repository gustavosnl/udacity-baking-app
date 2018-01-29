package br.com.glima.bakingapp.business;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class Recipe implements Parcelable {

	private String id;
	private String name;
	private List<Ingredient> ingredients;
	private List<Step> steps;
	private String servings;
	private String image;

	protected Recipe(Parcel in) {
		id = in.readString();
		name = in.readString();
		ingredients = in.createTypedArrayList(Ingredient.CREATOR);
		steps = in.createTypedArrayList(Step.CREATOR);
		servings = in.readString();
		image = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeTypedList(ingredients);
		dest.writeTypedList(steps);
		dest.writeString(servings);
		dest.writeString(image);
	}
	@Override
	public int describeContents() {
		return 0;
	}
	public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
		@Override
		public Recipe createFromParcel(Parcel in) {
			return new Recipe(in);
		}

		@Override
		public Recipe[] newArray(int size) {
			return new Recipe[size];
		}
	};
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public String getServings() {
		return servings;
	}

	public String getImage() {
		return image;
	}


}
