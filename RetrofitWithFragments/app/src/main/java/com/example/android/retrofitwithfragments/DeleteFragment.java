package com.example.android.retrofitwithfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.retrofitwithfragments.Model.Data;
import com.example.android.retrofitwithfragments.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// GetFragment Own Class extends Fragment Class.
public class DeleteFragment extends Fragment {

    // Init View.
    View view;

    TextView DeleteResultTv;

    Button DeleteDataBtn;

    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_delete, container, false);

        DeleteResultTv = (TextView) view.findViewById(R.id.text_result_delete_fragment);

        DeleteDataBtn = (Button) view.findViewById(R.id.btn_post_data);

        DeleteDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Connect RetrofitClient Class that has 'Base URL' with Interface that has 'Relative URL' & methods.
                JsonPlaceHolderAPI jsonPlaceHolderAPI = RetrofitClient.getRetrofit().create(JsonPlaceHolderAPI.class);

                // Send ID deletePost method.
                Call<Void> call = jsonPlaceHolderAPI.deletePost(1);

                // 'enqueue' method works in background thread, that mean it will not effect on UI.
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (!response.isSuccessful()) {
                            // setText by the code.
                            DeleteResultTv.setText("Code " + response.code());

                            // Stop.
                            return;
                        }
                        // Otherwise.
                        // 'setText' for displaying only one data.
                        // If Code = 200 that mean data is updated.
                        DeleteResultTv.setText("Code: " + response.code());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // setText of error message.
                        DeleteResultTv.setText(t.getMessage());

                    }
                });
            }
        });
        return view;
    }
}