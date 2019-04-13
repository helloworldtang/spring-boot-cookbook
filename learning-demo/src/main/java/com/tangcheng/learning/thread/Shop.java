package com.tangcheng.learning.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

/**
 * <br>最佳价格查询器</br>
 */
@Slf4j
public class Shop {
    //商店名称
    private String name;
    //商店列表
    private static List<Shop> shops;

    static {
        shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("ShopEasy"),
                new Shop("ShopColl"));
    }

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static Executor executor = Executors.newFixedThreadPool(
            //1为了避免发生由于商店的数目过多导致服务器超负荷而崩溃，设置一个上限100
            Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    //使用守护线程——这种方式不会阻止程序的关停
                    t.setDaemon(true);
                    return t;
                }
            }
    );

    /**
     * 以ShopName:price:DiscountCode的格式返回一个String类型的值
     *
     * @param product
     * @return
     */
    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    /**
     * 价格计算器
     *
     * @param product
     * @return
     */
    private double calculatePrice(String product) {
        randomDelay(); //模拟价格计算的时间
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1); //返回一个价格的随机值
    }

    /**
     * 重构findPrices方法返回一个由Future构成的流
     */
    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(
                        shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product),
                                executor
                        )
                )
                .map(
                        future -> future.thenApply(Quote::parse)
                )
                .map(
                        future -> future.thenCompose(
                                quote -> CompletableFuture.supplyAsync(
                                        () -> Discount.applyDiscount(quote),
                                        executor
                                )
                        )
                );
    }


    /**
     * 目前为止，你实现的findPrices方法只有在取得所有商店的返回值时才显示商品的价格。
     * 而你希望的效果是，只要有商店返回商品价格就在第一时间显示返回值，不再等待那些还未返回的商店（有些甚至会发生超时）
     *
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        return null;
    }

    private static final Random RANDOM = new Random();

    /**
     * 随机延迟
     */
    public static void randomDelay() {
        int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPriceFormat(String product) {
        log.info("getPriceFormat product:{}", product);
        return product + ":1:SILVER";
    }


}
