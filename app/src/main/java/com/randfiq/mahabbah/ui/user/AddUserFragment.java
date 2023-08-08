package com.randfiq.mahabbah.ui.user;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.randfiq.mahabbah.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddUserFragment extends Fragment {

    private View view;

    // Object Data Pribadi
    private TextInputEditText inputEditText_nama;
    private TextInputEditText inputEditText_nama_ayah;
    private TextInputEditText inputEditText_marga;
    private TextInputEditText inputEditText_status;

    // Object Kontak Pribadi
    private TextInputEditText inputEditText_nomor_hp;
    private TextInputEditText inputEditText_email;
    private TextInputEditText inputEditText_alamat;

    // Object Data Identitas
    private TextInputEditText inputEditText_nik_ktp;
    private TextInputEditText inputEditText_tempat_lahir;
    private TextInputEditText inputEditText_tanggal_lahir;

    // Object Data Perbankan
    private AutoCompleteTextView inputAutoCompletTextView_bank;
    private TextInputEditText inputEditText_nomor_rekening;

    String nama, nama_ayah, marga, status;
    String nomor_hp, email, alamat;
    String nik_ktp, tempat_lahir, tanggal_lahir;
    String bank, nomor_rekening;

    boolean isField_Empty = true;

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

        createMenu();

        // Init Object Data Pribadi
        inputEditText_nama = view.findViewById(R.id.textfield_adduser_nama);
        inputEditText_nama_ayah = view.findViewById(R.id.textfield_adduser_ayah);
        inputEditText_marga = view.findViewById(R.id.textfield_adduser_marga);
        inputEditText_status = view.findViewById(R.id.textfield_adduser_status);

        // Init Object Kontak Pribadi
        inputEditText_nomor_hp = view.findViewById(R.id.textfield_adduser_nomor_hp);
        inputEditText_email = view.findViewById(R.id.textfield_adduser_email);
        inputEditText_alamat = view.findViewById(R.id.textfield_adduser_alamat);

        // Init Object Data Identitas
        inputEditText_nik_ktp = view.findViewById(R.id.textfield_adduser_nikktp);
        inputEditText_tempat_lahir = view.findViewById(R.id.textfield_adduser_tempat_lahir);
        inputEditText_tanggal_lahir = view.findViewById(R.id.textfield_adduser_tanggal_lahir);

        // Init Object Data Perbankan
        inputAutoCompletTextView_bank = view.findViewById(R.id.textfield_adduser_bank);
        inputEditText_nomor_rekening = view.findViewById(R.id.textfield_adduser_nomor_rekening);

        // Setup Object
        setupAdapterBankList();
        inputEditText_tanggal_lahir.setOnClickListener(v -> showDatePickerDialog());

        return view;
    }

    private void getTextField(){
        // Get Text Data Pribadi
        nama = String.valueOf(inputEditText_nama.getText());
        nama_ayah = String.valueOf(inputEditText_nama_ayah.getText());
        marga = String.valueOf(inputEditText_marga.getText());
        status = String.valueOf(inputEditText_status.getText());

        nomor_hp = String.valueOf(inputEditText_nomor_hp.getText());
        email = String.valueOf(inputEditText_email.getText());
        alamat = String.valueOf(inputEditText_alamat.getText());

        nik_ktp = String.valueOf(inputEditText_nik_ktp.getText());
        tempat_lahir = String.valueOf(inputEditText_tempat_lahir.getText());
        tanggal_lahir = String.valueOf(inputEditText_tanggal_lahir.getText());

        bank = String.valueOf(inputAutoCompletTextView_bank.getText());
        nomor_rekening = String.valueOf(inputEditText_nomor_rekening.getText());
    }

    private void field_ErrorHelper_Required(TextInputEditText textInputEditText){
        String inputText = String.valueOf(textInputEditText.getText());
        if (inputText.isEmpty()){
            String hint = String.valueOf(textInputEditText.getHint());
            textInputEditText.setError(hint + " " + getString(R.string.form_helper_required));
            isField_Empty = true;
        } else {
            textInputEditText.setError(null);
            isField_Empty = false;
        }
    }

    private void checkField(){
        getTextField();

        field_ErrorHelper_Required(inputEditText_nama);
        field_ErrorHelper_Required(inputEditText_nama_ayah);
        field_ErrorHelper_Required(inputEditText_marga);
        field_ErrorHelper_Required(inputEditText_status);

        field_ErrorHelper_Required(inputEditText_nomor_hp);
        // field_ErrorHelper_Required(inputEditText_email);
        field_ErrorHelper_Required(inputEditText_alamat);

        field_ErrorHelper_Required(inputEditText_nik_ktp);
        field_ErrorHelper_Required(inputEditText_tempat_lahir);
        field_ErrorHelper_Required(inputEditText_tanggal_lahir);

        if (String.valueOf(inputAutoCompletTextView_bank.getText()).isEmpty()){
            String hint = String.valueOf(inputAutoCompletTextView_bank.getHint());
            inputAutoCompletTextView_bank.setError(hint + " " + getString(R.string.form_helper_required));
            isField_Empty = true;
        } else {
            inputAutoCompletTextView_bank.setError(null);
            isField_Empty = false;
        }

        field_ErrorHelper_Required(inputEditText_nomor_rekening);
    }

    private void setupAdapterBankList(){
        // Create an ArrayAdapter with the suggestions
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.bank_list)
        );

        // Set the adapter for the AutoCompleteTextView
        inputAutoCompletTextView_bank.setAdapter(adapter);
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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = dateFormat.format(selectedDate.getTime());

                    inputEditText_tanggal_lahir.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void createMenu() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_pengguna, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.menu_savenew) {
                    checkField();
                    Toast.makeText(requireContext(), "Seve New", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.CREATED);
    }
}