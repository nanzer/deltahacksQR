package deltahacks.sheetaccess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter < MyDataModel > {

    List < MyDataModel > modelList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyArrayAdapter(Context context, List < MyDataModel > objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.textViewId.setText(item.getId());
        vh.textViewName.setText(item.getName());
        vh.textViewSchool.setText(item.getSchool());
        vh.textViewEmail.setText(item.getEmail());
        vh.textViewDiet.setText(item.getDiet());
        vh.textViewSlack.setText(item.getSlack());
        vh.textViewPhoto.setText(item.getPhoto());
        vh.textViewEOHSS.setText(item.getEOHSS());


        return vh.rootView;
    }



    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView textViewId;
        public final TextView textViewName;
        public final TextView textViewSchool;
        public final TextView textViewEmail;
        public final TextView textViewDiet;
        public final TextView textViewSlack;
        public final TextView textViewPhoto;
        public final TextView textViewEOHSS;


        private ViewHolder(RelativeLayout rootView, TextView textViewName, TextView textViewId,
                           TextView textViewSchool, TextView textViewEmail, TextView textViewDiet, TextView textViewSlack,
                           TextView textViewPhoto, TextView textViewEOHSS) {
            this.rootView = rootView;
            this.textViewId = textViewId;
            this.textViewName = textViewName;
            this.textViewSchool = textViewSchool;
            this.textViewEmail = textViewEmail;
            this.textViewDiet = textViewDiet;
            this.textViewSlack = textViewSlack;
            this.textViewPhoto = textViewPhoto;
            this.textViewEOHSS = textViewEOHSS;

        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewId = (TextView) rootView.findViewById(R.id.textViewId);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewSchool = (TextView) rootView.findViewById(R.id.textViewSchool);
            TextView textViewEmail = (TextView) rootView.findViewById(R.id.textViewEmail);
            TextView textViewDiet = (TextView) rootView.findViewById(R.id.textViewDiet);
            TextView textViewSlack = (TextView) rootView.findViewById(R.id.textViewSlack);
            TextView textViewPhoto = (TextView) rootView.findViewById(R.id.textViewPhoto);
            TextView textViewEOHSS = (TextView) rootView.findViewById(R.id.textViewEOHSS);

            return new ViewHolder(rootView, textViewName, textViewId,
                    textViewSchool, textViewEmail, textViewDiet, textViewSlack, textViewPhoto, textViewEOHSS);
        }
    }
}