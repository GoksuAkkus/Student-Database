package com.example.kontess.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Kontess on 7.5.2018.
 */

public class StudentDatabase extends SQLiteOpenHelper {

    private static String DATABASE_NAME="Student_Database";
    private static Integer DATABASE_VERSION = 1;

    //name surname , age , class , gpa , gender

    private static String TABLE_NAME = "STUDENTS";
    private static String TABLE_COLUMN_ID = "ID";
    private static String TABLE_COLUMN_NAME = "NAME";
    private static String TABLE_COLUMN_SURNAME = "SURNAME";
    private static String TABLE_COLUMN_AGE = "AGE";
    private static String TABLE_COLUMN_CLASS = "CLASS";
    private static String TABLE_COLUMN_GPA = "GPA" ;
    private static String TABLE_COLUMN_GENDER = "GENDER";


    public StudentDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        //TABLO OLUŞTURCAZ
        String tablo_command= "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "+
                                TABLE_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                TABLE_COLUMN_NAME+ " TEXT, "+
                                TABLE_COLUMN_SURNAME+" TEXT, "+
                                TABLE_COLUMN_AGE+" INTEGER, "+
                                TABLE_COLUMN_CLASS+" INTEGER, "+
                                TABLE_COLUMN_GPA+" DOUBLE, "+
                                TABLE_COLUMN_GENDER+" TEXT "+" ); ";

        db.execSQL(tablo_command);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddStudent(Student student){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NAME",student.getName());
        values.put("SURNAME",student.getSurname());
        values.put("AGE",student.getAge());
        values.put("CLASS",student.getClas());
        values.put("GPA",student.getGpa());
        values.put("GENDER",student.getGender());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public void ClearStudent(Student student){

        SQLiteDatabase db = this.getWritableDatabase();

        String[] whereArgs = new String[]{student.getId().toString()};

        db.delete(TABLE_NAME,TABLE_COLUMN_ID+" =? ",whereArgs);

        db.close();

    }

    public void UpdateStudent (Student student){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NAME",student.getName());
        values.put("SURNAME",student.getSurname());
        values.put("AGE",student.getAge());
        values.put("CLASS",student.getClas());
        values.put("GPA",student.getGpa());
        values.put("GENDER",student.getGender());

        String[] whereArgs = new String[] {student.getId().toString()};

        db.update(TABLE_NAME,values,TABLE_COLUMN_ID+" =? " ,whereArgs);
        db.close();

    }

    public ArrayList<Student> GetAllStudents ()
    {
        String select_all_command = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(select_all_command,null);

        ArrayList<Student> studentList = new ArrayList<>();

        while(cursor.moveToNext()){

            Integer id  = cursor.getInt( cursor.getColumnIndex(TABLE_COLUMN_ID) );
            String name = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_SURNAME));
            Integer age = cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_AGE));
            Double gpa = cursor.getDouble(cursor.getColumnIndex(TABLE_COLUMN_GPA));
            String gender = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_GENDER));
            String clas = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_CLASS));

            Student student = new Student(name,surname,age,gpa,gender,clas,id);

            studentList.add(student);
        }

        cursor.close();
        db.close();

        return studentList;

    }
    public Student GetStudent(Long id)
    {
        String select_command = "SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_COLUMN_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Student student = null;

        if(cursor.moveToFirst()){
            Long employeID  = cursor.getLong( cursor.getColumnIndex(TABLE_COLUMN_ID) );
            String name = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_SURNAME));
            Integer age = cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_AGE));
            Double gpa = cursor.getDouble(cursor.getColumnIndex(TABLE_COLUMN_GPA));
            String gender = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_GENDER));
            String clas = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_CLASS));

            student = new Student(name,surname,age,gpa,clas,gender);
        }

        cursor.close();
        db.close();
        return student;
    }

    public void ClearAllStudents()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);
        db.close();

    }

}
