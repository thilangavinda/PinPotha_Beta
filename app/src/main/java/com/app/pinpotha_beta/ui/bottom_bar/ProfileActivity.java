package com.app.pinpotha_beta.ui.bottom_bar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.app.pinpotha_beta.R;
import com.app.pinpotha_beta.ui.records.AddRecord;
import com.app.pinpotha_beta.ui.ketayam.NetworkChangeListener;
import com.app.pinpotha_beta.ui.side_bar.Communiuty;
import com.app.pinpotha_beta.util.TimeData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    // Initialize variable

    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    BottomAppBar bottomAppBar;
    FloatingActionButton faButton;
    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    TextView greeting;
    CardView btCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Assign variable
        bottomAppBar=findViewById(R.id.bottomAppBar);
        faButton=findViewById(R.id.fActionbtn);
        greeting=findViewById(R.id.greeting);
        btCommunity=findViewById(R.id.crd_community);

        // Initialize firebase auth
        firebaseAuth=FirebaseAuth.getInstance();

        // Initialize firebase user
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        // Check condition
        if(firebaseUser!=null)
        {
           //load details
        }
        else{
            Toast.makeText(getApplicationContext(), "User Not Avialable", Toast.LENGTH_SHORT).show();
        }

        greeting.setText(TimeData.getGreeting(firebaseUser.getDisplayName(),
                getString(R.string.good_morning),
                getString(R.string.good_afternoon),
                getString(R.string.good_evening),
                getString(R.string.good_night)
        ));

        // Initialize sign in client
        googleSignInClient= GoogleSignIn.getClient(ProfileActivity.this
                , GoogleSignInOptions.DEFAULT_SIGN_IN);

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext()
                        , AddRecord.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                overridePendingTransition(0, 0);
            }
        });

        bottomAppBar.setOnMenuItemClickListener(menuItem->{
            switch (menuItem.getItemId()) {
                case R.id.sidemenu:
                    startActivity(new Intent(getApplicationContext()
                            , SideMenu.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.translatelam:
                    startActivity(new Intent(getApplicationContext()
                            , Translate.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.home:
                    return true;

                case R.id.search:
                    startActivity(new Intent(getApplicationContext()
                            , Search.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });

        btCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext()
                        , Communiuty.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}