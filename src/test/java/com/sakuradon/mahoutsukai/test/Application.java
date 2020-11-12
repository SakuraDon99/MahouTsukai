package com.sakuradon.mahoutsukai.test;


import com.sakuradon.mahoutsukai.core.Starter;

public class Application {

    public static void main(String[] args) {
        Starter starter = new Starter(Application.class);
        starter.run(args);
    }

}
