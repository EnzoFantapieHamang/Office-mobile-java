package com.example.office;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button buttonRecherche;


    private Button buttonNvProduit;

    private LinearLayout ll_lstproduits;

    private ArrayList<CheckBox> lstCheckBox=new ArrayList<>();

    private Button buttonRemove;
    private Spinner spinnerLst;
    private ActivityResultLauncher<Intent> activityAvecRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Modele.init();
        ll_lstproduits=findViewById(R.id.ll_lstproduits);

        refreshList();


       buttonNvProduit=findViewById(R.id.buttonNvProduit);

       buttonRecherche=findViewById(R.id.buttonRecherche);

       buttonRemove=findViewById(R.id.buttonRemoveProduct);


       buttonNvProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivityAjout.class);
                activityAvecRetour.launch(intent);
            }
        });


        buttonRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivitySearch.class);
                startActivity(intent);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeProduct();
            }
        });

        activityAvecRetour = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        switch (result.getResultCode()) {
                            case 10 :
                                refreshList();
                                break;
                        }
                    }
                });

    }
    private void removeProduct() {
          for(int i=(Modele.catalogue.size())-1;i>-1;i--){
              if(lstCheckBox.get(i).isChecked()){
                  Modele.catalogue.remove(i);
              }
          }
          refreshList();
    }

    private void refreshList() {
        ll_lstproduits.removeAllViewsInLayout();
        lstCheckBox.clear();
        for(int i= 0;i<Modele.catalogue.size();i++){
            LinearLayout unLayout = new LinearLayout(getApplicationContext());
            unLayout.setOrientation(LinearLayout.HORIZONTAL);

            CheckBox cb_suppr= new CheckBox(getApplicationContext());

            TextView tv_ref = new TextView(getApplicationContext());
            tv_ref.setText(Modele.catalogue.get(i).getRef());
            TextView tv_nom = new TextView(getApplicationContext());
            tv_nom.setText(Modele.catalogue.get(i).getNom());
            TextView tv_prix = new TextView(getApplicationContext());
            tv_prix.setText(Modele.catalogue.get(i).getPrix()+"");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;

            tv_ref.setLayoutParams(params);
            tv_nom.setLayoutParams(params);
            tv_prix.setLayoutParams(params);
            cb_suppr.setLayoutParams(params);


            unLayout.addView(cb_suppr);
            unLayout.addView(tv_ref);
            unLayout.addView(tv_nom);
            unLayout.addView(tv_prix);

            lstCheckBox.add(cb_suppr);
            ll_lstproduits.addView(unLayout);
        }
    }


}