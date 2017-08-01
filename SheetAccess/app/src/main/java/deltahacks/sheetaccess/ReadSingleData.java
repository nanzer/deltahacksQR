package deltahacks.sheetaccess;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADJ on 5/14/2017.
 */
public class ReadSingleData extends AppCompatActivity {

    private Button read;
    String id;
    String name;
    String school;
    String email;
    String diet;
    String slack;
    String photo;
    String EOHSS;

    private EditText uid1ET;
    private TextView id_l, name_l, school_l, email_l, diet_l, slack_l, photo_l, EOHSS_l;
    private TextView id_v, name_v, school_v, email_v, diet_v, slack_v, photo_v, EOHSS_v;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_data);
        read = (Button) findViewById(R.id.insert_btn);
        uid1ET = (EditText) findViewById(R.id.uid);

        id_l = (TextView) findViewById(R.id.id_l);
        name_l = (TextView) findViewById(R.id.name_l);
        school_l = (TextView) findViewById(R.id.school_l);
        email_l = (TextView) findViewById(R.id.email_l);
        diet_l = (TextView) findViewById(R.id.diet_l);
        slack_l = (TextView) findViewById(R.id.slack_l);
        photo_l = (TextView) findViewById(R.id.photo_l);
        EOHSS_l= (TextView) findViewById(R.id.EOHSS_l);

        id_v = (TextView) findViewById(R.id.id_v);
        name_v = (TextView) findViewById(R.id.name_v);
        school_v = (TextView) findViewById(R.id.school_v);
        email_v = (TextView) findViewById(R.id.email_v);
        diet_v = (TextView) findViewById(R.id.diet_v);
        slack_v = (TextView) findViewById(R.id.slack_v);
        photo_v = (TextView) findViewById(R.id.photo_v);
        EOHSS_v = (TextView) findViewById(R.id.EOHSS_v);


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = uid1ET.getText().toString();

                new ReadDataActivity().execute();
            }
        });
    }


    class ReadDataActivity extends AsyncTask < Void, Void, Void > {

        ProgressDialog dialog;
        int jIndex;
        int x;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(ReadSingleData.this);
            dialog.setTitle("Please wait.");
            dialog.setMessage("Fetching your values!");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void...params) {
            Log.i(Controller.TAG, "IDVALUE" + id);
            JSONObject jsonObject = Controller.readData(id);
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    JSONObject user = jsonObject.getJSONObject("user");
                    name = user.getString("name");
                    school = user.getString("school");
                    email = user.getString("email");
                    diet = user.getString("diet");
                    slack = user.getString("slack");
                    photo = user.getString("photo");
                    EOHSS = user.getString("EOHSS");

                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            if (name != null) {
                id_l.setText("ID");
                name_l.setText("Full Name");
                school_l.setText("School");
                email_l.setText("Email");
                diet_l.setText("Diet");
                slack_l.setText("Slack?");
                photo_l.setText("Photo Consent?");
                EOHSS_l.setText("EOHSS Waiver");

                id_v.setText(id);
                name_v.setText(name);
                school_v.setText(school);
                email_v.setText(email);
                diet_v.setText(diet);
                slack_v.setText(slack);
                photo_v.setText(photo);
                EOHSS_v.setText(EOHSS);

            } else
                Toast.makeText(getApplicationContext(), "ID not found", Toast.LENGTH_LONG).show();
        }
    }
}