package com.example.basicauthorizesystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextListViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lvMonHoc;
    ArrayList<String> arrayCourse;
    private Button btn_Add, btn_Edit;
    private EditText edt_Input;
    private int itemPosition;
    ArrayAdapter adapter;

    View currentView;

    public TextListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TextListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextListViewFragment newInstance(String param1, String param2) {
        TextListViewFragment fragment = new TextListViewFragment();
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
        currentView =  inflater.inflate(R.layout.fragment_text_list_view, container, false);
        return currentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_Add = (Button) view.findViewById(R.id.buttonAdd);
        btn_Edit = (Button) view.findViewById(R.id.buttonEdit);
        edt_Input = (EditText) view.findViewById(R.id.editTextText);
        lvMonHoc = (ListView) view.findViewById(R.id.listViewMH);

        arrayCourse = new ArrayList<>();
        arrayCourse.add("Android");
        arrayCourse.add("PHP");
        arrayCourse.add("IOS");
        arrayCourse.add("Unity");
        arrayCourse.add("ASP.net");
        adapter = new ArrayAdapter(
                view.getContext(),
                android.R.layout.simple_list_item_1,
                arrayCourse
        );
        lvMonHoc.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemPosition = position;
                edt_Input.setText(arrayCourse.get(position));
            }
        });

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayCourse.remove(position);
                adapter.notifyDataSetChanged();

                return false;
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt_Input.getText().toString())){
                    Toast.makeText(currentView.getContext(), "Please insert item", Toast.LENGTH_LONG).show();
                }
                else{
                    arrayCourse.add(edt_Input.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt_Input.getText().toString())){
                    Toast.makeText(currentView.getContext(), "Please insert item", Toast.LENGTH_LONG).show();
                }
                else{
                    arrayCourse.set(itemPosition, edt_Input.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}