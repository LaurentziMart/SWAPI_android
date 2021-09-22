package com.laubor.starwarscvapp.activities.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.model.Starship;
import com.laubor.starwarscvapp.model.Transport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StarshipsRecyclerViewAdapter extends RecyclerView.Adapter<StarshipsRecyclerViewAdapter.ViewHolder> {

    private List<Starship> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public StarshipsRecyclerViewAdapter(Context context, List<Starship> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.vehicle_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mData.get(position).getName();
        String model = mData.get(position).getModel();
        holder.tvName.setText(name);
        holder.tvModel.setText(model);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvModel)
        TextView tvModel;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onStarshipClick(view, mData.get(getAdapterPosition()));
        }
    }


    Transport getItem(int id) {
        return mData.get(id);
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onStarshipClick(View view, Starship starship);
    }
}