package com.example.office;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivitySearch extends AppCompatActivity {

    private EditText searchField;
    private TextView resultSearch;
    private Button buttonRecherche;

    private LinearLayout searchLayout;

    private ArrayList<String> lstChoiceSearch=new ArrayList<>();

    private Spinner choiceSearchWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);

        initSpinner();

        searchField=findViewById(R.id.et_searchField);
        resultSearch=findViewById(R.id.tv_resulteRecherche);
        buttonRecherche=findViewById(R.id.button);
        searchLayout=findViewById(R.id.searchLayout);

        buttonRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultSearch();
            }
        });

        choiceSearchWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchLayout.removeAllViewsInLayout();
                switch (i){
                    case 0:
                        searchField.setHint("Reference");
                        searchField.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        searchField.setHint("Nom");
                        searchField.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 2:
                        searchField.setHint("Prix");
                        searchField.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    private void initSpinner(){
        lstChoiceSearch.add("Reference");
        lstChoiceSearch.add("Nom");
        lstChoiceSearch.add("Prix");

        choiceSearchWay=findViewById(R.id.spinner);
        ArrayAdapter<String> spinAdapter=new ArrayAdapter<String>(this,R.layout.spinner_element);
        for(int i=0;i<lstChoiceSearch.size();i++){
            spinAdapter.add(lstChoiceSearch.get(i));
        }
        choiceSearchWay.setAdapter(spinAdapter);
    }
    public void resultSearch(){
        resultSearch.setText("");
        String value=searchField.getText().toString();
        switch (choiceSearchWay.getSelectedItemPosition()){
            case 0:
                for (Produit produit : Modele.catalogue) {
                    if (produit.getRef().equals(value)) {
                        resultSearch.setText(produit.toString());
                    }
                }
                break;
            case 1:
                for (Produit produit : Modele.catalogue) {
                    if (produit.getNom().contains(value)) {
                        resultSearch.setText(resultSearch.getText().toString() + "\n" + produit.toString());
                    }
                }
                break;
            case 2:
                for (Produit produit : Modele.catalogue) {
                    if (Double.valueOf(value).compareTo(produit.getPrix())==0) {
                        resultSearch.setText(resultSearch.getText().toString() + "\n" + produit.toString());
                    }
                }
                break;
        }

/*        resultSearch.setText("");

        if(!nom.isEmpty()) {
            for (Produit produit : Modele.catalogue) {
                if (produit.getNom().contains(nom)) {
                    resultSearch.setText(resultSearch.getText().toString() + "\n" + produit.toString());
                }
            }
        }
        else{
            for (Produit produit : Modele.catalogue) {
                if (produit.getRef().equals(ref)) {
                    resultSearch.setText(produit.toString());
                }
            }
        }*/

    }
}