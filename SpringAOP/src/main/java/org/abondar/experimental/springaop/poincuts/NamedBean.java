package org.abondar.experimental.springaop.poincuts;

/**
 * Created by abondar on 17.07.16.
 */
public class NamedBean {

    public void foo(){
        System.out.println("foo");
    }

    public void foo(int x){
      System.out.println("foo " +x);

    }

    public void bar(){
        System.out.println("bar");
    }

    public void yup(){
        System.out.println("yup");
    }
}
