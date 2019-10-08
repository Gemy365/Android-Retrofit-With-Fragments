package com.example.android.retrofitwithfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.retrofitwithfragments.Model.Data;
import com.example.android.retrofitwithfragments.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// GetFragment Own Class extends Fragment Class.
public class PostFragment extends Fragment {
    // Init View.
    View view;

    EditText PostUserIdTv;
    EditText PostTitleTv;
    EditText PostBodyTv;

    TextView PostResultTv;

    Button PostDataBtn;
    Data data;

    public PostFragment() {
        // Required empty public constructor
    }

    // onCreateView will be creation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Connect Class With fragment_post Xml.
        view = inflater.inflate(R.layout.fragment_post, container, false);

        // Find view by IDs.
        PostUserIdTv = (EditText) view.findViewById(R.id.edt_text_user_id_post_fragment);
        PostTitleTv = (EditText) view.findViewById(R.id.edt_text_title_post_fragment);
        PostBodyTv = (EditText) view.findViewById(R.id.edt_text_body_post_fragment);
        PostResultTv = (TextView) view.findViewById(R.id.text_result_post_fragment);

        PostDataBtn = (Button) view.findViewById(R.id.btn_post_data);

        // When click on button.
        PostDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Connect RetrofitClient Class that has 'Base URL' with Interface that has 'Relative URL' & methods.
                JsonPlaceHolderAPI jsonPlaceHolderAPI = RetrofitClient.getRetrofit().create(JsonPlaceHolderAPI.class);


                // Call as a data type, connect with server
                // createPost take Data as Params.
                Call<Data> call = jsonPlaceHolderAPI.createPost(Integer.parseInt(PostUserIdTv.getText().toString())
                        , PostTitleTv.getText().toString(), PostBodyTv.getText().toString());

                // 'enqueue' method works in background thread, that mean it will not effect on UI.
                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {

                        // Check if response not successful.
                        if (!response.isSuccessful()) {
                            // setText by the code.
                            PostResultTv.setText("Code " + response.code());

                            // Stop.
                            return;
                        }
                        // Otherwise.
                        // 'response.body()' get all data from response & convert it into PostClass.
                        data = response.body();

                        // Empty String To Store All Data.
                        String content = "";

                        // Concatenate Texts Together.
                        content += "Code: " + response.code() + "\n";
                        content += "ID: " + data.getId() + "\n";
                        content += "UserID: " + data.getUserId() + "\n";
                        content += "Title: " + data.getTitle() + "\n";
                        content += "Text: " + data.getText() + "\n\n";

                        // append .. Concatenate Texts Together.
                        PostResultTv.append(content);

                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        // setText of error message.
                        PostResultTv.setText(t.getMessage());

                    }
                });
            }
        });
        // return.
        return view;
    }

}