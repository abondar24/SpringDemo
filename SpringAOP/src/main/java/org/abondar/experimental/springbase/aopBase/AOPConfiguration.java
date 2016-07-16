package org.abondar.experimental.springbase.aopBase;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.MessageDigest;


/**
 * Created by abondar on 03.07.16.
 */

@Configuration
@PropertySource(value = "classpath:aop.properties")
public class AOPConfiguration {


}
