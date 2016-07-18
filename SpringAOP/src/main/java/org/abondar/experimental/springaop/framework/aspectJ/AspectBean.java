package org.abondar.experimental.springaop.framework.aspectJ;


import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by abondar on 18.07.16.
 */


public class AspectBean {


    private AspectDependency dep;

    public void execute(){
        dep.foo(100);
        dep.foo(101);
        dep.bar();
    }

    @Autowired
    public void setDep(AspectDependency dep){
        this.dep = dep;
    }
}
