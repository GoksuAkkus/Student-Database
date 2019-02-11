package com.example.kontess.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EkleActivity extends AppCompatActivity {

    StudentDatabase studentDatabase;

    EditText et_name,et_surname,et_age;
    Spinner spclas,spgender;
    SeekBar seekgpa;
    TextView lblgpa;

    Double seciligpa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);

        et_name=(EditText)findViewById(R.id.txt_name);
        et_surname=(EditText)findViewById(R.id.txt_surname);
        et_age=(EditText)findViewById(R.id.txt_age);
        spclas=(Spinner)findViewById(R.id.sp_class);
        spgender=(Spinner)findViewById(R.id.sp_gender);
        seekgpa=(SeekBar) findViewById(R.id.seekBar);
        lblgpa=(TextView)findViewById(R.id.lbl_gpa);

        this.studentDatabase = new StudentDatabase(getApplicationContext());

        seekgpa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seciligpa = progress/10.0;
                lblgpa.setText(Double.valueOf(seciligpa).toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(EkleActivity.this,"GPA Bos Bırakılamaz!",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void imBtn_Kaydet(View view) {

        String name = et_name.getText().toString();
        String surname = et_surname.getText().toString();
        Integer age = Integer.valueOf(et_age.getText().toString());
        String gender = spgender.getSelectedItem().toString();
        String clas = spclas.getSelectedItem().toString();
        Double gpa = Double.valueOf(lblgpa.getText().toString());

        Student student = new Student (name,surname,age,gpa,gender,clas);

        studentDatabase.AddStudent(student);

    }

    public void imBtn_Cikis(View view) {

        Intent anasayfa=new Intent(EkleActivity.this,MainActivity.class);
        startActivity(anasayfa);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
