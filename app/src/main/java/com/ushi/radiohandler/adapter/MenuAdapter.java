package com.ushi.radiohandler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ushi.radiohandler.R;
import com.ushi.radiohandler.data.Menu;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Holder> {

    private RecyclerView mRecyclerView;

    private OnItemSelectedListener mListener;

    public interface OnItemSelectedListener {

        void onItemSelected(MenuAdapter adapter, Menu menu, int position);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mRecyclerView.getContext()).inflate(R.layout.item_menu, parent, false);
        Holder holder = new Holder(view);
        holder.itemView.setOnClickListener(mItemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.name.setText(Menu.values()[position].name);
    }

    @Override
    public int getItemCount() {
        return Menu.values().length;
    }

    private View.OnClickListener mItemClickListener = v -> {
        int position = mRecyclerView.getChildAdapterPosition(v);
        if (mListener != null
                && position != RecyclerView.NO_POSITION) {
            mListener.onItemSelected(this, Menu.values()[position], position);
        }
    };

    static class Holder extends RecyclerView.ViewHolder {

        TextView name;

        public Holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
