package com.finaxemoney.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.finaxemoney.R;
import com.finaxemoney.adapters.TransactionAdapter;
import com.finaxemoney.model.TransactionPojo;
import com.finaxemoney.tools.CONSTANT;
import com.finaxemoney.tools.ExtraOperations;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {
    private View view;
    ShimmerFrameLayout mShimmerViewContainer;

    private LinearLayout noTxnsLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<TransactionPojo> transactionPojoList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();

        this.transactionPojoList = new ArrayList();
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if (new ExtraOperations().haveNetworkConnection(this)) {
            loadMyTransactions();
        }else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    private void loadMyTransactions() {
        transactionPojoList.clear();
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        noTxnsLayout.setVisibility(View.GONE);
         FirebaseDatabase.getInstance().getReference().child(CONSTANT.TRANSACTION).child(FirebaseAuth.getInstance().getUid())
                 .addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         if(dataSnapshot.exists()){
                                  for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                      TransactionPojo pojo= snapshot.getValue(TransactionPojo.class);
                                      transactionPojoList.add(pojo);

                                  }
                             if (!transactionPojoList.isEmpty()){
                                 adapter = new TransactionAdapter(transactionPojoList,getApplicationContext());
                                 adapter.notifyDataSetChanged();
                                 recyclerView.setAdapter(adapter);
                                 mShimmerViewContainer.stopShimmer();
                                 mShimmerViewContainer.setVisibility(View.GONE);
                                 noTxnsLayout.setVisibility(View.GONE);
                                 recyclerView.setVisibility(View.VISIBLE);
                             }

                         }else{

                             mShimmerViewContainer.stopShimmer();
                             mShimmerViewContainer.setVisibility(View.GONE);
                             recyclerView.setVisibility(View.GONE);
                             noTxnsLayout.setVisibility(View.VISIBLE);

                         }


                     }
                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                         mShimmerViewContainer.stopShimmer();
                         mShimmerViewContainer.setVisibility(View.GONE);
                         recyclerView.setVisibility(View.GONE);
                         noTxnsLayout.setVisibility(View.VISIBLE);

                     }
                 });



    }


    private void initView() {
        this.mShimmerViewContainer = (ShimmerFrameLayout)findViewById(R.id.shimmer_container);
        this.noTxnsLayout = (LinearLayout) findViewById(R.id.noTxnLayout);
        this.recyclerView = (RecyclerView) findViewById(R.id.txnListRecyclerView);
    }




    @Override
    protected void onResume() {
        super.onResume();
        this.mShimmerViewContainer.startShimmer();

    }


    @Override
    protected void onPause() {
        this.mShimmerViewContainer.stopShimmer();
        super.onPause();
    }

}
