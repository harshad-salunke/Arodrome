package com.example.aerodromeApp.userActivity_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.aerodromeApp.R;
import com.example.aerodromeApp.dataModel.data_model_userActivity_listView_food_and_beverages;
import com.example.aerodromeApp.userActivity_adapters.user_activity_listView_food_and_beverages_adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userActivity_food_and_beverages_listView_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userActivity_food_and_beverages_listView_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<data_model_userActivity_listView_food_and_beverages>  dataHolder;
    Activity context;
    user_activity_listView_food_and_beverages_adapter food_and_beverages_listView_adapter_inside;



    public userActivity_food_and_beverages_listView_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userActivity_food_and_beverages_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userActivity_food_and_beverages_listView_fragment newInstance(String param1, String param2) {
        userActivity_food_and_beverages_listView_fragment fragment = new userActivity_food_and_beverages_listView_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_user_activity_food_and_beverages_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_food_and_beverages_userActivity_xml);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();


        context = getActivity();


        data_model_userActivity_listView_food_and_beverages ob1 = new data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Burger King","This is a famous burger shop originated from florida");
        dataHolder.add(ob1);

        data_model_userActivity_listView_food_and_beverages ob2 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"McDonald’s","It is a fast food company which delivers food and beverage's to its customers");
        dataHolder.add(ob2);

        data_model_userActivity_listView_food_and_beverages ob3=  new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Pizza hut "," They serve their signature pan pizza and other dishes including pasta, breadsticks and desserts.");
        dataHolder.add(ob3);

        data_model_userActivity_listView_food_and_beverages ob4 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"KFC","KFC specialized in fried chicken and also has many other snacks");
        dataHolder.add(ob4);

        data_model_userActivity_listView_food_and_beverages ob5 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Subway","Subway offers submarine sandwiches, wraps, salads and beverages");
        dataHolder.add(ob5);

        data_model_userActivity_listView_food_and_beverages ob6 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Domino's Pizza","Domino's is a leading pizza making company and it makes different variety of pizza's");
        dataHolder.add(ob6);

        data_model_userActivity_listView_food_and_beverages ob7 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Haldiram’s","Haldiram's is an Indian sweets, snacks and restaurant");
        dataHolder.add(ob7);

        data_model_userActivity_listView_food_and_beverages ob8 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Starbucks","Starbucks serves hot and cold drinks, whole-bean coffee, micro-ground instant coffee, espresso, caffe latte, full and loose-leaf teas, juices, Frappuccino beverages, pastries, and snacks.");
        dataHolder.add(ob8);

        data_model_userActivity_listView_food_and_beverages ob9 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Café Coffee Day","This is a clothes shop");
        dataHolder.add(ob9);

        data_model_userActivity_listView_food_and_beverages ob10 = new  data_model_userActivity_listView_food_and_beverages(R.drawable.beverage,"Chai Point","Chai points brings a perfectly brewed cup of Chai made with fresh, natural ingredients to its customers");
        dataHolder.add(ob10);




        food_and_beverages_listView_adapter_inside = new user_activity_listView_food_and_beverages_adapter(dataHolder,getActivity());
        recyclerView.setAdapter(food_and_beverages_listView_adapter_inside);
        food_and_beverages_listView_adapter_inside.notifyDataSetChanged();
        return view;

    }
}