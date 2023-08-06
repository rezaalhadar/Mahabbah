package com.randfiq.mahabbah.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.randfiq.mahabbah.R;
import com.randfiq.mahabbah.databinding.FragmentWebviewBinding;

public class WebviewFragment extends Fragment {

    private FragmentWebviewBinding binding;

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWebviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        webView = root.findViewById(R.id.webview);

        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdQfY4iO3YBKZ3YiBNE4cXHwJGJ3wMDcHM_zg709fjVmR3pWg/viewform");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}