package deltahacks.sheetaccess;

import android.app.Activity;
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

import java.util.Map;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADJ on 5/14/2017.
 */
public class ReadSingleData extends AppCompatActivity {

    private Button read;
    private Map<String,String> map = new HashMap <String, String>();
    private String [] headings = {"ID", "Full Name", "School", "Email", "Diet", "Slack?", "Photo Consent?", "EHOSS Waiver"};

    private EditText uid1ET;
    private TextView [] labels = new TextView[8];
    private TextView [] attendeeInfo = new TextView[8];


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_data);
        read = (Button) findViewById(R.id.insert_btn);
        uid1ET = (EditText) findViewById(R.id.uid);

        labels[0] = (TextView) findViewById(R.id.id_l);
        labels[1] = (TextView) findViewById(R.id.name_l);
        labels[2] = (TextView) findViewById(R.id.school_l);
        labels[3] = (TextView) findViewById(R.id.email_l);
        labels[4] = (TextView) findViewById(R.id.diet_l);
        labels[5] = (TextView) findViewById(R.id.slack_l);
        labels[6] = (TextView) findViewById(R.id.photo_l);
        labels[7] = (TextView) findViewById(R.id.EOHSS_l);

        attendeeInfo[0] = (TextView) findViewById(R.id.id_v);
        attendeeInfo[1] = (TextView) findViewById(R.id.name_v);
        attendeeInfo[2] = (TextView) findViewById(R.id.school_v);
        attendeeInfo[3] = (TextView) findViewById(R.id.email_v);
        attendeeInfo[4] = (TextView) findViewById(R.id.diet_v);
        attendeeInfo[5] = (TextView) findViewById(R.id.slack_v);
        attendeeInfo[6] = (TextView) findViewById(R.id.photo_v);
        attendeeInfo[7] = (TextView) findViewById(R.id.EOHSS_v);


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.put("ID", uid1ET.getText().toString().trim());

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
            Log.i(Controller.TAG, "IDVALUE" + map.get("ID"));
            JSONObject jsonObject = Controller.readData(map.get("ID"));
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    JSONObject user = jsonObject.getJSONObject("user");
                    map.put(headings[1], user.getString("name"));
                    map.put(headings[2], user.getString("school"));
                    map.put(headings[3], user.getString("email"));
                    map.put(headings[4], user.getString("diet"));
                    map.put(headings[5], user.getString("slack"));
                    map.put(headings[6], user.getString("photo"));
                    map.put(headings[7],user.getString("EOHSS"));

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
            if (map.get(headings[1]) != null) {


                for (int i = 0 ; i<labels.length; i++) {
                    labels[i].setText(headings[i]);
                }

                for (int i = 0; i<attendeeInfo.length; i++) {
                    attendeeInfo[i].setText(map.get(headings[i]));
                }

            } else
                Toast.makeText(getApplicationContext(), "ID not found", Toast.LENGTH_LONG).show();
        }
    }
}