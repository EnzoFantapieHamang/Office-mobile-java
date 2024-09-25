package com.example.office;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityAjout extends AppCompatActivity {

    private Button buttonRecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        this.actualiserListe();


        Button boutonActListe=(Button) findViewById(R.id.buttonActListe);
        Button boutonEnregistrer=(Button) findViewById(R.id.boutonEnregistrer);

        Button boutonRecherche=findViewById(R.id.buttonRechercher);

        boutonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enregistrerProduit();
            }
        });

        boutonActListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualiserListe();
            }
        });
        boutonRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivitySearch.class);
                startActivity(intent);
            }
        });

    }
    public void actualiserListe(){
        TextView listeProduit=findViewById(R.id.textView4);
        listeProduit.setText("Liste des produits");
        String lesProduits=listeProduit.getText().toString();
        for (int i=0;i<Modele.catalogue.size();i++){
            lesProduits=lesProduits+"\n"+Modele.catalogue.get(i).toString();
        }
        listeProduit.setText(lesProduits);

    }

    public void enregistrerProduit() {
        EditText  reference=findViewById(R.id.et_ref);
        EditText  nom=findViewById(R.id.et_nom);
        EditText  prix=findViewById(R.id.et_prix);

        String referenceTexte=reference.getText().toString();
        String nomTexte=nom.getText().toString();
        Double prixTexte= Double.valueOf(prix.getText().toString());

        Produit NvProduit=new Produit(referenceTexte,nomTexte,prixTexte);
        Modele.catalogue.add(NvProduit);
        Intent intentDeRetour = new Intent();
        setResult(10,intentDeRetour);
        finish();


    }
}