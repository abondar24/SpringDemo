package org.abondar.experimental.springmvc.web;

import org.abondar.experimental.springmvc.dataAccess.ContactDataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by abondar on 20.07.16.
 */
@Configuration
@Import(ContactDataConfig.class)
@ComponentScan("org.abondar.experimental.springmvc")
public class ContactMvcRootConfig {
}
