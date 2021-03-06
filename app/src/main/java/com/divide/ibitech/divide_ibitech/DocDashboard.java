package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class DocDashboard extends AppCompatActivity {

    TextView tv_DocName;
    ImageView imgProfilePic;
    ImageButton btnTutorial;
    CardView cv_Appointments, cv_Patients;
    Button btn_Logout;

    private Bitmap bitmap;
    String getId;

    private static final String TAG = DocDashboard.class.getSimpleName(); //getting the info

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_dashboard);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        tv_DocName = findViewById(R.id.tvDocName);
        cv_Appointments = findViewById(R.id.cvAppointments);
        cv_Patients = findViewById(R.id.cvAllPatients);
        btn_Logout = findViewById(R.id.btnLogout);
        btnTutorial = findViewById(R.id.imgTutorial);

        HashMap<String,String> doc = sessionManager.getDocDetails();
        String sName = doc.get(sessionManager.NAME);
        String sSurname = doc.get(sessionManager.SURNAME);

        tv_DocName.setText("Dr " + sName + " " + sSurname);

        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DocDashboard.this, DoctorTutorial.class));
            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.doclogout();
            }
        });

       cv_Appointments.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(DocDashboard.this, ViewAppointments.class));
           }
       });

       cv_Patients.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(DocDashboard.this, ViewPatients.class));
           }
       });


    }
}
