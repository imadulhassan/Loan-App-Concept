package com.finaxemoney.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.finaxemoney.R;
import com.finaxemoney.model.TransactionPojo;
import com.finaxemoney.tools.CONSTANT;

import java.text.DecimalFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private int i = 0;
    private Context context;
    private List<TransactionPojo> transactionPojoList;

    public TransactionAdapter(List<TransactionPojo> transactionPojoList, Context context){
        super();
        this.transactionPojoList = transactionPojoList;
        this.context = context;
    }

    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transactions, parent, false);
        TransactionAdapter.ViewHolder viewHolder = new TransactionAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TransactionAdapter.ViewHolder holder, int position) {
        final TransactionPojo transactionPojo =  transactionPojoList.get(position);

        holder.txnDate.setText(transactionPojo.getDate());
        holder.txnRemark.setText(transactionPojo.getRemark());
        CharSequence txnType = transactionPojo.getType();
        if (txnType!=null) {
            int i =position+1;
            holder.txnType.setText(transactionPojo.getId()+"");

//            if (transactionPojo.getWallet().equals("PayPal")){
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("+ "+CONSTANT.INTERNATIONAL_CURRENCY_SIGN);
//                stringBuilder.append(new DecimalFormat("##.#").format(transactionPojo.getAmount()));
//                holder.txnAmount.setText(stringBuilder.toString());
//            }
//            else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(""+CONSTANT.INTERNATIONAL_CURRENCY_SIGN);
            stringBuilder.append(new DecimalFormat("##.#").format(transactionPojo.getAmount()));
            holder.txnAmount.setText(stringBuilder.toString());
//            }

            holder.txnGateway.setText(transactionPojo.getWallet());
            holder.txnStatus.setText("Complete");

            if (transactionPojo.getWallet().length() < 3){
                holder.txnGateway.setVisibility(View.GONE);
            }
            else {
                holder.txnGateway.setVisibility(View.VISIBLE);
            }

            holder.txnType.setTextColor(Color.parseColor("#ff1e7e34"));
            holder.txnGateway.setTextColor(Color.parseColor("#ff1e7e34"));
            holder.txnAmount.setTextColor(Color.parseColor("#ff1e7e34"));
            holder.txnStatus.setTextColor(Color.parseColor("#ff1e7e34"));

        } else if (txnType.equals("debit")) {

            holder.txnType.setText(txnType);

            if (transactionPojo.getWallet().equals("PayPal")){
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("- "+CONSTANT.INTERNATIONAL_CURRENCY_SIGN);
                stringBuilder2.append(transactionPojo.getAmount());
                holder.txnAmount.setText(stringBuilder2.toString());
            }
            else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("- "+ CONSTANT.INTERNATIONAL_CURRENCY_SIGN);
                stringBuilder2.append(transactionPojo.getAmount());
                holder.txnAmount.setText(stringBuilder2.toString());
            }

            holder.txnGateway.setText(transactionPojo.getWallet());

            if (transactionPojo.getWallet().length() < 3){
                holder.txnGateway.setVisibility(View.GONE);
            }
            else {
                holder.txnGateway.setVisibility(View.VISIBLE);
            }

            holder.txnType.setTextColor(Color.parseColor("#ff0000"));
            holder.txnGateway.setTextColor(Color.parseColor("#ff0000"));

            if (transactionPojo.getPayment_status().equals("1")){
                holder.txnStatus.setText("Pending");
                holder.txnAmount.setTextColor(Color.parseColor("#ffff8c00"));
                holder.txnStatus.setTextColor(Color.parseColor("#ffff8c00"));
            }
            else  if (transactionPojo.getPayment_status().equals("1")){
                holder.txnStatus.setText("Complete");
                holder.txnAmount.setTextColor(Color.parseColor("#ff1e7e34"));
                holder.txnStatus.setTextColor(Color.parseColor("#ff1e7e34"));
            }
            else if (transactionPojo.getPayment_status().equals("2")){
                holder.txnStatus.setText("Rejected");
                holder.txnAmount.setTextColor(Color.parseColor("#ff0000"));
                holder.txnStatus.setTextColor(Color.parseColor("#ff0000"));
            }
            else {
                holder.txnStatus.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return transactionPojoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txnAmount;
        TextView txnDate;
        TextView txnRemark;
        TextView txnType;
        TextView txnGateway;
        TextView txnStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            this.txnType = (TextView) itemView.findViewById(R.id.txnType);
            this.txnRemark = (TextView) itemView.findViewById(R.id.txnRemark);
            this.txnDate = (TextView) itemView.findViewById(R.id.txnDate);
            this.txnAmount = (TextView) itemView.findViewById(R.id.txnAmount);
            this.txnGateway = (TextView) itemView.findViewById(R.id.txnGateway);
            this.txnStatus = (TextView) itemView.findViewById(R.id.txnStatus);
        }

    }
}
