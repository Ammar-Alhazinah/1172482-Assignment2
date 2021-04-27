package edu.cs.birzeit.a1172482.a1172482_assignment2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import edu.cs.birzeit.a1172482.a1172482_assignment2.R;
import edu.cs.birzeit.a1172482.a1172482_assignment2.models.AcademicDetails;
import edu.cs.birzeit.a1172482.a1172482_assignment2.models.PersonalInfo;

public class academicActivity extends AppCompatActivity {

    //Initialize variables
    private EditText course;
    private EditText school;
    private CheckBox percentage;
    private CheckBox cgpa;
    private String percent;
    private EditText mark;
    private RadioButton graduate;
    private RadioButton pursuing;
    private String graduated;
    private EditText yearOfPassing;
    private TextView perIcon;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);
        setupSharedPrefs();
        setupView();
    }


    private void setupSharedPrefs() {
        //Assign variable
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        gson = new Gson();
    }

    private void setupView() {
        //Assign variable
        course = findViewById(R.id.edtCourse);
        school = findViewById(R.id.edtSchool);
        percentage = findViewById(R.id.chkPercentage);
        cgpa = findViewById(R.id.chkCGPA);
        mark = findViewById(R.id.edtMark);
        graduate = findViewById(R.id.edtGraduated);
        pursuing = findViewById(R.id.edtPursing);
        yearOfPassing = findViewById(R.id.edtDate);
        perIcon = findViewById(R.id.persentageIcon);

        String str = prefs.getString("detailsData", "");
        AcademicDetails academicDetail = gson.fromJson(str, AcademicDetails.class);
        if(academicDetail != null){
            course.setText(academicDetail.getCourse());
            school.setText(academicDetail.getSchool());
            mark.setText(academicDetail.getMark());
            yearOfPassing.setText(academicDetail.getYearOfPassing());
            if(academicDetail.getPercentage() != null) {
                if(academicDetail.getPercentage().equals("percentage")) {
                    percentage.setChecked(true);
                    perIcon.setText("%");
                }else if(academicDetail.getPercentage().equals("cgpa")){
                    cgpa.setChecked(true);
                    perIcon.setText("");
                }
            }
            if(academicDetail.getGraduated() != null) {
                if (academicDetail.getGraduated().equals("graduate")) {
                    graduate.setChecked(true);
                } else if (academicDetail.getGraduated().equals("pursing")) {
                    pursuing.setChecked(true);
                }
            }
        }

    }



    public void saveData(){
        if(percentage.isChecked()){
            percent = "percentage";
        }else if(cgpa.isChecked()){
            percent = "cgpa";
        }

        if(graduate.isChecked()){
            graduated = "graduate";
        }else if(pursuing.isChecked()){
            graduated = "pursing";
        }
        AcademicDetails details = new AcademicDetails(course.getText().toString(), school.getText().toString(), percent, mark.getText().toString(), graduated, yearOfPassing.getText().toString());
        String infoString = gson.toJson(details);

        editor.putString("detailsData", infoString);
        editor.commit();
    }
    public void chkCGPAOnClick(View view) {
        if(cgpa.isChecked()){
            percentage.setChecked(false);
            perIcon.setText("");
        }
        else{
            percentage.setChecked(true);
            perIcon.setText("%");
        }
    }
    public void chkPercentageOnClick(View view) {
        if(percentage.isChecked()){
            cgpa.setChecked(false);
            perIcon.setText("%");
        }
        else{
            cgpa.setChecked(true);
            perIcon.setText("");
        }
    }
    public void btnSaveOnClick(View view) {
        saveData();
        Toast.makeText(this, "Data Saved !!",Toast.LENGTH_SHORT).show();
    }
    public void btnBackOnClick(View view) {
        saveData();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        saveData();
        super.onDestroy();
    }
}