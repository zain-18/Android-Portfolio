package com.tapkrill.facebookcode.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tapkrill.facebookcode.Adapter.CategoriesAdapter;
import com.tapkrill.facebookcode.Model.DataList;
import com.tapkrill.facebookcode.R;
import java.util.ArrayList;



public class Categories  extends Fragment {

RecyclerView categories_recyclerview;
  CategoriesAdapter categoriesAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //initialinig view
        View view = inflater.inflate(R.layout.category, container, false);
        return view;




    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        //initialize widgets
        categories_recyclerview=view.findViewById(R.id.categories_recyclerview);
        categories_recyclerview.setHasFixedSize(true);

        //initialize Array list and passing Model class
        ArrayList<DataList> dataarraylist= new ArrayList<>();

            // Add item in Array list and calling Model class and passing parameters
        dataarraylist.add(new DataList(R.drawable.animals,"Animals"));
        dataarraylist.add(new DataList(R.drawable.anime,"Anime"));
        dataarraylist.add(new DataList(R.drawable.vehicles,"Vehicles"));
        dataarraylist.add(new DataList(R.drawable.designs,"Designs"));
        dataarraylist.add(new DataList(R.drawable.drawing,"Drawing"));
        dataarraylist.add(new DataList(R.drawable.entertainment,"Entertainment"));
        dataarraylist.add(new DataList(R.drawable.funny,"Funny"));
        dataarraylist.add(new DataList(R.drawable.games,"Games"));
        dataarraylist.add(new DataList(R.drawable.holidays,"Holidays"));
        dataarraylist.add(new DataList(R.drawable.love,"Love"));
        dataarraylist.add(new DataList(R.drawable.music,"Music"));
        dataarraylist.add(new DataList(R.drawable.nature,"Nature"));
        dataarraylist.add(new DataList(R.drawable.motivation,"Motivation"));
        dataarraylist.add(new DataList(R.drawable.sports,"Sports"));
        dataarraylist.add(new DataList(R.drawable.dark,"Dark"));
        dataarraylist.add(new DataList(R.drawable.sayings,"Sayings"));
        dataarraylist.add(new DataList(R.drawable.actors,"Actors"));
        dataarraylist.add(new DataList(R.drawable.peoples,"Peoples"));

        //initialize Adapter and passing required parameters init
        categoriesAdapter=new CategoriesAdapter(getContext(),dataarraylist,Categories.this);

        // Setting Layout Manager & type
        categories_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));

        //set Adapter to recyclerview
        categories_recyclerview.setAdapter(categoriesAdapter);

        //notifyAdapter when data updated
        categoriesAdapter.notifyDataSetChanged();



    }

}
