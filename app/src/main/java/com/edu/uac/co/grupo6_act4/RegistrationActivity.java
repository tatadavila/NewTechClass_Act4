package com.edu.uac.co.grupo6_act4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";

    private Button register, cancel;
    private EditText etName, etId, etAddress, etAttentionPlace;
    private TextView tvPositiveTestDate;
    private DatePickerDialog.OnDateSetListener dateListener;

    private Patient pat;
    private PatientController pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        register = findViewById(R.id.registerBtn);
        cancel = findViewById(R.id.cancelBtn);
        etName = findViewById(R.id.nameET);
        etId = findViewById(R.id.idET);
        etAddress = findViewById(R.id.addressET);
        etAttentionPlace = findViewById(R.id.attenPlaceET);
        tvPositiveTestDate = findViewById(R.id.positiveTestDateTV);

        pc = new PatientController(getApplicationContext());

        tvPositiveTestDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrationActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        dateListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/MM/yyyy" + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                tvPositiveTestDate.setText(date);
            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pat = new Patient(etId.getText().toString(), etName.getText().toString(),
                        etAddress.getText().toString(), etAttentionPlace.getText().toString(),
                        tvPositiveTestDate.getText().toString());

                Toast.makeText(getApplicationContext(), pat.id, Toast.LENGTH_SHORT).show();

                if (!pc.findPatient(pat.id)) {
                    Log.d("Buscar", "No Encontrado");
                    Toast.makeText(getApplicationContext(), "Paciente Registrado", Toast.LENGTH_SHORT).show();
                    pc.registerPatient(pat);
                    clearForm();

                } else {
                    Log.d("Buscar", "Encontrado");
                    Toast.makeText(getApplicationContext(), "Paciente Registrado Previamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(i);
                clearForm();
            }
        });
    }

    public void clearForm() {
        if (etName.length() > 0) {
            etName.setText(null);
        }
        if (etId.length() > 0) {
            etId.setText(null);
        }
        if (etAddress.length() > 0) {
            etAddress.setText(null);
        }
        if (etAttentionPlace.length() > 0) {
            etAttentionPlace.setText(null);
        }
        if (tvPositiveTestDate.length() > 0) {
            tvPositiveTestDate.setHint("Seleccionar Fecha");
        }
    }

}
