package cn.bavelee.coupletimetable.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.bavelee.coupletimetable.R;
import me.drakeet.multitype.ItemViewBinder;

public class TitleViewBinder extends ItemViewBinder<Title, TitleViewBinder.Holder> {
    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(inflater.inflate(R.layout.item_title, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, @NonNull Title title) {
        holder.tvTitle.setText(title.getTitle());
        holder.tvTitle.setTextColor(title.isToday() ? holder.tvTitle.getContext().getColor(R.color.colorGirl) : Color.BLACK);
        holder.tvTitle.setTypeface(title.isToday() ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView;
        }
    }
}
