package com.iflytek.clientdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iflytek.voicedemo.IRemoteService;

public class MainActivity extends AppCompatActivity {
   private IRemoteService iRemoteService;
    boolean isConnect;
    Button button;
    String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动服务
        Intent intent=  new Intent("com.iflytek.servicedemo.aidl");
        intent.setPackage("com.iflytek.voicedemo");
        startService(intent);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"-----开始绑定------");
                if(!isConnect){
                    Intent intent=new Intent("com.iflytek.servicedemo.aidl");
                    intent.setPackage("com.iflytek.voicedemo");
                    bindService(intent,connection, Context.BIND_AUTO_CREATE);
                }else{
                    try {
                        int pid=  iRemoteService.getPid();
                        iRemoteService.basicTypes(1,5L, true, 12.2f, 12.3,"aaa");
                        Log.i(TAG,"-----onClick----pid--"+pid);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                iRemoteService=IRemoteService.Stub.asInterface(iBinder);
               if(iRemoteService!=null){
                   int pid=  iRemoteService.getPid();
                   int currentPid = Process.myPid();
                   iRemoteService.basicTypes(1,5L, true, 12.2f, 12.3,"aaa");
                   Log.i(TAG,"-----onServiceConnected----pid--"+pid+"===currentPid="+currentPid);
                   isConnect=true;
               }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG,"-----onServiceDisconnected------");
            isConnect=false;
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        Log.i(TAG,"---------onDestroy-----------");
    }

}
