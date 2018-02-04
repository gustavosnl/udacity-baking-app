package br.com.glima.bakingapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Recipe;
import br.com.glima.bakingapp.network.RecipesClient;
import br.com.glima.bakingapp.view.RecipeClickedCallback;
import br.com.glima.bakingapp.view.list.DividerItemDecoration;
import br.com.glima.bakingapp.view.list.RecipesAdapter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class RecipesFragment extends Fragment implements Observer<List<Recipe>> {
	private RecipesClient client;

	private RecyclerView recyclerView;
	public RecipesFragment() {
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false);
		recyclerView = rootView.findViewById(R.id.recipes_list);
		recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		client = new RecipesClient();

		return rootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		client.fetchRecipes().subscribe(this);

	}

	@Override
	public void onSubscribe(Disposable d) {

	}

	@Override
	public void onNext(List<Recipe> recipes) {
		recyclerView.setAdapter(new RecipesAdapter(recipes, (RecipeClickedCallback) getActivity()));
	}

	@Override
	public void onError(Throwable e) {

	}

	@Override
	public void onComplete() {

	}
}
