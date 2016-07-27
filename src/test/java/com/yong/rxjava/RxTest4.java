package com.yong.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by NHNENT on 2016-07-27.
 */
public class RxTest4 {
	@Test
	public void notify_test(){
		Observable<String> observable = Observable.just("hello?");

		observable.subscribe(new Subscriber<String>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onNext(String s) {
				System.out.println("[" + s + "]");
			}
		});
	}

	@Test
	public void publish_subject_test(){
		System.out.println("publish start==================================================================");
		PublishSubject<String> subject = PublishSubject.create();

		subject.onNext("first");
		subject.onNext("second");

		subject.subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println("["+ s +" 첫번째다]");
			}
		});

		subject.subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println("["+ s +" 두번째다]");
			}
		});

		subject.onNext("third");
		subject.onNext("fourth");
	}

	@Test
	public void behavior_subject_test(){
		System.out.println("behavior start==================================================================");
		BehaviorSubject<String> subject = BehaviorSubject.create();

		subject.onNext("first");
		subject.onNext("second");

		subject.subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println("[" + s + " 첫번째다]");
			}
		});

		subject.subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println("[" + s + " 두번째다]");
			}
		});

		subject.onNext("third");
		subject.onNext("fourth");
	}
}
