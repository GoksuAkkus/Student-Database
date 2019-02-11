package com.example.kontess.studentdatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class GuncelleActivity extends AppCompatActivity {

    StudentDatabase studentDatabase;

    ArrayList<Student> studentArrayList = new ArrayList<>();


    EditText et_name,et_surname,et_age;
    Spinner spclas,spgender;
    SeekBar seekgpa;
    TextView lblgpa;

    Double seciligpa;

    Integer secilikisi=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelle);

        et_name=(EditText)findViewById(R.id.txt_name);
        et_surname=(EditText)findViewById(R.id.txt_surname);
        et_age=(EditText)findViewById(R.id.txt_age);
        spclas=(Spinner)findViewById(R.id.sp_class);
        spgender=(Spinner)findViewById(R.id.sp_gender);
        seekgpa=(SeekBar) findViewById(R.id.seekBar);
        lblgpa = (TextView)findViewById(R.id.lbl_gpa);

        this.studentDatabase = new StudentDatabase(getApplicationContext());

        studentArrayList = studentDatabase.GetAllStudents();

        SharedPreferences mypref=getSharedPreferences("SeciliKisiPosition",MODE_PRIVATE);

        secilikisi=mypref.getInt("position",0);

        this.updateBoxesBySelectedStudent(secilikisi);

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

            }
        });


    }

    public void imBtn_Kaydet(View view) { //GUNCELLE

        String name = et_name.getText().toString();
        String surname = et_surname.getText().toString();
        Integer age = Integer.parseInt(et_age.getText().toString());
        String clas = spclas.getSelectedItem().toString();
        String gender = spgender.getSelectedItem().toString();
        Double gpa = Double.valueOf(lblgpa.getText().toString());

        Student student = studentArrayList.get(secilikisi);
        student.setName(name);
        student.setSurname(surname);
        student.setAge(age);
        student.setClas(clas);
        student.setGender(gender);
        student.setGpa(gpa);

        studentDatabase.UpdateStudent(student);


    }


    public void imBtn_Cikis(View view) {

        Intent anasayfa = new Intent(GuncelleActivity.this,MainActivity.class);
        startActivity(anasayfa);

    }


    public void updateBoxesBySelectedStudent(int position){

            Student student = studentArrayList.get(position);

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


            switch(student.getClas())
            {
                case "1":
                    spclas.setSelection(0);
                case "2":
                    spclas.setSelection(1);
                case "3":
                    spclas.setSelection(2);
                case "4":
                    spclas.setSelection(3);

            }
    }
}
