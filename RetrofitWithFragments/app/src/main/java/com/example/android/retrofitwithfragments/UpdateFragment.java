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


// UpdateFragment Own Class extends Fragment Class.
public class UpdateFragment extends Fragment {

    // Init View.
    View view;

    EditText UpdateUserIdTv;
    EditText UpdateTitleTv;
    EditText UpdateBodyTv;

    TextView UpdateResultTv;

    Button UpdateDataBtn;
    Data data;

    public UpdateFragment() {
        // Required empty public constructor
    }


    // onCreateView will be creation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update, container, false);

        // Find view by IDs.
        UpdateUserIdTv = (EditText) view.findViewById(R.id.edt_text_user_id_update_fragment);
        UpdateTitleTv = (EditText) view.findViewById(R.id.edt_text_title_update_fragment);
        UpdateBodyTv = (EditText) view.findViewById(R.id.edt_text_body_update_fragment);
        UpdateResultTv = (TextView) view.findViewById(R.id.text_result_update_fragment);

        UpdateDataBtn = (Button) view.findViewById(R.id.btn_update_data);

        // When click on button.
        UpdateDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Connect RetrofitClient Class that has 'Base URL' with Interface that has 'Relative URL' & methods.
                JsonPlaceHolderAPI jsonPlaceHolderAPI = RetrofitClient.getRetrofit().create(JsonPlaceHolderAPI.class);

                // Send Data As Params Into Constructor.
                 data = new Data(Integer.parseInt(UpdateUserIdTv.getText().toString())
                        , UpdateTitleTv.getText().toString(), UpdateBodyTv.getText().toString());


                // Call as a data type, connect with server
                // patchPost or putPost take ID & Data as Params.
                Call<Data> call = jsonPlaceHolderAPI.patchPost(1, data);

                // 'enqueue' method works in background thread, that mean it will not effect on UI.
                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {

                        // Check if response not successful.
                        if (!response.isSuccessful()) {
                            // setText by the code.
                            UpdateResultTv.setText("Code " + response.code());

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
                        UpdateResultTv.append(content);

                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        // setText of error message.
                        UpdateResultTv.setText(t.getMessage());

                    }
                });
            }
        });
        // return.
        return view;
    }
}
