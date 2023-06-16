package com.example.complexapicalling.interfaces

interface ResponseCallback<T> {

    fun response(data:T?)
    fun errorMessage(message:String?)

}