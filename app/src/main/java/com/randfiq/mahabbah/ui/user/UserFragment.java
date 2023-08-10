package com.randfiq.mahabbah.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.randfiq.mahabbah.R;
import com.randfiq.mahabbah.data.handler.DataCacheManager;
import com.randfiq.mahabbah.data.handler.DataManager;
import com.randfiq.mahabbah.data.model.DataPengguna;
import com.randfiq.mahabbah.databinding.FragmentUserBinding;
import com.randfiq.mahabbah.utils.adapter.AdapterDataPengguna;

import java.util.List;

public class UserFragment extends Fragment implements AdapterDataPengguna.OnItemClickListener {

    private FragmentUserBinding binding;
    View view;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextInputEditText editText_cariData;

    AdapterDataPengguna adapterDataPengguna;
    List<DataPengguna> cachedItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        editText_cariData = view.findViewById(R.id.textfield_cariData);
        FloatingActionButton fab_adduser = view.findViewById(R.id.fab_addData);

        // Initialize the RecyclerView and its adapter
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        cachedItems = DataCacheManager.getCachedData(requireContext());
        if(cachedItems == null){
            getData();
        } else {
            setupAdapter(cachedItems);
        }

        if(cachedItems != null){
            editText_cariData.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Not used in this case
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String query = s.toString();
                    adapterDataPengguna.filterBasic(query);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // This is where you would typically fetch and refresh your data
                    // Call your data fetching method here
                    getData();

                    cachedItems = DataCacheManager.getCachedData(requireContext());
                    setupAdapter(cachedItems);

                    // Once the data is fetched, call setRefreshing(false) to stop the refresh animation
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }

        fab_adduser.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_navigation_user_to_navigation_adduser);
        });

        return view;
    }

    private void setupAdapter(List<DataPengguna> data){
        adapterDataPengguna = new AdapterDataPengguna(data);
        recyclerView.setAdapter(adapterDataPengguna);
        adapterDataPengguna.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        DataManager.fetchData(requireContext(), new DataManager.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(List<DataPengguna> items) {
                DataCacheManager.cacheData(requireContext(), items);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(requireContext(), "Error get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(DataPengguna item) {
//        Bundle bundle = new Bundle();
//        bundle.putString(key_dateinput, item.getDateInput());
//        bundle.putString(key_kodeProduk, item.getKodeProduk());
//        bundle.putString(key_namaProduk, item.getNamaProduk());
//        bundle.putInt(key_hargaSatuanProduk, item.getHargaSatuanProduk());
//
//        NavController navController = Navigation.findNavController(view);
//        navController.navigate(R.id.action_navigation_dashboard_to_navigation_item_detail, bundle);
         Toast.makeText(requireContext(), "Clicked: " + item.getNama(), Toast.LENGTH_SHORT).show();
    }
}