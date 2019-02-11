package com.example.kontess.studentdatabase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    StudentDatabase studentDatabase;
    ArrayList<Student> studentArrayList = new ArrayList<>();

    EditText et_name,et_surname,et_age;
    Spinner spgender,spclas;
    TextView lblgpa;
    SeekBar seekgpa;


    Integer secilikisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        et_name = (EditText)findViewById(R.id.txt_name);
        et_surname = (EditText)findViewById(R.id.txt_surname);
        et_age = (EditText)findViewById(R.id.txt_age);
        spgender = (Spinner)findViewById(R.id.sp_gender);
        spclas = (Spinner)findViewById(R.id.sp_class);
        seekgpa = (SeekBar) findViewById(R.id.seekBar);
        lblgpa = (TextView)findViewById(R.id.lbl_gpa);


        this.studentDatabase = new StudentDatabase(getApplicationContext());

        studentArrayList = studentDatabase.GetAllStudents();

        SharedPreferences mypref=getSharedPreferences("SeciliKisiPosition",MODE_PRIVATE);

        secilikisi=mypref.getInt("position",0);

        this.getStudentHere();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getStudentHere()
    {
            Student student = studentArrayList.get(secilikisi);


            et_name.setText(student.getName());
            et_surname.setText(student.getSurname());
            et_age.setText(student.getAge().toString());
            lblgpa.setText(student.getGpa().toString());

            if(student.getGender().equals("Male"))
            {

                spgender.setSelection(0);
            }
            else
            {
                spgender.setSelection(1);
            }

            if(student.getClas().equals("1"))
            {

                spclas.setSelection(0);
            }
            else if (student.getClas().equals("2"))
            {
                spclas.setSelection(1);
            }
            else if (student.getClas().equals("3"))
            {
                spclas.setSelection(2);
            }
            else if (student.getClas().equals("4"))
            {
                spclas.setSelection(3);
            }

        et_name.setEnabled(false);
        et_surname.setEnabled(false);
        et_age.setEnabled(false);
        spgender.setEnabled(false);
        spclas.setEnabled(false);
        seekgpa.setEnabled(false);
        }
    }
