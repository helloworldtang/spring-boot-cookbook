package com.tangcheng.learning.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

/**
 * @Auther: cheng.tang
 * @Date: 2019/4/13
 * @Description:
 */
@Slf4j
public class ShopTest {

    @Test
    public void test1() {

        Shop shop = new Shop("BestPrice");
        /**
         * 响应CompletableFuture的completion事件
         * 优化最佳价格查询器
         */
        CompletableFuture[] futures = shop.findPricesStream("myPhone")
                .map(f -> f.thenAccept(item -> {
                    System.out.println(item);
                    log.info("thenAccept item:{}", item);
                }))
                .toArray(CompletableFuture[]::new);
        /**
         * 等待最初Stream中的所有CompletableFuture对象执行完毕，
         * 对allOf方法返回的CompletableFuture执行join操作
         */
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops returned results or timed out");

        /**
         * 然而在另一些场景中，你可能希望只要CompletableFuture对象数组中有任何一个执行完毕就不再等待，
         * 比如，你正在查询两个汇率服务器，任何一个返回了结果都能满足你的需求。
         * 在这种情况下，你可以使用一个类似的工厂方法anyOf。该方法接收一个CompletableFuture对象构成的数组，
         * 返回由第一个执行完毕的CompletableFuture对象的返回值构成的Completable-Future<Object>。
         */

    }


    @Test
    public void test2() {
        /**
         * 20:30:38.405 [Thread-1] INFO com.tangcheng.learning.thread.Shop - getPriceFormat product:B
         * 20:30:38.405 [Thread-0] INFO com.tangcheng.learning.thread.Shop - getPriceFormat product:A
         * 20:30:38.415 [Thread-0] INFO com.tangcheng.learning.thread.Quote - Quote parse priceFormat:A:1:SILVER
         * 20:30:38.415 [Thread-1] INFO com.tangcheng.learning.thread.Quote - Quote parse priceFormat:B:1:SILVER
         * 20:30:38.723 [Thread-3] INFO com.tangcheng.learning.thread.Discount - Discount applyDiscount quote:{"discountCode":"SILVER","price":1.0,"shopName":"B"}   【此处起了第三个线程】
         * 20:30:38.723 [Thread-2] INFO com.tangcheng.learning.thread.Discount - Discount applyDiscount quote:{"discountCode":"SILVER","price":1.0,"shopName":"A"}
         * 20:30:39.726 [Thread-3] INFO com.tangcheng.learning.thread.ShopTest - done in mesc 1471
         * 20:30:39.726 [Thread-2] INFO com.tangcheng.learning.thread.ShopTest - done in mesc 1471
         */
        findPricesContinue();
    }

    List<Shop> shops = Arrays.asList(
            new Shop("A"),
            new Shop("B")
//            new Shop("C"),
//            new Shop("D"),
//            new Shop("E"),
//            new Shop("F"),
//            new Shop("G"),
//            new Shop("H"),
//            new Shop("I"),
//            new Shop("J")
    );

    public void findPricesContinue() {
        long st = System.currentTimeMillis();
        Stream<CompletableFuture<String>> futurePrices = shops.stream()
                //首先异步获取价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceFormat(shop.getName()), myExecutor))
                //将获取的字符串解析成对象
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异步任务有获取折扣价格
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), myExecutor)));
        //thenAccept()会在CompletableFuture完成之后使用他的返回值，这里会持续执行子线程
        CompletableFuture[] futures = futurePrices.map(f -> f.thenAccept(product -> {
            log.info("product:{} done in mesc {}", product, (System.currentTimeMillis() - st));
        })).toArray(CompletableFuture[]::new);
        //allOf()工厂方法接受由CompletableFuture对象构成的数组，这里使用其等待所有的子线程执行完毕
        CompletableFuture.allOf(futures).join();
    }


    /**
     * 异步查询
     * 相比并行流的话CompletableFuture更有优势：可以对执行器配置，设置线程池大小
     */
    @SuppressWarnings("all")
    private final Executor myExecutor = Executors.newFixedThreadPool(Math.min(shops.size() * 2, 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            //使用守护线程保证不会阻止程序的关停
            t.setDaemon(true);
            return t;
        }
    });

}