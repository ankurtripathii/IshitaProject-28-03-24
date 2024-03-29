package com.anksys.ishitaproject_28_03_24.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.anksys.ishitaproject_28_03_24.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


//    All Subjects List
    String[] subjects = {"Select Subject","HISTORY", "GEOGRAPHY", "POLITICAL SCIENCE", "SOCIOLOGY" ,"PHYSIOLOGY", "HINDI", "PHYSICS",
            "CHEMISTRY", "BIOLOGY", "MATHS", "ENGLISH", "ECONOMICS", "COMPUTER SCIENCE", "PHYSICAL EDUCATION", "ACCOUNTS", "BUSINESS STUDIES"};

    String sub1,sub2,sub3,sub4;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Assign variable
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCourse1.setAdapter(adapter);
        binding.spinnerCourse2.setAdapter(adapter);
        binding.spinnerCourse3.setAdapter(adapter);
        binding.spinnerCourse4.setAdapter(adapter);

            binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sub1 = binding.spinnerCourse1.getSelectedItem().toString();
                    sub2 = binding.spinnerCourse2.getSelectedItem().toString();
                    sub3 = binding.spinnerCourse3.getSelectedItem().toString();
                    sub4 = binding.spinnerCourse4.getSelectedItem().toString();

                     Log.d("SpinnerSame--- ",  Boolean.toString(areStringsEqual(sub1,sub2,sub3,sub4)) );
                     String valueSame = Boolean.toString(areStringsEqual(sub1,sub2,sub3,sub4));

                    if (sub1.equalsIgnoreCase("select subject") | sub2.equalsIgnoreCase("select subject") |
                            sub3.equalsIgnoreCase("select subject") | sub4.equalsIgnoreCase("select subject") | valueSame.equalsIgnoreCase("true")){
                        Toast.makeText(MainActivity.this, "Please select different subject in each field.", Toast.LENGTH_SHORT).show();
                    }
                    else{
//                        will go to career path screen after 1.5 sec
                        ShowProgress();
                    }
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.spinnerCourse1.setSelection(0);
        binding.spinnerCourse2.setSelection(0);
        binding.spinnerCourse3.setSelection(0);
        binding.spinnerCourse4.setSelection(0);
    }
    public static boolean areStringsEqual(String str1, String str2, String str3, String str4) {
        return str1.equals(str2) && str1.equals(str3) && str1.equals(str4);
    }
    public void ShowProgress() {

        pDialog = ProgressDialog.show(MainActivity.this,"Please wait", "While we are recommending best career path for you.",true);
        new Thread() {
            public void run() {
                try{
                    sleep(1500);  //1.5 Second
                    Intent myIntent = new Intent(MainActivity.this, CareerPathActivity.class);
                    myIntent.putExtra("sub1", sub1);
                    myIntent.putExtra("sub2", sub2);
                    myIntent.putExtra("sub3", sub3);
                    myIntent.putExtra("sub4", sub4);
                    MainActivity.this.startActivity(myIntent);
                } catch (Exception e) {  }
                pDialog.dismiss();
            }
        }.start();
    }
}
