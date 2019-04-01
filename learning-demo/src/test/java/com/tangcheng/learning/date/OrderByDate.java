package com.tangcheng.learning.date;

import com.tangcheng.learning.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cheng.tang
 * @Date: 2019/4/1
 * @Description:
 */
@Data
@AllArgsConstructor
public class OrderByDate {

    //物流轨迹时间
    private Date trackTime;

    //轨迹信息
    private String remark;

    public OrderByDate(String trackTime, String remark) {
        this.setTrackTime(DateUtil.convertToDate(trackTime));
        this.remark = remark;
    }


    public static void main(String[] args) {
        List<OrderByDate> tmpList = Arrays.asList(new OrderByDate("2019-03-29 18:41:02", "data1"),
                new OrderByDate("2019-04-01 10:14:02", "data2"),
                new OrderByDate("2019-03-29 19:21:19", "data3"),
                new OrderByDate("2019-03-30 05:41:11", "data4"));
        /**
         * 升序:
         * OrderByDate(trackTime=Mon Apr 01 10:14:02 CST 2019, remark=data2)
         * OrderByDate(trackTime=Sat Mar 30 05:41:11 CST 2019, remark=data4)
         * OrderByDate(trackTime=Fri Mar 29 19:21:19 CST 2019, remark=data3)
         * OrderByDate(trackTime=Fri Mar 29 18:41:02 CST 2019, remark=data1)
         */
        tmpList.sort(new Comparator<OrderByDate>() {
            @Override
            public int compare(OrderByDate o1, OrderByDate o2) {
                if (o1.getTrackTime().after(o2.getTrackTime())) {
                    //当前大，返回-1,则表示升序
//                    return getMillisOf(this) > getMillisOf(when);
                    return -1;
                } else if (o1.getTrackTime().before(o2.getTrackTime())) {
                    return 1;
                }
                return 0;
            }
        });
        tmpList.forEach(System.out::println);
    }

}
