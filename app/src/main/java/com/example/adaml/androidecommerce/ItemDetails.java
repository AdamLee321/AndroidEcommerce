package com.example.adaml.androidecommerce;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.adaml.androidecommerce.Database.Database;
import com.example.adaml.androidecommerce.Model.Item;
import com.example.adaml.androidecommerce.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import javax.xml.transform.Templates;

public class ItemDetails extends AppCompatActivity {

    TextView item_name, item_price, item_description;
    ImageView item_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String itemId="";

    //Database
    FirebaseDatabase database;
    DatabaseReference items;

    Item currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        //Database
        database = FirebaseDatabase.getInstance();
        items = database.getReference("Items");

        //Initialize the view
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        itemId,
                        currentItem.getName(),
                        numberButton.getNumber(),
                        currentItem.getPrice(),
                        currentItem.getDiscount()
                ));
                Toast.makeText(ItemDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollaspedAppBar);

        item_description = (TextView) findViewById(R.id.item_description);
        item_price = (TextView) findViewById(R.id.item_price);
        item_name = (TextView) findViewById(R.id.item_name);
        item_image = (ImageView) findViewById(R.id.item_image);

        //Get the items Id from intent
        if(getIntent() != null){
            itemId = getIntent().getStringExtra("ItemId");
        }
        if(!itemId.isEmpty()){
            getItemDetails(itemId);
        }
    }

    private void getItemDetails(String itemId) {
        items.child(itemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentItem = dataSnapshot.getValue(Item.class);

                //set the image when found
                Picasso.with(getBaseContext()).load(currentItem.getImage()).into(item_image);

                collapsingToolbarLayout.setTitle(currentItem.getName());

                item_name.setText(currentItem.getName());
                item_price.setText(currentItem.getPrice());
                item_description.setText(currentItem.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
