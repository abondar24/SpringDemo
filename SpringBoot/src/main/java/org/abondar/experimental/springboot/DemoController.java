package org.abondar.experimental.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by abondar on 25.07.16.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(method= RequestMethod.GET)
    public String demo() {
      return "Spring Boot REST demo";
    }
}
