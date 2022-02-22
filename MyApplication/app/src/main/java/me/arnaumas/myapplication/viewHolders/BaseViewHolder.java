package me.arnaumas.myapplication.viewHolders;


import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import me.arnaumas.myapplication.Content;

/**
 * Created by Michael Stoddart
 * 08/04/2018.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setPayload(Content content);
}
