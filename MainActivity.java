package com.example.kontess.studentdatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton ekle,sil,hepsinisil,info,guncelle;
    ListView listv;
    ArrayList<Student> StudentArrayList = new ArrayList<>();
    MyAdapter adapter;
    StudentDatabase StDatabase;

    Integer seciliKisi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ekle=(ImageButton)findViewById(R.id.imBtn_Ekle);
        sil=(ImageButton)findViewById(R.id.imBtn_Sil);
        guncelle=(ImageButton)findViewById(R.id.imBtn_Guncelle);
        info=(ImageButton)findViewById(R.id.imBtn_info);
        hepsinisil=(ImageButton)findViewById(R.id.imBtn_HepsiniSil);
        listv=(ListView)findViewById(R.id.listview);


        this.StDatabase = new StudentDatabase(getApplicationContext());
        this.ListviewUpdate();

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                seciliKisi = position;


            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor=getSharedPreferences("SeciliKisiPosition",MODE_PRIVATE).edit();
        editor.putInt("position",seciliKisi);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.ListviewUpdate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        this.ListviewUpdate();


    }

    public void ListviewUpdate(){

        StudentArrayList=StDatabase.GetAllStudents();
        adapter = new MyAdapter(this,StudentArrayList);
        listv.setAdapter(adapter);
    }

    public void Ekle(View view) {
        //BOŞ OLAN EKLEME SAYFASI AÇILCAK

        Intent eklesayfasi=new Intent(MainActivity.this,EkleActivity.class);
        startActivity(eklesayfasi);

        //Database e ekleyip listviewu güncellicez

    }

    public void Guncelle(View view) {

        Intent guncellesayfasi=new Intent(MainActivity.this,GuncelleActivity.class);
        startActivity(guncellesayfasi);

        //TIKLANAN KİŞİNİN BİLGİLERİYLE DOLU OLAN EKLEME TABLOSU AÇILCAK

    }

    public void Info(View view) {

        Intent infosayfasi=new Intent(MainActivity.this,InfoActivity.class);
        startActivity(infosayfasi);

        //TIKLANAN KİŞİ İÇİN İNFO SAYFASI AÇILCAK

    }

    public void HepsiniSil(View view) {

        StDatabase.ClearAllStudents();

        StudentArrayList = StDatabase.GetAllStudents(); //arrayliste databaseden bütün öğrencileri çek
        adapter = new MyAdapter(this,StudentArrayList); //Yeni arraylistesini adaptöre at
        listv.setAdapter(adapter); //adaptör görünümlü array listesini listviewa at.


    }

    public void Sil(View view) {

        Student student = StudentArrayList.get(seciliKisi); //Silinecek studentımız student arraylistesindeki secilikisi olarak ayarlandı

        StDatabase.ClearStudent(student);
        //student listesi değişiklikten sonra güncellenmeli!
        //veritabanından bu listeye güncel olan tüm listeyi çekeriz
        StudentArrayList = StDatabase.GetAllStudents();
        //aray listesine çektiğimiz listeyi istediğimiz formata (custom adaptorümüze) çevirmeliyiz

        adapter = new MyAdapter(this,StudentArrayList);

        listv.setAdapter(adapter);

    }
}
