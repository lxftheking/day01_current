package com.thekingqj;

interface  Foo{

    void say();

     default  int sum(int x,int y){
         return x+y;
     }
     public static int  div(int x,int y){
         return x/y;
    }

}


public class lambdaExp {


    public static void main(String[] args) {
        Foo foo=()->{
            System.out.println("呦hello");
        };
        foo.say();
        System.out.println(foo.sum(1, 20));
        System.out.println(Foo.div(10, 2));
    }

}

