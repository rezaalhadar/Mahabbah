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

    // Object Data Pribadi
    private TextInputEditText inputEditText_nama;
    private TextInputEditText inputEditText_namaayah;
    private TextInputEditText inputEditText_marga;
    private TextInputEditText inputEditText_status;

    // Object Kontak Pribadi
    private TextInputEditText inputEditText_nomorhp;
    private TextInputEditText inputEditText_email;
    private TextInputEditText inputEditText_alamatsekarang;

    // Object Data Identitas
    private TextInputEditText inputEditText_nikktp;
    private TextInputEditText inputEditText_tempatlahir;
    private TextInputEditText inputEditText_tanggallahir;

    // Object Data Perbankan
    private AutoCompleteTextView inputEditText_namabank;
    private TextInputEditText inputEditText_nomorrekening;

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

        // Set Object Data Pribadi
        inputEditText_nama = view.findViewById(R.id.textfield_adduser_nama);
        inputEditText_namaayah = view.findViewById(R.id.textfield_adduser_ayah);
        inputEditText_marga = view.findViewById(R.id.textfield_adduser_marga);
        inputEditText_status = view.findViewById(R.id.textfield_adduser_status);

        // Set Object Kontak Pribadi
        inputEditText_nomorhp = view.findViewById(R.id.textfield_adduser_nomorhp);
        inputEditText_email = view.findViewById(R.id.textfield_adduser_email);
        inputEditText_alamatsekarang = view.findViewById(R.id.textfield_adduser_alamat);

        // Set Object Data Identitas
        inputEditText_nikktp = view.findViewById(R.id.textfield_adduser_nikktp);
        inputEditText_tempatlahir = view.findViewById(R.id.textfield_adduser_tempatlahir);
        inputEditText_tanggallahir = view.findViewById(R.id.textfield_adduser_tanggallahir);

        // Set Object Data Perbankan
        inputEditText_namabank = view.findViewById(R.id.textfield_adduser_bank);
        inputEditText_nomorrekening = view.findViewById(R.id.textfield_adduser_nomorrekening);

        setupAdapterBankList();
        inputEditText_tanggallahir.setOnClickListener(v -> showDatePickerDialog());

        return view;

    }

    private void setupAdapterBankList(){
        // Create an ArrayAdapter with the suggestions
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.bank_list)
        );

        // Set the adapter for the AutoCompleteTextView
        inputEditText_namabank.setAdapter(adapter);
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