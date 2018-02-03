package com.sonika.kotlinrxjavaexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var tvHello: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHello = findViewById<View>(R.id.tvHello) as TextView

        showToastOnSubscribe()
    }

    fun showToastOnSubscribe(){
        Observable.just("Welcome to RxJava")
                .subscribeOn(Schedulers.newThread())
                //each subscription is going to be on a new thread.
                .observeOn(AndroidSchedulers.mainThread())
        //observation on the main thread
        //Now our subscriber!
        .subscribe(object : Observer<String>{
            override fun onComplete() {
            }

            override fun onNext(t: String) {
                tvHello.setText(t)
                Toast.makeText(applicationContext, "Welcome to RxJava in android", Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
            }

            override fun onSubscribe(d: Disposable) {
            }
        })
    }
}
