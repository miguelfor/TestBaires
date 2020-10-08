package com.google.testapplication.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.testapplication.R;
import com.google.testapplication.ui.Client.APIServiceInterface;
import com.google.testapplication.ui.Models.Repositorio;
import com.google.testapplication.ui.Utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    private ListView listview;
    private ArrayList<String> item;
    private ArrayList<String> link;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        this.context = root.getContext();
        listview = (ListView) root.findViewById(R.id.listView);

        getRepositorio();
        return root;
    }

    private void getRepositorio() {
        Utils.showDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServiceInterface AI = retrofit.create(APIServiceInterface.class);
        Call<Repositorio> call = AI.getRepositorio();
        call.enqueue(new Callback<Repositorio>() {
            @Override
            public void onResponse(Call<Repositorio> call, Response<Repositorio> response) {
                Utils.dismissDialog();
                if (response.isSuccessful()) {
                    onResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<Repositorio> call, Throwable t) {
                Utils.dismissDialog();
            }
        });
    }

    public void onResult(Repositorio repo) {
        item = new ArrayList<String>();
        link = new ArrayList<String>();

        for (int i = 0; i < repo.getItems().size(); i++) {
            item.add(repo.getItems().get(i).getName());
            link.add(repo.getItems().get(i).getHtml_url());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, item);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(link.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}