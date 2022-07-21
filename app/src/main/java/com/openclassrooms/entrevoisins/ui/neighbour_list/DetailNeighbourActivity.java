package com.openclassrooms.entrevoisins.ui.neighbour_list;





import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.Serializable;

import butterknife.OnClick;


public class DetailNeighbourActivity extends AppCompatActivity {
//    String profilNameDetail, detailAvatar, firstNameDetail,addressNumberDetail,phoneNumberDetail,urlDetail,aProposDetail;
    private Neighbour neighbour;
    private NeighbourApiService mApiService;
    private FloatingActionButton favoriButtonDetail;
    private ImageButton backButton;
    boolean isFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService= DI.getNeighbourApiService();

        neighbour = (Neighbour) getIntent().getSerializableExtra("DETAIL_NEIGHBOUR");
        isFavorite = mApiService.IsFavoriteNeighbour(neighbour);

        setContentView(R.layout.activity_detail_neighbour);
        TextView profilNameDetail = findViewById(R.id.profilNameDetail);
        TextView firstNameDetail = findViewById(R.id.firstNameDetail);
        TextView addressNumberDetail = findViewById(R.id.addressNumberDetail);
        TextView urlDetail = findViewById(R.id.urlDetail);
        TextView phoneNumberDetail = findViewById(R.id.phoneNumberDetail);
        TextView aProposDetail = findViewById(R.id.aProposDetail);
        favoriButtonDetail = findViewById(R.id.favoriButtonDetail);
        backButton = findViewById(R.id.BackButton);

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
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailNeighbourActivity.this.finish();
            }
        });
/*Changement d'icône en cliquant sur le bouton favori */
        favoriButtonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (!isFavorite){
                   mApiService.addFavoriteNeighbour(neighbour);
                    favoriButtonDetail.setImageResource(R.drawable.ic_star_white_24dp);
                    Toast.makeText(DetailNeighbourActivity.this, "Vous avez ajouté "+ neighbour.getName() + " à vos favoris", Toast.LENGTH_SHORT).show();

                }
                else {
                    mApiService.deleteFavoriteNeighbour(neighbour);
                    favoriButtonDetail.setImageResource(R.drawable.ic_star_border_white_24dp);
                   Toast.makeText(DetailNeighbourActivity.this, "Vous avez supprimé " + neighbour.getName() + " de vos favoris", Toast.LENGTH_SHORT).show();

                }
                isFavorite = !isFavorite;
            }
        });


    }


}