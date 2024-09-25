package com.example.office;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySearchPrice extends AppCompatActivity {

    private RadioButton rbEgal;

    private RadioButton rbSup;

    private RadioButton rbInf;

    private EditText recherchePrix;

    private TextView resultRecherche;

    private Button buttonRecherchePrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_price);

        rbEgal=findViewById(R.id.rb_egal);
        rbInf=findViewById(R.id.rb_inf);
        rbSup=findViewById(R.id.rb_sup);

        recherchePrix=findViewById(R.id.et_recherchePrix);
        resultRecherche=findViewById(R.id.tv_resultRecherchePrix);

        buttonRecherchePrix=findViewById(R.id.bt_recherchePrix);

        buttonRecherchePrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchByPrix();
            }
        });

    }
    public void searchByPrix(){
        if(rbEgal.isChecked()){
            searchEgal();
        }
        if(rbSup.isChecked()){
            searchSup();
        }
        if(rbInf.isChecked()){
            searchInf();
        }
        if(!rbEgal.isChecked() && !rbInf.isChecked() && !rbSup.isChecked()){
            String lesProduits ="";
            for (int i=0;i<Modele.catalogue.size();i++){
                lesProduits=lesProduits+"\n"+Modele.catalogue.get(i).toString();
            }
            resultRecherche.setText(lesProduits);
        }
    }

    private void searchInf() {
        String lesProduits ="";
        for (int i=0;i<Modele.catalogue.size();i++){
            if(Double.valueOf(recherchePrix.getText().toString()).compareTo(Modele.catalogue.get(i).getPrix())>0){
                lesProduits=lesProduits+"\n"+Modele.catalogue.get(i).toString();
            }
            resultRecherche.setText(lesProduits);
        }
    }

    private void searchSup() {
        String lesProduits ="";
        for (int i=0;i<Modele.catalogue.size();i++){
            if(Double.valueOf(recherchePrix.getText().toString()).compareTo(Modele.catalogue.get(i).getPrix())<0){
                lesProduits=lesProduits+"\n"+Modele.catalogue.get(i).toString();
            }
            resultRecherche.setText(lesProduits);
        }
    }

    private void searchEgal(){
        String lesProduits ="";
        for (int i=0;i<Modele.catalogue.size();i++){
            if(Double.valueOf(recherchePrix.getText().toString()).compareTo(Modele.catalogue.get(i).getPrix())==0){
                lesProduits=lesProduits+"\n"+Modele.catalogue.get(i).toString();
            }
            resultRecherche.setText(lesProduits);
        }
    }
}
