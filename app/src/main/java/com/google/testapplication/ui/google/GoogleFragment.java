package com.google.testapplication.ui.google;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.testapplication.R;

public class GoogleFragment extends Fragment {

    private GoogleViewModel googleViewModel;

    private WebView wv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        googleViewModel =
                ViewModelProviders.of(this).get(GoogleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_google, container, false);

        String url = "https://www.google.com";
        wv = root.findViewById(R.id.wvGoogle);
        final WebSettings ajustesVisorWeb = wv.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        wv.loadUrl(url);

        return root;
    }
}