package me.arnaumas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Video> elements;
    private RequestQueue queue = CarregarDades.queue;


    public MyRecyclerViewAdapter(List<Video> elements) {
        this.elements = elements;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View viewElement = inflater.inflate(R.layout.view_holder, parent, false);

        return new ViewHolder(viewElement);
    }


    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.getTxtElement().setText(elements.get(position).getTitol().toString());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtElement;

        public ViewHolder(View itemView) {
            super(itemView);
            //Quan fem click a la llista mostrem l'element
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder.this.mostraElement(v);
                }
            });

            txtElement = itemView.findViewById(R.id.textElement);
        }

        private void mostraElement(View v) {

            // Cridem la pantalla de mostrar personatge i li passem les dades
            Intent mostrarTorneig = new Intent(v.getContext(), MostraTorneig.class);
            Video personatge = elements.get(getAdapterPosition());
            mostrarTorneig.putExtra("key", personatge.getId());

            v.getContext().startActivity(mostrarTorneig);

        }

        public TextView getTxtElement() {
            return txtElement;
        }
    }

}
