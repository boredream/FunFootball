package com.qysports.funfootball.net;


import android.content.Context;

import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.qysports.funfootball.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Downloader {

    /**
     * 异步保存动态图文件
     *
     * @param callback
     * @param context
     */
    public static void saveGif(final Context context, final String filename, GifDrawable gifDrawable, Subscriber<File> callback) {
        // 将数据发送到新的子线程中处理耗时工作,完成后在主线程中回调更新UI
        Observable.just(gifDrawable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GifDrawable, File>() {
                    @Override
                    public File call(GifDrawable gifDrawable) {
                        try {
                            byte[] data = gifDrawable.getData();
                            return FileUtils.saveImageFile(context, data, filename);
                        } catch (IOException e) {
                            // 抛出runtime异常,Subscriber中的onError会捕获处理
                            throw new RuntimeException("动态图保存失败 : " + e.getMessage());
                        }
                    }
                })
                .subscribe(callback);
    }
}
