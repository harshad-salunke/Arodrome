package com.example.aerodromeApp.userActivity_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.aerodromeApp.R;
import com.example.aerodromeApp.dataModel.data_model_userActivity_listView;
import com.example.aerodromeApp.userActivity_adapters.user_activity_listView_adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userActivity_shopping_fragment_listView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userActivity_shopping_fragment_listView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<data_model_userActivity_listView> dataHolder;
    Activity context;
    GridView gridView;
    user_activity_listView_adapter user_activity_listView_adapter_inside;


    public userActivity_shopping_fragment_listView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userActivity_shopping_fragment_listView.
     */
    // TODO: Rename and change types and number of parameters
    public static userActivity_shopping_fragment_listView newInstance(String param1, String param2) {
        userActivity_shopping_fragment_listView fragment = new userActivity_shopping_fragment_listView();
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

        View view = inflater.inflate(R.layout.fragment_user_activity_shopping_list_view, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_userActivity_xml);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();

        context = getActivity();


        int i=1;

        data_model_userActivity_listView ob1 = new
                data_model_userActivity_listView(R.drawable.shopping,"Allen Solly","This is a famous clothes shop. It is well know for its luxurious clothes");
        dataHolder.add(ob1);


        data_model_userActivity_listView ob2 = new
                data_model_userActivity_listView(R.drawable.shopping,"Levi's","Levi’s offers jeans, trousers, shorts, shirts, jackets, sweaters, sweatshirts, T-shirts");
        dataHolder.add(ob2);


        data_model_userActivity_listView ob3=  new
                data_model_userActivity_listView(R.drawable.shopping,"Provogue","Provogue has a comprehensive collection of men’s and women’s fashion apparel and accessories");
        dataHolder.add(ob3);


        data_model_userActivity_listView ob4 = new
                data_model_userActivity_listView(R.drawable.shopping,"Van Heusen","The brand is popular for formal clothing. Hence, Van Heusen is best for office or business attire");
        dataHolder.add(ob4);


        data_model_userActivity_listView ob5 = new
                data_model_userActivity_listView(R.drawable.shopping,"Park Avenue","Park Avenue provides stylish and innovative wardrobe solutions to well-dressed gentlemen.");
        dataHolder.add(ob5);


        data_model_userActivity_listView ob6 = new
                data_model_userActivity_listView(R.drawable.shopping,"Mufti","Basically, Mufti is a menswear brand."+
                "It also caters to a wide range of dress materials. The list includes T-shirts, shorts, joggers, outerwear, blazers, and some accessories also");
        dataHolder.add(ob6);


        data_model_userActivity_listView ob7 = new
                data_model_userActivity_listView(R.drawable.shopping,"Peter England","The brand is known for its standardized fits, superior quality, and wide range and fashionable styles.");
        dataHolder.add(ob7);


        data_model_userActivity_listView ob8 = new
                data_model_userActivity_listView(R.drawable.shopping,"Louis Philippe","An international super brand for menswear clothing, " +
                                                     "Louis Philippe offers a range of formals, semi-formals, custom-made clothing, and accessories.");
        dataHolder.add(ob8);


        data_model_userActivity_listView ob9 = new
                data_model_userActivity_listView(R.drawable.shopping,"Raymond","Company is the largest integrated manufacturer of worsted fabric in the world.");
        dataHolder.add(ob9);


        data_model_userActivity_listView ob10 = new
                data_model_userActivity_listView(R.drawable.shopping,"Adidas","Adidas is the largest sportswear manufacturer in Europe and the second-largest in the world.");
        dataHolder.add(ob10);


        user_activity_listView_adapter_inside = new user_activity_listView_adapter(dataHolder,getActivity());
        recyclerView.setAdapter(user_activity_listView_adapter_inside);

        user_activity_listView_adapter_inside.notifyDataSetChanged();
        

        return view;
    }
}