package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
//    private final MyLogger myLogger;
    private final MyLogger myLogger;
    /** 1번 방법
    private final ObjectProvider<MyLogger> myLogger;
     **/
    public void logic(String id) {
        /** 1번방법
        MyLogger object = myLogger.getObject();
//        myLogger.log("service id = "+id);
        object.log("service id = "+id);
         **/
        myLogger.log("service id = " +id);

    }
}
