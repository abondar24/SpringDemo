package org.abondar.experimental.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by abondar on 19.07.16.
 */
@RestController
@RequestMapping(value = "/test")
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);



    @RequestMapping(value = "/showData", method = RequestMethod.GET)
    @ResponseBody
    public MyBean listData() {
        return new MyBean(1L,"Hey Man!");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public MyBean save(@RequestBody MyBean myBean) {
        logger.info("Saving a new message: " + myBean);
        return myBean;
    }

    @RequestMapping(value = "/showMessages", method = RequestMethod.PUT)
    @ResponseBody
    public void update() {
        logger.info("It's failure man");

    }

}
