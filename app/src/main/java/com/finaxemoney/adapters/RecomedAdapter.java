package com.finaxemoney.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finaxemoney.R;

public class RecomedAdapter extends RecyclerView.Adapter<RecomedAdapter.RecomendHolder> {

    Context context;

    String []   bankname= {"Mi Credit",
            "Fullerton",
            "Dhani",
            "Paysense",
            "Money Tap",
            "Early Salary",
            "Kreditbee",
            "Mobikwik",
            "Money View",
            "Loan Tap",
    };

    int []   amount= {200000,
            500000,
            1500000,
            500000,
            500000,
            200000,
            200000,
            60000,
            500000,
            300000
    };

    String [] tenure={"36 months",
            "60 months",
            "60 months",
            "60 months",
            "36 months",
            "12 months",
            "24 months",
            "18 months",
            "60 months",
            "60 months"
    };


    String [] links={"https://play.google.com/store/apps/details?id=com.micredit.in.gp",
    "https://play.google.com/store/apps/details?id=com.fullerton.instaloan",
    "https://pf43w.app.link/4qlzlckaf8",
    "https://www.gopaysense.com/refer-friend/?referral_code=6218714c",
    "http://ref.moneytap.com/mone5b55s-1rf/3",
    "http://bit.ly/2YNd93e",
    "https://kb.onelink.me/02Je/kbrefer",
    "https://sak38.app.goo.gl/share-boost",
    "https://moneyview.whizdm.com/share_lref?ref=DEEPRANS",
    "https://play.google.com/store/apps/details?id=in.loantap.app"

};



    int [] imgId={ R.drawable.b1, R.drawable.b2,
            R.drawable.b3,
            R.drawable.b4,
            R.drawable.b5,
            R.drawable.b6,
            R.drawable.b7,
            R.drawable.b8,
            R.drawable.b9,
            R.drawable.b10
    };




    public RecomedAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecomendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.recomend_cardlist, parent ,false);
        return new RecomendHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecomendHolder holder, final int position) {

           holder.bankImage.setImageResource(imgId[position]);
           holder.amount.setText(""+ amount[position]);
           holder.tenure.setText(tenure[position]);
           holder.applyBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i2 = new Intent(Intent.ACTION_VIEW);
                   i2.setData(Uri.parse(links[position]));
                   i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(i2);
               }
           });

    }

    @Override
    public int getItemCount() {
        return bankname.length;
    }



    class RecomendHolder extends RecyclerView.ViewHolder {

        TextView  tenure , amount;
        Button applyBtn;
        ImageView bankImage;


        public RecomendHolder(@NonNull View itemView) {
            super(itemView);
            tenure = itemView.findViewById(R.id.txt_tenure);
            amount = itemView.findViewById(R.id.txt_amount);
            applyBtn = itemView.findViewById(R.id.applybtn);
            bankImage =  itemView.findViewById(R.id.iv_bnk);
        }
    }
}
