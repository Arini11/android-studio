package me.arnaumas.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.arnaumas.myapplication.viewHolders.BaseViewHolder;
import me.arnaumas.myapplication.viewHolders.HeaderItemViewHolder;
import me.arnaumas.myapplication.viewHolders.ImageItemViewHolder;
import me.arnaumas.myapplication.viewHolders.LargeHeaderItemViewHolder;
import me.arnaumas.myapplication.viewHolders.ParagraphItemViewHolder;


/**
 * Created by Michael Stoddart
 * 08/04/2018.
 */

public class MultiElementAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int LARGE_HEADER = 0;
    private static final int HEADER = 1;
    private static final int PARAGRAPH = 2;
    private static final int IMAGE = 3;

    private List<Content> contentList;

    public MultiElementAdapter(List<Content> contents){
        this.contentList = contents;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case LARGE_HEADER:
                return new LargeHeaderItemViewHolder(inflater.inflate(R.layout.view_holder_large_header, parent, false));
            case HEADER:
                return new HeaderItemViewHolder(inflater.inflate(R.layout.view_holder_header, parent, false));
            case PARAGRAPH:
                return new ParagraphItemViewHolder(inflater.inflate(R.layout.view_holder_paragraph, parent, false));
            case IMAGE:
                return new ImageItemViewHolder(inflater.inflate(R.layout.view_holder_image, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setPayload(contentList.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    @Override
    public int getItemViewType(int position) {

        switch (contentList.get(position).getType()){
            case 0:
                return LARGE_HEADER;
            case 1:
                return HEADER;
            case 2:
               return PARAGRAPH;
            case 3:
                return IMAGE;
        }


        return super.getItemViewType(position);
    }
}
