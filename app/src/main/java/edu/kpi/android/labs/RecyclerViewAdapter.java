package edu.kpi.android.labs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<HistoryItem> items;

    public RecyclerViewAdapter(List<HistoryItem> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textSizeView;
        public final TextView messageView;


        public ViewHolder(View itemView) {
            super(itemView);
            textSizeView = itemView.findViewById(R.id.text_size);
            messageView = itemView.findViewById(R.id.text_message);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryItem item = items.get(position);

        holder.textSizeView.setText(String.valueOf(item.fontSize));
        holder.messageView.setText(item.message);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
