package org.abondar.experimental.BeanLifeCycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

/**
 * Created by abondar on 04.07.16.
 */
public class SimpleDestuctiveBean {
    private static Logger logger = LoggerFactory.getLogger(SimpleDestuctiveBean.class);

    private File file;

    @Value("#{systemProperties['java.io.tmpdir']}#{systemProperties['file.separator']}test.txt")
    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception{
        logger.info("Initializing  destructive bean");
        if(filePath==null){
            throw new IllegalArgumentException("Specify filePath property of "+ SimpleDestuctiveBean.class);
        }

        file = new File(filePath);
        file.createNewFile();

        logger.info("File exists: "+file.exists());
    }

    @PreDestroy
    public void destroy(){
        logger.info("Destroying bean");

        if (!file.delete()){
            logger.error("Fail to delete file");
        }

        logger.info("File exists: "+file.exists());
    }
}
