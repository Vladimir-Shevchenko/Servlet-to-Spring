package com.aop;

import org.springframework.stereotype.Component;

@Component("libraryBean")
public class Library {

    public void getBook(){
        System.out.println("We are taking book");
    }
}
