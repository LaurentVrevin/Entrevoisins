package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {
    /* je crée une clé afin de passer les informations de la vu dedans pour la récupérer dans l'activité détail */
    private final String DETAIL_NEIGHBOUR = "DETAIL_NEIGHBOUR";


    private final List<Neighbour> mNeighbours;
    private final boolean isFavorite;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, boolean isFavorite) {
        mNeighbours = items;
        this.isFavorite = isFavorite;
    }



    /**création d'un ViewHolder.
     * Le ViewHolder contient les éléments qui composent un élément de la liste
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    /** onBindViewHolder permet de lier les éléments de la ViewHolder
     * à leurs valeurs dans la base de données
     * Le voisin est récupéré grâce à la position de la ViewHolder dans l'adapter
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        //S'il est favorite alors enlève la visibilité du bouton supprimer
        if (isFavorite)
            holder.mDeleteButton.setVisibility(View.GONE);
        else {
            holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                }
            });

        }

        /** Je génère l'ouverture de l'activité Détail via le on click sur la view
         *
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailNeighbourActivity.class);
                intent.putExtra(DETAIL_NEIGHBOUR, neighbour);
                context.startActivity(intent);
                /**la méthode getContext utilise le propre context de l'activité
                 * et non pas de l'application comme pour getcontextapplication()
                 */
                }
        });
    }
    /* getItemCount() renvoi le nbre d'éléments total à afficher */
    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
