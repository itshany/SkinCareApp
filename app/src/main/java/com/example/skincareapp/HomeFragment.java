package com.example.skincareapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class HomeFragment extends Fragment{

    /*TextView saDetails, gaDetails;
    LinearLayout saLayout, gaLayout;
    String databaseReadValue = "0", fristBtnName;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myDatabaseRef = database.getReference();*/
    RecyclerView recyclerView;
    FetchData fetchData;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //myDatabaseRef.child("mainViewData").child("RecyclerView").child("1").child("Message").setValue("Hello, World!");
        //myDatabaseRef.setValue("Hello, World!");

        /*myDatabaseRef.child("mainViewData").child("RecyclerView").child("1").child("name").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                //Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                fristBtnName = String.valueOf(task.getResult().getValue());
                Toast.makeText(getContext(), fristBtnName, Toast.LENGTH_SHORT).show();
            }
        });*/



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        new FetchData().readData(new FetchData.DataStatus() {
            @Override
            public void DataIsLoaded(List<SkinCareInfo> items, List<String> keys) {
                /*String name = data.get(0).getName();
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();*/
                new RecyclerView_Config().setConfig(recyclerView, getActivity(),items,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        /*saDetails = (TextView) view.findViewById(R.id.saDetails);
        gaDetails = (TextView) view.findViewById(R.id.gaDetails);
        saLayout = (LinearLayout) view.findViewById(R.id.saLayout);
        gaLayout = (LinearLayout) view.findViewById(R.id.gaLayout);
        saLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        gaLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        saLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (saDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
                TransitionManager.beginDelayedTransition(saLayout,new AutoTransition());
                saDetails.setVisibility(v);

                basicRead();
            }
        });
        gaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (gaDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(gaLayout,new AutoTransition());
                gaDetails.setVisibility(v);
            }
        });*/
        return view;
    }

    public void basicRead(/*String name, String details, String index*/) {
        // [START write_message]
        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Hello, World!");
        // [END write_message]

        // [START read_message]
        // Read from the database
        /*myDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                 databaseReadValue = dataSnapshot.getValue(String.class);
                Toast.makeText(getContext(), databaseReadValue, Toast.LENGTH_LONG).show();
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                //String value = null;
            }
            
        });
        // [END read_message]*/



    }
}