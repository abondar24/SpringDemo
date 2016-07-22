package org.abondar.experimental.springmvc.web;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by abondar on 22.07.16.
 */
public class UrlUtil {
    public static String encodeUrlPathSegment(String pathSegment,
                                              HttpServletRequest httpServletRequest) {

    String enc = httpServletRequest.getCharacterEncoding();

        if (enc==null){
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }

        try{
            pathSegment = UriUtils.encodePathSegment(pathSegment,enc);
        } catch (UnsupportedEncodingException uee){}

        return pathSegment;
    }


}
