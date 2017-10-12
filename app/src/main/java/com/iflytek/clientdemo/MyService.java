//package com.iflytek.voicedemo;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.os.Process;
//import android.os.RemoteException;
//import android.util.Log;
//
//public class MyService extends Service {
//    String TAG="MainActivity";
//
//    public MyService() {
//
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.i(TAG,"----------onCreate------therad--"+Thread.currentThread().getName());
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        Log.i(TAG,"----------onBind--------");
//         return  iBinder;
////        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    private IRemoteService.Stub iBinder=new IRemoteService.Stub() {
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//            Log.i(TAG,"----------basicTypes------therad--"+Thread.currentThread().getName());
//            Log.i(TAG,"-----------basicTypes--------anInt--"+anInt);
//        }
//
//        @Override
//        public int getPid() throws RemoteException {
//            Log.i(TAG,"----------getPid------thread--"+Thread.currentThread().getName());
//
//            return Process.myPid();
//        }
//    };
//
//
//
//
//
//}
