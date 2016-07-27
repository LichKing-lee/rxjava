package com.yong.observer;

import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ChangYong on 2016. 7. 23..
 */
public class ObserverTest1 {
    @Test
    public void 옵저버패턴_테스트(){
        Observable observable = new Observable(){
            @Override
            public void notifyObservers() {
                this.setChanged();
                this.notifyObservers(null);
            }
        };
        Observer observer1 = new Observer() {
            public void update(Observable o, Object arg) {
                System.out.println("Hello Observer1");
            }
        };
        Observer observer2 = new Observer() {
            public void update(Observable o, Object arg) {
                System.out.println("Hello Observer2");
            }
        };
        Observer observer3 = new Observer() {
            public void update(Observable o, Object arg) {
                System.out.println("Hello Observer3");
            }
        };

        observable.addObserver(observer1);
        observable.addObserver(observer2);
        observable.addObserver(observer3);
        observable.notifyObservers();
    }
}
