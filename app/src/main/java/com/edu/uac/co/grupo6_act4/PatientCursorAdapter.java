package com.edu.uac.co.grupo6_act4;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PatientCursorAdapter extends RecyclerView.Adapter<PatientCursorAdapter.PatientViewHolder> {

    List<String> selectedIdsList = new ArrayList<>();

    private List<Patient> dataPatients;
    private PatientListener patienListener;

    public PatientCursorAdapter(List<Patient> dataPatients, PatientListener patientListener){
        this.dataPatients = dataPatients;
        this.patienListener = patientListener;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_data, parent, false);
        return new PatientViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {

        Patient patient = dataPatients.get(position);

        holder.txtVIds.setText(patient.id);
        holder.txtVNames.setText(patient.name);
        holder.txtVAddresses.setText(patient.address);
        holder.txtVAttentPlaces.setText(patient.attentionPlace);
        holder.txtVPositiveDates.setText(patient.positiveDate);

    }

    @Override
    public int getItemCount() {
        return dataPatients.size();
    }

    public void deletePatient(Patient patient) {
        this.dataPatients.remove(patient);
        notifyDataSetChanged();
    }



    public class PatientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CheckBox selectionCB;
        public TextView txtVIds;
        public TextView txtVNames;
        public TextView txtVAddresses;
        public TextView txtVAttentPlaces;
        public TextView txtVPositiveDates;


        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);

            init(itemView);

            selectionCB.setOnClickListener(this);

        }

        public void init(View view){
            selectionCB = view.findViewById(R.id.selectCheckBox);
            txtVIds = view.findViewById(R.id.idTextV);
            txtVNames = view.findViewById(R.id.nameTxtV);
            txtVAddresses =  view.findViewById(R.id.addressTxtV);
            txtVAttentPlaces =  view.findViewById(R.id.attenPlaceTxtV);
            txtVPositiveDates =  view.findViewById(R.id.positiveDateTxtV);
        }

        @Override
        public void onClick(View v) {
            patienListener.itemClicked(dataPatients.get(getLayoutPosition()));
        }
    }
}
