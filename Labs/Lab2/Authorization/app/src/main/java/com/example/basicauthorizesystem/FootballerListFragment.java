package com.example.basicauthorizesystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.basicauthorizesystem.Adapters.FootBallPlayerAdpater;
import com.example.basicauthorizesystem.Models.FootBallPlayer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FootballerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FootballerListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lv_Player;
    ArrayList<FootBallPlayer> playerList;
    FootBallPlayerAdpater adpater;
    View currentView;

    public FootballerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FootballerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FootballerListFragment newInstance(String param1, String param2) {
        FootballerListFragment fragment = new FootballerListFragment();
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
        // Inflate the layout for this fragment
        currentView = inflater.inflate(R.layout.fragment_footballer_list, container, false);
        return currentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Reference();
        adpater = new FootBallPlayerAdpater(currentView.getContext(), R.layout.activity_football_player_component, playerList);
        lv_Player.setAdapter(adpater);
    }

    private void Reference() {
        lv_Player = (ListView) currentView.findViewById(R.id.lvPlayer);

        playerList = new ArrayList<>();
        playerList.add(new FootBallPlayer("Pele", "October 23, 1940 (age 72)", R.drawable.pele, R.drawable.brazil));
        playerList.add(new FootBallPlayer("Diego Maradona", "October 30, 1960 (age 52)", R.drawable.maradona, R.drawable.argentina));
        playerList.add(new FootBallPlayer("Johan Cruyff", "April 25, 1947 (age 65)", R.drawable.johan_cruyff, R.drawable.netherlands));
        playerList.add(new FootBallPlayer("Franz Beckenbauer", "September 11, 1945 (age 67)", R.drawable.beckenbauer, R.drawable.germany));
        playerList.add(new FootBallPlayer("Michel Platini", "June 21, 1955 (age 57)", R.drawable.platini, R.drawable.france));
        playerList.add(new FootBallPlayer("Ronaldo De Lima", "September 22, 1976 (age 36)", R.drawable.ronaldo_de_lima, R.drawable.brazil));
    }
}