package com.example.android.retrofitwithfragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.retrofitwithfragments.Model.Data;
import com.example.android.retrofitwithfragments.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// GetFragment Own Class extends Fragment Class.
public class GetFragment extends Fragment {
    // Init View.
    View view;

    TextView Result;
    Button getData;

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    List<Data> data;

    // Empty constructor.
    public GetFragment() {
    }

    // onCreateView will be creation After onCreate method.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Connect Class With fragment_get Xml.
        view = inflater.inflate(R.layout.fragment_get, container, false);

        // Find view by IDs.
        recyclerView = (RecyclerView) view.findViewById(R.id.get_recycler_view);
        Result = (TextView) view.findViewById(R.id.text_get_fragment);
        getData = (Button) view.findViewById(R.id.btn_get_fragment);

        // When click on button.
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Disappear this button.
                getData.setVisibility(View.GONE);

                // RecyclerView Make it as LinearLayout or grid as you want.
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                // Constructor of own RecyclerViewAdapter class take two param (Context, Data).
                adapter = new RecyclerViewAdapter(getContext(), data);

                // Set Adapter.
                recyclerView.setAdapter(adapter);
            }
        });
        // return.
        return view;
    }

    // onCreate The first method will be creation.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect RetrofitClient Class that has 'Base URL' with Interface that has 'Relative URL' & methods.
        JsonPlaceHolderAPI jsonPlaceHolderAPI = RetrofitClient.getRetrofit().create(JsonPlaceHolderAPI.class);

        // Call as a data type, connect with server.
        Call<List<Data>> call = jsonPlaceHolderAPI.getPost();

        // 'enqueue' method works in background thread, that mean it will not effect on UI.
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                // Check if response not successful.
                if (!response.isSuccessful()) {
                    // setText by the code.
                    Result.setText("Code " + response.code());

                    // Stop.
                    return;
                }
                // Otherwise.
                // 'response.body()' get all data from response & convert it into List<PostClass>.
                data = response.body();
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                // setText of error message.
                Result.setText(t.getMessage());

            }
        });
    }
}
