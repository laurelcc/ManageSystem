package com.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by soong on 2017/7/23.
 */
public class CustomBasicErrorController extends BasicErrorController {

    protected static final Logger logger = LoggerFactory.getLogger(CustomBasicErrorController.class);

    @Autowired
    public CustomBasicErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        logger.info("HttpStatus: {}", response.getStatus());

        int code = response.getStatus();
        HttpStatus status = HttpStatus.valueOf(code);
        if (status == null){
            return new ModelAndView("errors/500");
        }else{
            if (status.equals(HttpStatus.NOT_FOUND)){
                return new ModelAndView("errors/404");
            }else if(status.equals(HttpStatus.INTERNAL_SERVER_ERROR)){
                return new ModelAndView("errors/500");
            }else {
                return new ModelAndView("errors/500");
            }
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        return super.error(request);
    }
}
