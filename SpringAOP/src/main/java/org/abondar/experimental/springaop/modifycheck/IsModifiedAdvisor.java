package org.abondar.experimental.springaop.modifycheck;

import org.aopalliance.aop.Advice;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * Created by abondar on 18.07.16.
 */
public class IsModifiedAdvisor extends DefaultIntroductionAdvisor{
    public IsModifiedAdvisor() {
        super(new IsModifiedMixin());
    }
}
