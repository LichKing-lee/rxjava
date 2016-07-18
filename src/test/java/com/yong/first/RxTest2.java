package com.yong.first;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action;
import rx.functions.Action1;

/**
 * Created by ChangYong on 2016. 7. 17..
 */
public class RxTest2 {
    Subscriber<String> subscriber;

    @Before
    public void setUp(){
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete!");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
    }

    @Test
    public void create_observable(){
        System.out.println("create start");
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>(){
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello RxJava");
                        sub.onCompleted();
                    }
                }
        );

        observable.subscribe(subscriber);
    }

    @Test
    public void just_observable(){
        System.out.println("just start");
        Observable<String> observable = Observable.just("hello just");

        observable.subscribe(subscriber);
    }

    @Test
    public void action_observable(){
        System.out.println("action start");
        Observable<String> observable = Observable.just("hello action", "hello second");
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        Action1<Throwable> action12 = new Action1<Throwable>() {
            @Override
            public void call(Throwable s) {
                System.out.println(s);
            }
        };

        observable.subscribe(action1, action12);
    }

    @Test
    public void chain_observable(){
        System.out.println("chain start");

        Observable.just("hello chain")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    @Test
    public void lambda_observable(){
        System.out.println("lambda start");

        Observable.just("hello lambda")
                .subscribe(s -> System.out.println(s));
    }
}
