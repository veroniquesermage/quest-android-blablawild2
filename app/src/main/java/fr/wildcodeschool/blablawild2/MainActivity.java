package fr.wildcodeschool.blablawild2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Welcome into BlaBlaWild!");

//        ItineraryModel itineraryModel = new ItineraryModel("Toulouse", "Paris", "Eric Cartman", new Date(), 15);
        DatabaseReference itineraryRef = database.getReference("itinerary");
//        itineraryRef.setValue(itineraryModel);

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // récupère la donnée contenue et la convertie en String
//                String value = dataSnapshot.getValue(String.class);
//                // affiche la donnée contenue
//                Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // en cas d'erreur de récupération de la donnée
//                Toast.makeText(MainActivity.this, "Failed to read value.", Toast.LENGTH_LONG).show();
//            }
//        });
        itineraryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // récupère la donnée contenue et la convertie en ItineraryModel
                ItineraryModel itinerary = dataSnapshot.getValue(ItineraryModel.class);
                // affiche le conducteur de l'itineraire
//                Toast.makeText(MainActivity.this, itinerary.getDriver(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // en cas d'erreur de récupération de la donnée
                Toast.makeText(MainActivity.this, "Failed to read value.", Toast.LENGTH_LONG).show();
            }
        });


        Button bAddItinerary = findViewById(R.id.b_add_itinerary);
        bAddItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItineraryCreateActivity.class);
                startActivity(intent);
            }
        });

        Button bSearchItinerary = findViewById(R.id.b_search_itinerary);
        bSearchItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItinerarySearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
