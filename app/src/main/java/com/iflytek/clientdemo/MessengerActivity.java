package com.iflytek.clientdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MessengerActivity extends AppCompatActivity {

    Messenger mService;
    static final int MSG_REGISTER_CLIENT = 1;

    static final int MSG_UNREGISTER_CLIENT = 2;

    static final int MSG_SET_VALUE = 3;

    String TAG="MessengerActivity";

    private Handler incomingHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG,msg.what+"-------------handleMessage----------"+msg.arg1);
            switch (msg.what){
                case MSG_SET_VALUE :
                    Log.i(TAG,"Received from service: " + msg.arg1);
                    break;
            }


            super.handleMessage(msg);




        }
    };

    final Messenger messenger=new Messenger(incomingHandler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        //启动服务
//        Intent intent=  new Intent("com.iflytek.servicedemo.messager");
//        intent.setPackage("com.iflytek.clientdemo");
//        startService(intent);

       Button button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=  new Intent("android.service.dreams.DreamService");
                intent.setPackage("com.iflytek.clientdemo");
                startService(intent);
                bindService();
            }
        });

    }


    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService=new Messenger(iBinder);
            Log.i(TAG,"------onServiceConnected------------");

            try {
            Message msg=null;
            msg=Message.obtain(null,MSG_REGISTER_CLIENT);
            msg.replyTo=messenger;
            mService.send(msg);

             msg=Message.obtain(null,MSG_SET_VALUE,10,0);
             mService.send(msg);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG,"------onServiceDisconnected------------");
        }
    };

       private void bindService(){
           Intent intent=new Intent("com.iflytek.servicedemo.messager");
           intent.setPackage("com.iflytek.clientdemo");

           bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
       }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
