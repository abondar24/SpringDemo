package org.abondar.experimental.springaop.framework.aspectJ;


/**
 * Created by abondar on 16.07.16.
 */


public class AspectDependency {
    public void foo(int intVal){
        System.out.println("foo: "+intVal);
    }

    public void bar(){
        System.out.println("bar");
    }
}
