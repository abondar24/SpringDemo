package org.abondar.experimental.PropertyEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by abondar on 05.07.16.
 */
public class PropertyEditorBean {

    //property editors of different types
    private byte[] bytes;
    private Class cls;
    private Boolean trueOrFalse;
    private List<String> stringList;
    private Date date;
    private Float floatVal;

    @Value("#{systemProperties['java.io.tmpdir']}#{systemProperties['file.separator']}test.txt")
    private File file;

    private InputStream stream;
    private Locale locale;
    private Pattern pattern;
    private Properties properties;
    private String trimString;
    private URL url;

    private static Logger logger = LoggerFactory.getLogger(PropertyEditorBean.class);

    public void setBytes(byte[] bytes) {
        logger.info("Adding "+bytes.length + " bytes");
        this.bytes = bytes;
    }

    public void setCls(Class cls) {
        logger.info("Setting class: "+cls.getName());
        this.cls = cls;
    }

    public void setTrueOrFalse(Boolean trueOrFalse) {
        logger.info("Setting boolean: "+trueOrFalse);
        this.trueOrFalse = trueOrFalse;
    }

    public void setStringList(List<String> stringList) {
        logger.info("Setting string list of the size:  "+stringList.size());
        this.stringList = stringList;

        for(String str:stringList){
            logger.info("StringList member: "+str);
        }
    }

    public void setDate(Date date) {
        logger.info("Setting date: "+date);
        this.date = date;
    }

    public void setFloatVal(Float floatVal) {
        logger.info("Setting float value: "+floatVal);
        this.floatVal = floatVal;
    }

    public void setFile(File file) {
        logger.info("Setting file: "+file.getName());
        this.file = file;
    }

    public void setStream(InputStream stream) {
        logger.info("Setting stream: "+stream);
        this.stream = stream;
    }

    public void setLocale(Locale locale) {
        logger.info("Setting locale: "+locale.getDisplayName());
        this.locale = locale;
    }

    public void setPattern(Pattern pattern) {
        logger.info("Setting pattern: "+pattern);
        this.pattern = pattern;
    }

    public void setProperties(Properties properties) {
        logger.info("Loaded "+properties.size() + " properties");
        this.properties = properties;
    }

    public void setTrimString(String trimString) {
        logger.info("Setting trim string: "+trimString);
        this.trimString = trimString;
    }

    public void setUrl(URL url) {
        logger.info("Setting URL: "+url.toExternalForm());
        this.url = url;
    }
}
