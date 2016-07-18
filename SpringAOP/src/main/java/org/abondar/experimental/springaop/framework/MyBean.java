package org.abondar.experimental.springaop.framework;


/**
 * Created by abondar on 18.07.16.
 */
public class MyBean {


    private MyDependency dep;

    public void execute(){
        dep.foo();
        dep.bar();
    }

    public void setDep(MyDependency dep){
        this.dep = dep;
    }
}
