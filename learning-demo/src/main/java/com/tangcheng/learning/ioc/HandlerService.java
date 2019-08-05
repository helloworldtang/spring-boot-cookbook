package com.tangcheng.learning.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class HandlerService {

    @Autowired
    private List<Handler> handlerList;//如果有接口Handler的实现类，则会自动注入到这个List

    @PostConstruct
    public void postConstruct() {
        /**
         * 2018-05-31 20:09:41.296  INFO 10024 --- [           main] com.tangcheng.learning.ioc.Handler1Impl  : getCanonicalName:com.tangcheng.learning.ioc.Handler1Impl,getSimpleName:Handler1Impl
         * 2018-05-31 20:09:41.297  INFO 10024 --- [           main] com.tangcheng.learning.ioc.Handler2Impl  : getCanonicalName:com.tangcheng.learning.ioc.Handler2Impl,getSimpleName:Handler1Impl
         */
        for (Handler handler : handlerList) {
            handler.handle();
        }
    }
}
