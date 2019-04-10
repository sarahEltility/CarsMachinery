package com.awoktask.sarah.carsmachinery.View.Adapter;

/**
 * Created by sarah on 4/5/19.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import okhttp3.OkHttpClient;

import com.awoktask.sarah.carsmachinery.Model.CarsData.Car;
import com.awoktask.sarah.carsmachinery.Model.CarsData.CarsListResponse;
import com.awoktask.sarah.carsmachinery.R;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.Timer;

/***
 * recyclerView Adapter
 * recyclerView.ViewHolder
 * */

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {

    private final int THREE_MIN_IN_SECONDS = 3*60*60;
    private Context context;
    private List<Car> carsListData;
    private int ticks;

    public CarsAdapter(Context context, List<Car> carsListData,  int ticks) {
        this.context = context;
        this.carsListData = carsListData;
        this.ticks=ticks;
    }


    class CarsViewHolder extends RecyclerView.ViewHolder{
       LinearLayout mCardView;
       public ImageView imageView;
       public TextView textViewmake,textViewPrice,textViewLotValue,textViewAED,TextBidsValue,txtviewModel,textViewYear;
       public TextView TextTimeValue;

        CarsViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = (LinearLayout) itemView;
            TextTimeValue = itemView.findViewById(R.id.txtviewTimeValue);
            imageView =  itemView.findViewById(R.id.imageView);
            textViewmake = itemView.findViewById(R.id.textViewmake);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewLotValue =itemView.findViewById(R.id.textViewLotValue);
            textViewAED = itemView.findViewById(R.id.textViewAED);
            TextBidsValue = itemView.findViewById(R.id.TextBidsValue);
            txtviewModel =itemView.findViewById(R.id.TxtviewModel);
            textViewYear = itemView.findViewById(R.id.txtviewYear);

        }
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LinearLayout cardView = (LinearLayout) inflater.inflate(R.layout.list_layout, parent, false);
        CarsViewHolder holder = new CarsViewHolder(cardView);
        return new CarsViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        holder.textViewmake.setText(carsListData.get(position).getMakeEn());
        holder.textViewYear.setText(carsListData.get(position).getYear()+"");
        holder.txtviewModel.setText(carsListData.get(position).getModelEn());
        holder.TextBidsValue.setText(carsListData.get(position).getAuctionInfo().getBids()+"");
        holder.textViewAED.setText(carsListData.get(position).getAuctionInfo().getCurrencyEn());
        holder.textViewPrice.setText(carsListData.get(position).getAuctionInfo().getCurrentPrice()+"");
        holder.textViewLotValue.setText(carsListData.get(position).getAuctionInfo().getLot()+"");




        String imageUrl =carsListData.get(position).getImage();
        imageUrl= imageUrl.replace("[w]","200");
        imageUrl= imageUrl.replace("[h]","200");
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(imageUrl
        )
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        if(ticks<=THREE_MIN_IN_SECONDS)
            holder.TextTimeValue.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        holder.TextTimeValue.setText(ticks+"");




    }

    @Override
    public int getItemCount() {
        return carsListData.size();
    }
    // Clean all elements of the recycler

    public void clear() {

        carsListData.clear();

        notifyDataSetChanged();

    }



// Add a list of items -- change to type used

    public void addAll(List<Car> list) {

        carsListData.addAll(list);

        notifyDataSetChanged();

    }

}
