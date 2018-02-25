package br.com.glima.bakingapp.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.glima.bakingapp.R;
import br.com.glima.bakingapp.business.Ingredient;
import br.com.glima.bakingapp.view.list.IngredientsAdapter;

/**
 * Created by gustavo on 20/02/18.
 */

public class IngredientsDialog extends DialogFragment {
	private RecyclerView mRecyclerView;
	private IngredientsAdapter mAdapter;
	private final String TAG = "ingredients_fragment";
	private static final String INGREDIENTS_BUNDLE_KEY = "ingredients";

	public IngredientsDialog() {}

	public static IngredientsDialog newInstance(ArrayList<Ingredient> ingredients) {
		Bundle args = new Bundle();
		args.putParcelableArrayList(INGREDIENTS_BUNDLE_KEY, ingredients);

		IngredientsDialog fragment = new IngredientsDialog();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.ingredients_dialog, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mRecyclerView = view.findViewById(R.id.ingredientsList);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new IngredientsAdapter(getArguments().<Ingredient>getParcelableArrayList(INGREDIENTS_BUNDLE_KEY));
		mRecyclerView.setAdapter(mAdapter);
	}
	public void display(FragmentManager manager) {
		show(manager, TAG);
	}
}