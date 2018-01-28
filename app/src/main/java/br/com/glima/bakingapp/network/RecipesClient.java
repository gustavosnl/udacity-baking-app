package br.com.glima.bakingapp.network;

import java.util.List;

import br.com.glima.bakingapp.BuildConfig;
import br.com.glima.bakingapp.business.Recipe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class RecipesClient {

	interface BakingService {
		@GET("topher/2017/May/59121517_baking/baking.json")
		Observable<List<Recipe>> getRecipes();

	}

	private BakingService service;

	public RecipesClient() {

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://d17h27t6h515a5.cloudfront.net/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(setupHttpClient())
				.build();

		service = retrofit.create(BakingService.class);
	}

	public Observable<List<Recipe>> fetchRecipes() {
		return service.getRecipes()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}


	private OkHttpClient setupHttpClient() {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(BuildConfig.DEBUG ? BODY : NONE);

		return new OkHttpClient.Builder()
				.addInterceptor(logging)
				.build();
	}
}
