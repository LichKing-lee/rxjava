package com.yong.first;

import org.junit.Test;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ChangYong on 2016. 7. 17..
 */
public class RxTest3 {
    @Test
    public void map_test(){
        System.out.println("map start");
        Observable.just("hello map")
                .map(new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        return s + "!!!";
                    }
                })
                .subscribe(s -> System.out.println(s));
    }

    @Test
    public void map_lambda_test(){
        System.out.println("map lambda start");
        Observable.just("hello map lambda")
                .map(s -> s + "!!!")
                .subscribe(s -> System.out.println(s));
    }

    @Test
    public void cast_test(){
        System.out.println("cast start");
        Observable.just("hello cast")
                .map(s -> s.hashCode())
                .subscribe(s -> System.out.println(Integer.parseInt(s)));
    }
}
