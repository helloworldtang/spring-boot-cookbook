package com.tangcheng.learning.thread;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }

    /**
     * Shop.getPrice()会以ShopName:price:DiscountCode的格式返回一个String类型的值
     *
     * @param priceFormat
     * @return
     */
    public static Quote parse(String priceFormat) {
        log.info("Quote parse priceFormat:{}", priceFormat);
        String[] split = priceFormat.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

}