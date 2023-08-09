package com.randfiq.mahabbah.ui.user;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.randfiq.mahabbah.R;
import com.randfiq.mahabbah.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddUserFragment extends Fragment implements Constant {

    private View view;

    AlertDialog AlertDialog_AddUserConfirm;
    View dvDialog_AddUserConfirm;
    AlertDialog.Builder builder;
    ViewGroup viewGroup;

    String formattedDate;

    // Object Data Pribadi
    private TextInputEditText inputEditText_nama;
    private TextInputEditText inputEditText_nama_ayah;
    private TextInputEditText inputEditText_marga;
    private TextInputEditText inputEditText_status;

    // Object Kontak Pribadi
    private TextInputEditText inputEditText_nomor_hp;
    private TextInputEditText inputEditText_email;
    private TextInputEditText inputEditText_alamat;
    private AutoCompleteTextView inputAutoCompletTextView_wilayah;

    // Object Data Identitas
    private TextInputEditText inputEditText_nik_ktp;
    private TextInputEditText inputEditText_tempat_lahir;
    private TextInputEditText inputEditText_tanggal_lahir;

    // Object Data Perbankan
    private AutoCompleteTextView inputAutoCompletTextView_bank;
    private TextInputEditText inputEditText_nomor_rekening;

    String nama, nama_ayah, marga, status;
    String nomor_hp, email, wilayah, alamat;
    String nik_ktp, tempat_lahir, tanggal_lahir;
    String nama_bank, nomor_rekening;

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
        inputAutoCompletTextView_wilayah = view.findViewById(R.id.textfield_adduser_wilayah);

        // Init Object Data Identitas
        inputEditText_nik_ktp = view.findViewById(R.id.textfield_adduser_nik_ktp);
        inputEditText_tempat_lahir = view.findViewById(R.id.textfield_adduser_tempat_lahir);
        inputEditText_tanggal_lahir = view.findViewById(R.id.textfield_adduser_tanggal_lahir);

        // Init Object Data Perbankan
        inputAutoCompletTextView_bank = view.findViewById(R.id.textfield_adduser_bank);
        inputEditText_nomor_rekening = view.findViewById(R.id.textfield_adduser_nomor_rekening);

        // Setup Object
        setupAdapter();
        inputEditText_tanggal_lahir.setOnClickListener(v -> showDatePickerDialog());

        inputAutoCompletTextView_wilayah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                inputAutoCompletTextView_wilayah.setError(null);
            }
        });

        inputAutoCompletTextView_bank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                inputAutoCompletTextView_bank.setError(null);
            }
        });

        return view;
    }

    private void setupAdapter(){
        // Create an ArrayAdapter
        ArrayAdapter<String> adapter_listBank = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.list_bank)
        );

        ArrayAdapter<String> adapter_listWilayah = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.list_wilayah)
        );

        // Set the adapter for the AutoCompleteTextView
        inputAutoCompletTextView_bank.setAdapter(adapter_listBank);
        inputAutoCompletTextView_wilayah.setAdapter(adapter_listWilayah);
    }

    private void getTextField(){
        // Get Text Data Pribadi
        nama = String.valueOf(inputEditText_nama.getText());
        nama_ayah = String.valueOf(inputEditText_nama_ayah.getText());
        marga = String.valueOf(inputEditText_marga.getText());
        status = String.valueOf(inputEditText_status.getText());

        nomor_hp = String.valueOf(inputEditText_nomor_hp.getText());
        email = String.valueOf(inputEditText_email.getText());
        wilayah = String.valueOf(inputAutoCompletTextView_wilayah.getText());
        alamat = String.valueOf(inputEditText_alamat.getText());

        nik_ktp = String.valueOf(inputEditText_nik_ktp.getText());
        tempat_lahir = String.valueOf(inputEditText_tempat_lahir.getText());
        tanggal_lahir = String.valueOf(inputEditText_tanggal_lahir.getText());

        nama_bank = String.valueOf(inputAutoCompletTextView_bank.getText());
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

        if (String.valueOf(inputAutoCompletTextView_wilayah.getText()).isEmpty()){
            String hint = String.valueOf(inputAutoCompletTextView_wilayah.getHint());
            inputAutoCompletTextView_wilayah.setError(hint + " " + getString(R.string.form_helper_required));
            isField_Empty = true;
        } else {
            inputAutoCompletTextView_wilayah.setError(null);
            isField_Empty = false;
        }

        field_ErrorHelper_Required(inputEditText_nomor_rekening);
    }

    private void openDialogInsertOperation(){
        builder = new AlertDialog.Builder(requireActivity());
        viewGroup = requireActivity().findViewById(android.R.id.content);
        dvDialog_AddUserConfirm = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_user_confirm, viewGroup, false);

        // Init Object Dialog Insert Data
        Button button_saveNewData = dvDialog_AddUserConfirm.findViewById(R.id.button_saveNewData);
        button_saveNewData.setOnClickListener(view -> insertDataToSheet());

        button_saveNewData.setOnClickListener(view -> {
            insertDataToSheet();
            AlertDialog_AddUserConfirm.dismiss();
        });

        builder.setView(dvDialog_AddUserConfirm);
        AlertDialog_AddUserConfirm = builder.create();
        AlertDialog_AddUserConfirm.show();
    }

    private void insertDataToSheet() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                WebAppBaseURL,
                response -> {
                    Toast.makeText(requireContext(), response, Toast.LENGTH_LONG).show();
                    if(response.equals("Success")){
                        NavController navController = Navigation.findNavController(view);
                        navController.popBackStack();
                        Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    Toast.makeText(requireContext(), "Gagal menyimpan data, mohon coba lagi", Toast.LENGTH_LONG).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(gscriptAction_key, gscriptAction_insertData_Pengguna);

                params.put(key_nama, nama);
                params.put(key_nama_ayah, nama_ayah);
                params.put(key_marga, marga);
                params.put(key_status, status);

                params.put(key_nomor_hp, nomor_hp);
                params.put(key_email, email);
                params.put(key_wilayah, wilayah);
                params.put(key_alamat, alamat);

                params.put(key_nik_ktp, nik_ktp);
                params.put(key_tempat_lahir, tempat_lahir);
                params.put(key_tanggal_lahir, tanggal_lahir);

                params.put(key_nama_bank, nama_bank);
                params.put(key_nomor_rekening, nomor_rekening);

                return params;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 20 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        queue.add(stringRequest);
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
                    formattedDate = dateFormat.format(selectedDate.getTime());
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
                    if (!isField_Empty){
                        openDialogInsertOperation();
                    }
                    // Toast.makeText(requireContext(), "Seve New", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.CREATED);
    }
}