package com.yong.first;

import org.junit.Ignore;
import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by ChangYong on 2016. 7. 14..
 */
public class RxTest {
    @Test
    @Ignore
    public void just_test(){
        System.out.println("Observable create");
        Observable<String> observable = Observable.just("개미");
        System.out.println("do subscribe");
        observable.subscribe(
                new Subscriber<String>() {
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    public void onError(Throwable throwable) {
                        System.out.println("error :: " + throwable.getMessage());
                    }

                    public void onNext(String s) {
                        System.out.println("next :: " + s);
                    }
                }
        );
    }

    @Test
    @Ignore
    public void defer_test(){
        System.out.println("Observable Create");
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            public Observable<String> call() {
                System.out.println("defer call");
                //return Observable.just("hello rx");
                return null;
            }
        });

        System.out.println("do subscribe");

        observable.subscribe(
                new Subscriber<String>() {
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    public void onError(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }

                    public void onNext(String s) {
                        System.out.println(" ===> " + s);
                    }
                }
        );
    }

    @Test
    @Ignore
    public void async_test(){
        System.out.println("Observable Create");

        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            public Observable<String> call() {
                System.out.println(Thread.currentThread().getName() + ", defer function call");
                return Observable.just("Hello RX");
            }
        });

        System.out.println(Thread.currentThread().getName() + ", gogogo");

        observable.subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<String>() {
                        public void onCompleted() {
                            System.out.println(Thread.currentThread().getName() + ", Complete");
                        }

                        public void onError(Throwable throwable) {
                            System.out.println("Error");
                        }

                        public void onNext(String s) {
                            System.out.println(Thread.currentThread().getName() + ", Next");
                        }
                    });

        System.out.println(Thread.currentThread().getName() + ", final");
    }
}
