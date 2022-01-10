package com.example.aerodromeApp.authority_fragments;

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
import com.example.aerodromeApp.adapters.shopping_myadapter;
import com.example.aerodromeApp.dataModel.datamodel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link shopping_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class shopping_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<datamodel> dataHolder;
    Activity context;
    GridView gridView;
    shopping_myadapter shopping_myadapter_inside;

    public shopping_fragment() {
        // Required empty public constructor



    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment shopping_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static shopping_fragment newInstance(String param1, String param2) {
        shopping_fragment fragment = new shopping_fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_shopping_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_xml);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();
     //   recyclerView.setAdapter(new shopping_gridView_Adapter(getActivity(),this.));

        // yt code
        context = getActivity();







        datamodel ob1 = new  datamodel(R.drawable.shopping,"Allen Solly","This is a famous clothes shop. It is well know for its luxurious clothes");
        dataHolder.add(ob1);

        datamodel ob2 = new  datamodel(R.drawable.shopping,"Levi's","Levi’s offers jeans, trousers, shorts, shirts, jackets, sweaters, sweatshirts, T-shirts");
        dataHolder.add(ob2);

        datamodel ob3=  new  datamodel(R.drawable.shopping,"Provogue","Provogue has a comprehensive collection of men’s and women’s fashion apparel and accessories");
        dataHolder.add(ob3);

        datamodel ob4 = new  datamodel(R.drawable.shopping,"Van Heusen","The brand is popular for formal clothing. Hence, Van Heusen is best for office or business attire.");
        dataHolder.add(ob4);

        datamodel ob5 = new  datamodel(R.drawable.shopping,"Park Avenue","Park Avenue provides stylish and innovative wardrobe solutions to well-dressed gentlemen.");
        dataHolder.add(ob5);

        datamodel ob6 = new  datamodel(R.drawable.shopping,"Mufti","Basically, Mufti is a menswear brand."+
                "\n" +
                "It also caters to a wide range of dress materials. The list includes T-shirts, shorts, joggers, outerwear, blazers, and some accessories also");
        dataHolder.add(ob6);

        datamodel ob7 = new  datamodel(R.drawable.shopping,"Peter England","The brand is known for its standardized fits, superior quality, and wide range and fashionable styles.");
        dataHolder.add(ob7);

        datamodel ob8 = new  datamodel(R.drawable.shopping,"Louis Philippe" ,"An international super brand for menswear clothing, Louis Philippe offers a range of formals, semi-formals, custom-made clothing, and accessories.");
        dataHolder.add(ob8);

        datamodel ob9 = new  datamodel(R.drawable.shopping,"Raymond" ,"Company is the largest integrated manufacturer of worsted fabric in the world.");
        dataHolder.add(ob9);

        datamodel ob10 = new  datamodel(R.drawable.shopping,"Adidas","Adidas is the largest sportswear manufacturer in Europe and the second-largest in the world.");
        dataHolder.add(ob10);

        shopping_myadapter_inside  = new shopping_myadapter(dataHolder,getActivity());

        recyclerView.setAdapter(shopping_myadapter_inside);

        shopping_myadapter_inside.notifyDataSetChanged();

     //   recyclerView.setAdapter(new shopping_myadapter(dataHolder));

        return view;
    }



    }