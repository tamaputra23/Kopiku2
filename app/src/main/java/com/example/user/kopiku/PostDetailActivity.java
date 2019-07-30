package com.example.user.kopiku;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class PostDetailActivity extends AppCompatActivity {
    TextView mTitleTv, mDetailTv, mOpenTv, mAlamatTv, mPhoneTv, mPriceTv;
    ImageView mImageIv;
    Bitmap bitmap;
    Button mSaveBtn, mShareBtn, mWallBtn;
    String goolgeMap = "com.google.android.apps.maps"; // identitas package aplikasi google masps android
    Uri gmmIntentUri;
    Intent mapIntent;
    String toko_kopi_seduh = "-6.3548118,106.8405373"; // koordinat Masjid Agung Demak
    /*Deklarasi variable*/
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);//Action bar
        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Post Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //initialize views
        mTitleTv = findViewById(R.id.titleTv);
        mDetailTv = findViewById(R.id.descriptionTv);
        mImageIv = findViewById(R.id.imageView);
        mSaveBtn = findViewById(R.id.saveBtn);
        mShareBtn = findViewById(R.id.shareBtn);
        mAlamatTv = findViewById(R.id.alamatTv);
        mOpenTv = findViewById(R.id.openTv);
        mPhoneTv = findViewById(R.id.phoneTv);
        mPriceTv = findViewById(R.id.priceTv);
        //get data from intent
        byte[] bytes = getIntent().getByteArrayExtra("image");
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
        mImageIv.setImageBitmap(bmp);
        //get image from imageview as bitmap
        bitmap = ((BitmapDrawable)mImageIv.getDrawable()).getBitmap();
        //save btn click handle
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });
        //share btn click handle
        mShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });
    }
    private void shareImage() {
        try {
            //intent to share image and text
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            intent.putExtra(Intent.EXTRA_SUBJECT, "KOPIKU"); // put the text
            intent.putExtra(Intent.EXTRA_TEXT, "https://maps.app.goo.gl/jHwaYNMQRs8UayFc6");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Share via"));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void saveImage() {
        gmmIntentUri = Uri.parse("google.navigation:q=" + toko_kopi_seduh);
        // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
        mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
        mapIntent.setPackage(goolgeMap);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(PostDetailActivity.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                    Toast.LENGTH_LONG).show();
        }
    }
    //handle onBackPressed(go to previous activity)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
