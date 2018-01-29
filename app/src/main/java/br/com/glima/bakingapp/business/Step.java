package br.com.glima.bakingapp.business;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class Step implements Parcelable {

	private String id;
	private String shortDescription;
	private String description;
	private String videoUrl;
	private String thumbnailUrl;


	protected Step(Parcel in) {
		id = in.readString();
		shortDescription = in.readString();
		description = in.readString();
		videoUrl = in.readString();
		thumbnailUrl = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(shortDescription);
		dest.writeString(description);
		dest.writeString(videoUrl);
		dest.writeString(thumbnailUrl);
	}
	@Override
	public int describeContents() {
		return 0;
	}
	public static final Creator<Step> CREATOR = new Creator<Step>() {
		@Override
		public Step createFromParcel(Parcel in) {
			return new Step(in);
		}

		@Override
		public Step[] newArray(int size) {
			return new Step[size];
		}
	};
	public String getId() {
		return id;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public String getDescription() {
		return description;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
}
