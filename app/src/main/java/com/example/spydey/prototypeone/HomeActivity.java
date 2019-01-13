package com.example.spydey.prototypeone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private NavigationView navview;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private FrameLayout frame;
    private View headerview;
    private TextView fullUsersName, userName;
    private String fullnameString, usernameString;
    private DatabaseReference databaseUserRef;
    private String uid;

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //last 2 parameters are for blind people
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navview = findViewById(R.id.nav_view);
        navview.setNavigationItemSelectedListener(this);

        headerview = navview.getHeaderView(0);
        fullUsersName = headerview.findViewById(R.id.navheader_fullusername);
        userName = headerview.findViewById(R.id.navheader_username);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RecordsFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        uid=FirebaseAuth.getInstance().getUid();
        databaseUserRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);

        databaseUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                fullnameString = user.firstName+" ";
                fullnameString = fullnameString.concat(user.lastName);
                usernameString = user.email;
                fullUsersName.setText(fullnameString);
                userName.setText(usernameString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.recordsMenuButton:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RecordsFragment()).commit();
                //Toast.makeText(this, "Activity under construction", Toast.LENGTH_SHORT).show();
                break;

            case R.id.analysisMenuButton:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnalyseFragment()).commit();
                break;

            case R.id.signoutMenuButton:
                logout();
                break;

            default:
                Toast.makeText(this, "Somthing went wrong", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

}
