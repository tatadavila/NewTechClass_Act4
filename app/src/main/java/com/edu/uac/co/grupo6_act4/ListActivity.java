package com.edu.uac.co.grupo6_act4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements PatientListener {

    PatientController pC;
    RecyclerView pList;
    PatientCursorAdapter pCA;
    private List<Patient> patientList = new ArrayList<Patient>();

    private Patient patientSelected = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pList = findViewById(R.id.infoListLV);
        pList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fillList(){

        patientList.clear();

        Cursor cursor = pC.allPatients();

        while (cursor.moveToNext()){

            String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("ADDRESS"));
            String attentionPlace = cursor.getString(cursor.getColumnIndexOrThrow("ATTENTION_PLACE"));
            String positiveDate = cursor.getString(cursor.getColumnIndexOrThrow("POSITIVE_TEST_DATE"));

            Patient localPatient = new Patient(id, name, address, attentionPlace, positiveDate);
            patientList.add(localPatient);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        pC = new PatientController(getApplicationContext());

        fillList();

        pCA = new PatientCursorAdapter(patientList, this);
        pList.setAdapter(pCA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_activity_toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(this, "Editar Paciente", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this, ModifyPatientActivity.class);
                intent.putExtra("patientkey", patientSelected);

                startActivity(intent);

                return true;

            case R.id.delete:
                Toast.makeText(this, "Paciente Eliminado", Toast.LENGTH_SHORT).show();

                pC.deletePatient(patientSelected);
                pCA.deletePatient(patientSelected);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(Patient patient) {
        this.patientSelected = patient;
    }
}
