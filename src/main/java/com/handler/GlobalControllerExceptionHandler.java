package com.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by soong on 2017/7/22.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

//    public static final String DEFAULT_ERROR_VIEW = "errorsss";
//
//    @ExceptionHandler(value = {Exception.class})
//    public ModelAndView
//    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        // If the exception is annotated with @ResponseStatus rethrow it and let
//        // the framework handle it - like the OrderNotFoundException example
//        // at the start of this post.
//        // AnnotationUtils is a Spring Framework utility class.
//        if (AnnotationUtils.findAnnotation
//                (e.getClass(), ResponseStatus.class) != null)
//            throw e;
//
//        // Otherwise setup and send the user to a default error-view.
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }

}
