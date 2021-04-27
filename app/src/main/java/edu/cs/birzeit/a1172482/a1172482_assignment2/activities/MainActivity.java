package edu.cs.birzeit.a1172482.a1172482_assignment2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;

import edu.cs.birzeit.a1172482.a1172482_assignment2.R;
import edu.cs.birzeit.a1172482.a1172482_assignment2.models.PersonalInfo;

public class MainActivity extends AppCompatActivity {
//Initialize variables
    private EditText name;
    private EditText address;
    private EditText mobile;
    private EditText email;
    private RadioButton male;
    private RadioButton female;
    private String gender;
    private Spinner city;
    private EditText aboutMe;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSharedPrefs();
        setupView();
    }

    private void populateCitiesSpinner() {
        ArrayList<String> citiesData = new ArrayList<>();
        citiesData.add("Jerusalem");
        citiesData.add("Ramallah");
        citiesData.add("Hebron");
        citiesData.add("Bethlehem");
        citiesData.add("Jericho");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiesData);

        city.setAdapter(adapter);
    }

    private void setupSharedPrefs() {
        //Assign variable
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        gson = new Gson();
    }

    private void setupView() {
        //Assign variable
        name = findViewById(R.id.edtName);
        address = findViewById(R.id.edtAddress);
        mobile = findViewById(R.id.edtMobile);
        email = findViewById(R.id.edtEmail);
        male = findViewById(R.id.rBtnMale);
        female = findViewById(R.id.rBtnFemale);
        city = findViewById(R.id.citiesSpinner);
        aboutMe = findViewById(R.id.edtAboutMe);
        populateCitiesSpinner();

        String str = prefs.getString("infoData", "");
        PersonalInfo info = gson.fromJson(str, PersonalInfo.class);

        if(info !=null){
            name.setText(info.getName());
            address.setText(info.getAddress());
            mobile.setText(info.getMobile());
            email.setText(info.getEmail());
            if(info.getGender().equals("male")) {
                male.setChecked(true);
            }
            if(info.getGender().equals("female")) {
                female.setChecked(true);
            }
            for (int i=0;i<city.getCount();i++) {
                if (city.getItemAtPosition(i).equals(info.getCity())) {
                    city.setSelection(i);
                    break;
                }
            }
            aboutMe.setText(info.getAboutMe());
        }

    }


    public void saveData(){
        if(male.isChecked()){
            gender = "male";
        }else if(female.isChecked()){
            gender = "female";
        }
        PersonalInfo info = new PersonalInfo(""+name.getText().toString(),""+address.getText().toString(),""+mobile.getText().toString(),""+email.getText().toString(),""+gender,""+city.getSelectedItem().toString(),""+aboutMe.getText().toString());
        String infoString = gson.toJson(info);

        editor.putString("infoData", infoString);
        editor.commit();
    }

    public void btnNextOnClick(View view) {
        saveData();
        Intent intent = new Intent(this, academicActivity.class);
        startActivity(intent);
    }
}