package com.caseystark.joatraveltime;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.caseystark.HTML.htmlParser;

import org.jsoup.select.Elements;

import java.util.concurrent.ExecutionException;


public class LoadActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);



        try {
            new RetrieveFieldsTask(this).execute(ApplicationController.getURL()).get();
            Intent intent = new Intent(this, EntryActivity.class);
            startActivity(intent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class RetrieveFieldsTask extends AsyncTask<String, Void, Void> {
        /** progress dialog to show user that the backup is processing. */
        private ProgressDialog dialog;
        /** application context. */
        private ActionBarActivity activity;

        private int progress;

        public RetrieveFieldsTask(ActionBarActivity activity) {
            this.activity = activity;
            dialog = new ProgressDialog(activity);

            progress = 0;
        }

        @Override
        protected void onPreExecute() {
            dialog.setIndeterminate(true);
            dialog.setProgress(progress);
            dialog.setMax(100);
//            dialog.show(activity, "Retrieving Form Title", "Retrieving Form Message");
        }

        protected Void doInBackground(String... urls) {
            try {
                htmlParser parser = htmlParser.getNewInstance(urls[0]);
                Elements formElements = parser.getFormElements();

                int elementsSize = formElements.size();
                float percentIncrease = 100 / elementsSize;

                dialog.setIndeterminate(false);

                for(int i = 0; i < formElements.size(); i++) {
                    parser.parseHTML(formElements.get(i));
                    dialog.setProgress(progress += percentIncrease);
                }

                return null;
                } catch (Exception e) {
                    return null;
                }
            }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(dialog.isShowing())
                dialog.dismiss();

            Log.i("HELLO", "DONE");
        }
    }
}
