package deltahacks.sheetaccess;

//MainActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button read, readAll, insert, delete, update;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        read = (Button) findViewById(R.id.read_btn);
        readAll = (Button) findViewById(R.id.read_all_btn);
        insert = (Button) findViewById(R.id.insert_btn);
        update = (Button) findViewById(R.id.update_btn);
        delete = (Button) findViewById(R.id.delete_btn);




        readAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReadAllData.class);
                startActivity(intent);
            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), InsertData.class);
                 startActivity(intent);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateData.class);
                startActivity(intent);
            }
        });


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReadSingleData.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteData.class);
                startActivity(intent);
            }
        });

    }

}