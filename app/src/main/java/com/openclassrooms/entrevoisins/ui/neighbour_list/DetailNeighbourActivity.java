package com.openclassrooms.entrevoisins.ui.neighbour_list;





import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.io.Serializable;


public class DetailNeighbourActivity extends AppCompatActivity {
    String profilNameDetail, detailAvatar, firstNameDetail,addressNumberDetail,phoneNumberDetail,urlDetail,aProposDetail;

    private Neighbour neighbour;

   private FloatingActionButton floatingActionButtonDetail;
   private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        neighbour = (Neighbour) getIntent().getSerializableExtra("DETAIL_NEIGHBOUR");


        setContentView(R.layout.activity_detail_neighbour);

        TextView profilNameDetail = findViewById(R.id.profilNameDetail);
        TextView firstNameDetail = findViewById(R.id.firstNameDetail);
        TextView addressNumberDetail = findViewById(R.id.addressNumberDetail);
        TextView urlDetail = findViewById(R.id.urlDetail);
        TextView phoneNumberDetail = findViewById(R.id.phoneNumberDetail);
        TextView aProposDetail = findViewById(R.id.aProposDetail);
        floatingActionButtonDetail = findViewById(R.id.floatingActionButtonDetail);
        backButton = findViewById(R.id.BackButton);

/*chargement du profil via la liste*/
        profilNameDetail.setText(neighbour.getName());
        firstNameDetail.setText(neighbour.getName());
        addressNumberDetail.setText(neighbour.getAddress());
        phoneNumberDetail.setText(neighbour.getPhoneNumber());
        urlDetail.setText("www.facebook.fr/" + neighbour.getName());
        aProposDetail.setText(neighbour.getAboutMe());

        ImageView photoDetail = findViewById(R.id.imageDetail);
        String detailAvatar = neighbour.getAvatarUrl().replace("/300","/600");
        Glide.with(this)
                .load(detailAvatar)
                .into(photoDetail);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailNeighbourActivity.this.finish();
            }
        });

        floatingActionButtonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}