package com.edu.uac.co.grupo6_act4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifyPatientActivity extends AppCompatActivity {

    private Button btnEdit;
    private EditText etName, etId, etAddress, etAttentionPlace;
    private TextView tvPositiveTestDate;

    private Patient patient;

    private PatientController patientController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_patient);

        patientController = new PatientController(this);

        initUI();

        patient = (Patient) getIntent().getExtras().getSerializable("patientkey");

        loadPatientInformation();

    }

    private void initUI(){
        etName = findViewById(R.id.etNameEdit);
        etId = findViewById(R.id.etIdEdit);
        etAddress = findViewById(R.id.etAddressEdit);
        etAttentionPlace = findViewById(R.id.etAttentPlaceEdit);
        tvPositiveTestDate = findViewById(R.id.etPositiveTestDateEdit);
        btnEdit = findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient patientEdited = new Patient(etId.getText().toString(),
                        etName.getText().toString(),
                        etAddress.getText().toString(),
                        etAttentionPlace.getText().toString(),
                        tvPositiveTestDate.getText().toString());

                patientController.updatePatient(patientEdited);

                finish();
            }
        });
    }

    private void loadPatientInformation(){
        etName.setText(patient.name);
        etId.setText(patient.id);
        etAddress.setText(patient.address);
        etAttentionPlace.setText(patient.attentionPlace);
        tvPositiveTestDate.setText(patient.positiveDate);
    }
}
