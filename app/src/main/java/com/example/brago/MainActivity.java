package com.example.brago;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.brago.Model.StatusModel;
import com.example.brago.R;

public class MainActivity extends Activity implements CancelCallback{

    final String LOG_TAG = "myLogs";

    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено

    Handler h;

    TextView tvStatus;
    ProgressBar pbConnect;
    Button btnConnect;
    Context cnt;

    MyAssynckTask mTask;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cnt = this;
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        pbConnect = (ProgressBar) findViewById(R.id.pbConnect);
        btnConnect = (Button) findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        mTask = (MyAssynckTask)  getLastNonConfigurationInstance();
        if(mTask == null) {
            mTask = new MyAssynckTask();

            mTask.execute(cnt);
        }
        mTask.link((Activity)cnt);
}

    @Override
    public Object onRetainNonConfigurationInstance() {
        mTask.unlink();
        return mTask;
    }




    @Override
    public void SendCancelCallback() {
        mTask.cancel(false);
    }

    static class MyAssynckTask extends AsyncTask<Context,Integer,Void>
    {
        MainActivity acvitity;
        public CancelCallback inCallback;



        public void link(Activity act)
        {
            acvitity = (MainActivity)act;
        }

        public void unlink()
        {
            acvitity = null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            acvitity.tvStatus.setText(Integer.toString(values[0]));
            if(values[0]>9){
                inCallback.SendCancelCallback();
                acvitity.tvStatus.setText("TASK CANCEL");
            }
        }

        @Override
        protected Void doInBackground(Context... context) {
            try {
                inCallback = (CancelCallback) context[0];
                for(int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                publishProgress(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}