package br.com.glima.bakingapp.view;

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

/**
 * Created by gustavo.lima on 27/01/18.
 */

public class RecipesFragment extends Fragment {

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
		return rootView;
	}

	public void setRecipes(List<Recipe> recipeList) {
		recyclerView.setAdapter(new RecipesAdapter(recipeList, (RecipeClickedCallback) getActivity()));
	}
}
