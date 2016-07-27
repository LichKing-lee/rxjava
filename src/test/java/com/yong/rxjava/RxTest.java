package com.yong.rxjava;

import org.junit.Ignore;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by ChangYong on 2016. 7. 14..
 */
public class RxTest {
    @Test
    @Ignore
    public void create_test(){
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        System.out.println("Hello Create");
                        subscriber.onNext("next!");
                    }
                }
        );

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    @Ignore
    public void just_test(){
        Observable<String> observable = Observable.just("Hello Just");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    @Ignore
    public void defer_test(){
        System.out.println(Thread.currentThread().getName() + "11");
        Observable<String> observable = Observable.defer(
                new Func0<Observable<String>>() {
                    @Override
                    public Observable<String> call() {
                        System.out.println(Thread.currentThread().getName() + "22");
                        return Observable.just("Hello Defer");
                    }
                }
        );
        System.out.println(Thread.currentThread().getName() + "33");

        observable.subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(Thread.currentThread().getName() + "44");
                    }
                });
        System.out.println(Thread.currentThread().getName() + "55");

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(Thread.currentThread().getName() + "66");
            }
        });

        observable.observeOn(Schedulers.computation());
    }

    @Test
    public void defer_test_2(){
        System.out.println(Thread.currentThread().getName() + "11");

        Observable<String> observable = Observable.just(Thread.currentThread().getName() + "22");

        observable.observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(Thread.currentThread().getName() + "33 [" + s + "]");
                    }
                });
    }
}
