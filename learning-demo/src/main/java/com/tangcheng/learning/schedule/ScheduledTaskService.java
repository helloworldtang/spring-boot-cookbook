package com.tangcheng.learning.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MyWorld on 2016/8/13.
 */
@Service
@ConditionalOnProperty(name = "quartz.enabled", havingValue = "true")//根据条件决定是否实例化这个Bean，只能用在Bean上
public class ScheduledTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTaskService.class);
    /**
     * 日期格式化字符串[YYYY-MM-dd]使用错误，应注意使用小写'y'表示当天所在的年，大写'Y'代表week in whick year
     */
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Scheduled(fixedRate = 6000)
    public void fixRate() {
        LOGGER.info("fixRate : {}", dateFormat.format(new Date()));
    }

    /**
     * cron一共有7位，但是最后一位是年，可以留空，所以我们可以写6位。
     * 第一位，表示秒，取值0-59
     * 第二位，表示分，取值0-59
     * 第三位，表示小时，取值0-23
     * 第四位，日期天/日，取值1-31
     * 第五位，日期月份，取值1-12
     * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
     * 另外：1表示星期天，2表示星期一。
     * 第7为，年份，可以留空，取值1970-2099
     * <p>
     * 其中每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符。由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?
     * <p>
     * (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
     * (?)问号：问号只能出现在日期和星期这两个位置。
     * (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
     * (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
     * (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60另：* /y,等同于0/y
     * <p>
     * 0 0 3 * * ?     每天3点执行
     * 0 5 3 * * ?     每天3点5分执行
     * 0 5 3 ? * *     每天3点5分执行，与上面作用相同
     * 0 5/10 3 * * ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
     * 0 10 3 ? * 1    每周星期天，3点10分 执行，注：1表示星期天
     * 0 10 3 ? * 1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
     * <p>
     * 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
     * 0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时
     * 0 0 12 ? * WED 表示每个星期三中午12点
     * "0 0 12 * * ?" 每天中午12点触发
     * "0 15 10 ? * *" 每天上午10:15触发
     * "0 15 10 * * ?" 每天上午10:15触发
     * "0 15 10 * * ? *" 每天上午10:15触发
     * "0 15 10 * * ? 2005" 2005年的每天上午10:15触发
     * "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
     * "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
     * "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
     * "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
     * "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
     * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
     * "0 15 10 15 * ?" 每月15日上午10:15触发
     * "0 15 10 L * ?" 每月最后一日的上午10:15触发
     * "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
     * "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
     * "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
     */
    @Scheduled(cron = "0 50 * ? * *")
    public void specialTime() {
        LOGGER.info("cron {} ", dateFormat.format(new Date()));
    }

}
