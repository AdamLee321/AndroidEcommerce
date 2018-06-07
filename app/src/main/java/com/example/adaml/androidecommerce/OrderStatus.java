package com.example.adaml.androidecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adaml.androidecommerce.Common.Common;
import com.example.adaml.androidecommerce.Model.Order;
import com.example.adaml.androidecommerce.Model.SubmitOrder;
import com.example.adaml.androidecommerce.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<SubmitOrder, OrderViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference orders;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database = FirebaseDatabase.getInstance();
        orders = database.getReference("Orders");

        recyclerView = (RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(Common.currentUser.getPhone());
    }

    private void loadOrders(String phone) {
        adapter = new FirebaseRecyclerAdapter<SubmitOrder, OrderViewHolder>(
                SubmitOrder.class, R.layout.order_layout, OrderViewHolder.class, orders.orderByChild("phone").equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, SubmitOrder model, int position) {
                viewHolder.txtOrderNumber.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderAddress.setText(model.getAddress());
                viewHolder.txtOrderPhone.setText(model.getPhone());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private String convertCodeToStatus(String status) {
        if(status.equals("0")){
            return "Order Placed";
        }
        else if(status.equals("1")){
            return "Out For Delivery";
        }
        else if(status.equals("2")){
            return "Shipped";
        }
        else
            return "Delivered";
    }
}
