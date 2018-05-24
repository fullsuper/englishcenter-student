package com.example.mobileda.englishcenter.activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.fragment.MessFragment;
import com.example.mobileda.englishcenter.fragment.ResultSubjectFragment;
import com.example.mobileda.englishcenter.fragment.ScheduleFragment;


public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

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
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                    case R.id.nav_fee:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                    case R.id.nav_home:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                    case R.id.nav_message: {
                        {
                            fragment= new MessFragment();
                        }
                        break;
                    }
                    case R.id.nav_schedule:
                    {
                        fragment = new ScheduleFragment();
                    }
                        break;
                    case  R.id.nav_mark:
                    {
                        fragment = new ResultSubjectFragment();
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

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
