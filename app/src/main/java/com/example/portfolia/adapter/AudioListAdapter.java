package com.example.portfolia.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolia.R;
import com.example.portfolia.util.TimeAgo;

import java.io.File;
import java.io.IOException;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioViewHolder> {

    private File[] allFiles;
    private TimeAgo timeAgo;
    private int row_index=-1;
    private onItemListClick onItemListClick;

    public AudioListAdapter(File[] allFiles, onItemListClick onItemListClick) {
        this.allFiles = allFiles;
        this.onItemListClick = onItemListClick;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        timeAgo = new TimeAgo();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, final int position) {
        holder.list_title.setText(allFiles[position].getName());
        holder.list_date.setText(timeAgo.getTimeAgo(allFiles[position].lastModified()));
       /* holder.constraint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                row_index=position;
                notifyDataSetChanged();
                return true;
            }
        });
        */
        if (row_index==1){
            holder.constraint.setBackgroundColor(Color.parseColor("#d4cbe5"));
        }else  holder.constraint.setBackgroundColor(Color.parseColor("#f5f5f5"));


    }

    @Override
    public int getItemCount() {
        return allFiles.length;
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        private ImageView list_image;
        private TextView list_title;
        private TextView list_date;
        private ConstraintLayout constraint;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);

            list_image = itemView.findViewById(R.id.list_image_view);
            list_title = itemView.findViewById(R.id.list_title);
            list_date = itemView.findViewById(R.id.list_date);
            constraint=itemView.findViewById(R.id.constraint);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemListClick.onClickListener(allFiles[getAdapterPosition()], getAdapterPosition());
        }


        @Override
        public boolean onLongClick(View view) {
            try {
                onItemListClick.onLongClickListener(allFiles[getAdapterPosition()], getAdapterPosition());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public interface onItemListClick {
        void onClickListener(File file, int position);
        void onLongClickListener(File file, int position) throws IOException;
    }

}
