package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final  LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final MyLogger myLogger;
    /**1번 방법
private final ObjectProvider<MyLogger> myLogger;
**/
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        System.out.println("myLogger.getClass() = " + myLogger.getClass());
        /** 1번 방법
        MyLogger object = myLogger.getObject();
        **/
        String requestURL = request.getRequestURL().toString();
//        myLogger.setRequestURL(requestURL);
        myLogger.setRequestURL(requestURL);
        /** 1번 방법
        object.setRequestURL(requestURL);
        **/
//        myLogger.log("controller test");
        myLogger.log("controller test");
        /** 1번 방법
        object.log("controller test");
        **/
         logDemoService.logic("testId");
        return "ok";
    }

}
