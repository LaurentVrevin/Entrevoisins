package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class DetailNeighbourActivity extends AppCompatActivity {

    private Neighbour neighbour;
    private NeighbourApiService mApiService;
    private FloatingActionButton favoritButtonDetail;
    boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();

        neighbour = (Neighbour) getIntent().getSerializableExtra("DETAIL_NEIGHBOUR");
        isFavorite = mApiService.IsFavoriteNeighbour(neighbour);

        setContentView(R.layout.activity_detail_neighbour);
        TextView profilNameDetail = findViewById(R.id.profilNameDetail);
        TextView firstNameDetail = findViewById(R.id.firstNameDetail);
        TextView addressNumberDetail = findViewById(R.id.addressNumberDetail);
        TextView urlDetail = findViewById(R.id.urlDetail);
        TextView phoneNumberDetail = findViewById(R.id.phoneNumberDetail);
        TextView aProposDetail = findViewById(R.id.aProposDetail);
        favoritButtonDetail = findViewById(R.id.floatingActionButtonDetail);
        ImageButton backButton = findViewById(R.id.BackButton);
        if (isFavorite) {
            favoritButtonDetail.setImageResource(R.drawable.ic_star_white_24dp);
        }

        /*chargement du profil concerné via la liste*/
        profilNameDetail.setText(neighbour.getName());
        firstNameDetail.setText(neighbour.getName());
        addressNumberDetail.setText(neighbour.getAddress());
        phoneNumberDetail.setText(neighbour.getPhoneNumber());
        urlDetail.setText("www.facebook.fr/" + neighbour.getName());
        aProposDetail.setText(neighbour.getAboutMe());

        ImageView photoDetail = findViewById(R.id.imageDetail);
        String detailAvatar = neighbour.getAvatarUrl();
        Glide.with(this)
                .load(detailAvatar)
                .into(photoDetail);

        /*Click sur le bouton pour retour en arrière */
        backButton.setOnClickListener(v -> DetailNeighbourActivity.this.finish());
        /*Changement d'icône en cliquant sur le bouton favori */
        favoritButtonDetail.setOnClickListener(v -> {

            if (!isFavorite) {
                mApiService.addFavoriteNeighbour(neighbour);
                favoritButtonDetail.setImageResource(R.drawable.ic_star_white_24dp);
                Toast.makeText(DetailNeighbourActivity.this, "Vous avez ajouté " + neighbour.getName() + " à vos favoris", Toast.LENGTH_SHORT).show();

            } else {
                mApiService.deleteFavoriteNeighbour(neighbour);
                favoritButtonDetail.setImageResource(R.drawable.ic_star_border_white_24dp);
                Toast.makeText(DetailNeighbourActivity.this, "Vous avez supprimé " + neighbour.getName() + " de vos favoris", Toast.LENGTH_SHORT).show();

            }
            isFavorite = !isFavorite;
        });
    }
}