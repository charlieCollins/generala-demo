package com.totsp.generala;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by cecollins on 6/23/16.
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("*** hello MainTest");


        // observable
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        // subscriber
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) { System.out.println(s); }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };

        //wiring
        myObservable.subscribe(mySubscriber);
    }


}
