package com.example.complexapicalling.interfaces

interface NamesInterface<T> {

    fun response(data:T?)
    fun errorMessage(message:String?)

}