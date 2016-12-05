package com.rest;

import com.api.LongTermTask;
import com.service.LongTimeAsyncCallService;
import com.util.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * Created by MyWorld on 2016/12/5.
 */
@RestController
public class AsyncController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);


    @Autowired
    private LongTimeAsyncCallService longTimeAsyncCallService;

    @RequestMapping(value = "/deferred", method = RequestMethod.GET)
    public DeferredResult<String> asyncTask() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        LOGGER.info("{} 调用！thread id is:{}",RequestHolder.getRequestFacade().getRequestURI(), Thread.currentThread().getId());
        longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTask() {
            @Override
            public void callback(Object result) {
                LOGGER.info("异步调用执行完成, thread id is:{}", Thread.currentThread().getId());
                deferredResult.setResult("Success");
            }
        });

        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("异步调用执行超时！thread id is:{}", Thread.currentThread().getId());
                deferredResult.setResult("异步调用执行超时");
            }
        });

        return deferredResult;
    }

    @RequestMapping(value = "/webAsyncTask", method = RequestMethod.GET)
    public WebAsyncTask longTimeTask() {
        LOGGER.info("{} 被调用 thread id is:{} ", RequestHolder.getRequestFacade().getRequestURI(),Thread.currentThread().getId());
        Callable<String> callable = () -> {
            TimeUnit.SECONDS.sleep(3);//假设是一些长时间任务
            LOGGER.info("执行成功 thread id is:{}", Thread.currentThread().getId());
            return "执行成功";
        };

        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(2000, callable);//时间 的单位是ms
        /**
         * 超时了，“执行成功”这句话还会打印？
         * 超时归超时，超时并不会打断正常执行流程，
         * 但注意，出现超时后我们给客户端返回了“超时”的结果，那接下来即便正常处理流程成功，
         * 客户端也收不到正常处理成功所产生的结果了，这带来的问题就是：客户端看到了“超时”，
         * 实际上操作到底有没有成功，客户端并不知道，
         * 但通常这也不是什么大问题，因为用户在浏览器上再刷新一下就好了
         */
        asyncTask.onTimeout(
                () -> {
                  /*
                   ModelAndView mav = new ModelAndView("longtimetask");
                    mav.addObject("result", "执行超时");
                  */
                    LOGGER.info("执行超时 thread id is:{}", Thread.currentThread().getId());
                    return "执行超时";
                }
        );
        /**
         * 直接返回Callable<ModelAndView>都是可以的，但我们这里包装了一层，是为了上面的“超时处理”
         * Callable的call方法并不是我们直接调用的，而是在longTimeTask返回后，
         * 由Spring MVC用一个工作线程来调用
         * 即仅仅是简单地把请求处理线程的任务转交给另一工作线程而已。
         * 其实“超时处理线程”和“回调处理线程”可能都是线程池中的某个线程
         */
        return new WebAsyncTask<>(3000, callable);//时间 的单位是ms
    }


}
