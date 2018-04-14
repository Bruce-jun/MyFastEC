package com.coderzj.min.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.coderzj.min.app.Min;
import com.coderzj.min.net.callback.IRequest;
import com.coderzj.min.net.callback.ISuccess;
import com.coderzj.min.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by 邹俊 on 2018/1/23.
 * 下载的异步请求
 */

public class DownLoadTask extends AsyncTask<Object,Void,File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    public DownLoadTask( IRequest request, ISuccess success) {
        REQUEST = request;
        SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir= (String) params[0];
        String extension= (String) params[1];
        final ResponseBody body= (ResponseBody) params[2];
        final String name= (String) params[3];
        final InputStream is= body.byteStream();
        if (downloadDir==null||downloadDir.equals("")){
            downloadDir="down_load";
        }
        if (extension==null||extension.equals("")){
            extension="";
        }
        if (name==null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (SUCCESS!=null){
            REQUEST.onRequestEnd();//请求结束
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent intent=new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Min.getApplicationContext().startActivity(intent);
        }

    }
}
