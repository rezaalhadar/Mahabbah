package com.randfiq.mahabbah.ui.user;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.randfiq.mahabbah.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddUserFragment extends Fragment {

    private View view;

    private TextInputEditText inputEditText_tanggallahir;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_user, container, false);

        inputEditText_tanggallahir = view.findViewById(R.id.textfield_adduser_tanggallahir);

     // Get the AutoCompleteTextView reference from XML layout
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.textfield_adduser_namabank);

        // Create an ArrayAdapter with the suggestions
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.bank_list)
        );

        // Set the adapter for the AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter);

        inputEditText_tanggallahir.setOnClickListener(v -> showDatePickerDialog());

        return view;

    }

    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, year1, month1, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(Calendar.YEAR, year1);
                    selectedDate.set(Calendar.MONTH, month1);
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = dateFormat.format(selectedDate.getTime());

                    inputEditText_tanggallahir.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }
}