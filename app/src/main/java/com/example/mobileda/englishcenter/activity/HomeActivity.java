package com.example.mobileda.englishcenter.activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.fragment.FeeFragment;
import com.example.mobileda.englishcenter.fragment.MessFragment;
import com.example.mobileda.englishcenter.fragment.ProfileFragment;
import com.example.mobileda.englishcenter.fragment.ScheduleFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "LOG_HOME";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Log.d(TAG, mAuth.getUid()+"");
        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.mipmap.studenticon);
        getSupportActionBar().setTitle("The English Center");


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fragment = new Fragment();
                int i = menuItem.getItemId();
                switch (i) {
                    case R.id.nav_action:
                        break;
                    case R.id.nav_fee:
                        fragment = new FeeFragment();
                        break;
                    case R.id.nav_home:

                        break;
                    case R.id.nav_message: {
                        fragment= new MessFragment();
                        break;
                    }
                    case R.id.nav_schedule:
                        fragment = new ScheduleFragment();
                        break;
                    case  R.id.nav_mark:
                    {
                        Intent intent = new Intent(HomeActivity.this,MarkActivity.class);
                        HomeActivity.this.startActivity(intent);
                    }
                    break;
                    case R.id.nav_subjects:
                    {
                        Intent intent = new Intent(HomeActivity.this,CourseRegistrationActivity.class);
                        HomeActivity.this.startActivity(intent);
                        return false;
                    }

            }
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContent, fragment).commit();
                drawerLayout.closeDrawers();
                return false;
            }
        });
        View headerview = navigation.getHeaderView(0);
        LinearLayout lnHeader = (LinearLayout) headerview.findViewById(R.id.profile);

        final TextView txtName = headerview.findViewById(R.id.txtNameStudent);
        final TextView txtLitercy = headerview.findViewById(R.id.txtLitercy);
        FirebaseFirestore
                .getInstance()
                .document("students/"+mAuth.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                try {
                    txtLitercy.setText(documentSnapshot.getString("literacy"));
                    txtName.setText(documentSnapshot.getString("name"));
                }
                catch (Exception a)
                {
                    txtLitercy.setText("Chưa có thông tin");
                    txtName.setText("Chưa có thông tin");

                }
            }
        });

        lnHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Fragment fragment = new ProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContent, fragment).commit();
                drawerLayout.closeDrawers();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertRegistor =  new AlertDialog.Builder(this);
        alertRegistor.setTitle("Đăng xuất");
        alertRegistor.setIcon(R.mipmap.exit);
        alertRegistor.setMessage("Bạn có muốn đăng xuất không?");

        alertRegistor.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alertRegistor.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertRegistor.show();

        //super.onBackPressed();

    }
}
