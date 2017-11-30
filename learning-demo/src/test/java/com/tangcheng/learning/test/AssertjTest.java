package com.tangcheng.learning.test;

import com.tangcheng.learning.test.assertj.AssertJEmployee;
import com.tangcheng.learning.test.assertj.AssertJPerson;
import com.tangcheng.learning.test.assertj.AssertJRing;
import com.tangcheng.learning.test.assertj.Magical;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.DateUtil.parse;
import static org.assertj.core.util.DateUtil.parseDatetimeWithMs;
import static org.assertj.core.util.Lists.newArrayList;

/**
 * https://github.com/joel-costigliola/assertj-core
 * https://github.com/joel-costigliola/assertj-core.git
 * https://github.com/joel-costigliola/assertj-examples
 * https://github.com/joel-costigliola/assertj-examples.git
 *
 * @author tangcheng
 * 2017/11/30
 */
@Slf4j
public class AssertjTest {

    @Test
    public void testString() {
        String str = null;
        assertThat(str).isNullOrEmpty();
        str = "";
        assertThat(str).isEmpty();
        str = "Frodo";
        assertThat(str).isEqualTo("Frodo").isEqualToIgnoringCase("frodo");
        assertThat(str).startsWith("Fro").endsWith("do").hasSize(5);
        assertThat(str).contains("ro").doesNotContain("or");
        assertThat(str).containsOnlyOnce("odo");
        assertThat(str).matches("..o.o").doesNotContain(".*d");
    }


    @Test
    public void TestNumber() {
        Integer num = null;
        assertThat(num).isNull();
        num = 42;
        assertThat(num).isEqualTo(42);
        assertThat(num).isGreaterThan(38).isGreaterThanOrEqualTo(39);
        assertThat(num).isLessThan(58).isLessThanOrEqualTo(50);
        assertThat(num).isNotZero();
        assertThat(0).isZero();
        assertThat(num).isPositive().isNotNegative();
        assertThat(-1).isNegative().isNotPositive();
    }

    @Test
    public void testDate() {
        assertThat(parse("2017-11-30"))
                .isEqualTo("2017-11-30")
                .isNotEqualTo("2017-11-29")
                .isAfter("2017-11-28")
                .isBefore(parse("2017-12-1"));

        assertThat(LocalDate.now().toDate())
                .isBefore(LocalDate.now().plusYears(1).toDate())
                .isAfter(LocalDate.now().minusYears(1).toDate());

        assertThat(parse("2017-11-30"))
                .isBetween("2017-11-1", "2017-12-1")
                .isNotBetween(parse("2017-12-1"), parse("2018-12-1"));

        assertThat(LocalDateTime.now().toDate())
                .isCloseTo(LocalDateTime.now().plusMillis(100).toDate(), 100)
                .isCloseTo(LocalDateTime.now().plusMillis(100).toDate(), 200)
                .isCloseTo(LocalDateTime.now().minusMillis(100).toDate(), 100)
                .isCloseTo(LocalDateTime.now().minusMillis(100).toDate(), 500);

        Date actual = parseDatetimeWithMs("2017-11-30T01:00:00.000");

        Date date2 = parseDatetimeWithMs("2017-11-30T01:00:00.555");
        assertThat(actual).isEqualToIgnoringMillis(date2);
        assertThat(actual).isInSameSecondAs(date2);

        Date date3 = parseDatetimeWithMs("2017-11-30T01:00:55.555");
        assertThat(actual).isEqualToIgnoringSeconds(date3);
        assertThat(actual).isInSameMinuteAs(date3);

        Date date4 = parseDatetimeWithMs("2017-11-30T01:55:55.555");
        assertThat(actual).isEqualToIgnoringMinutes(date4);
        assertThat(actual).isInSameHourAs(date4);

        Date date5 = parseDatetimeWithMs("2017-11-30T05:55:55.555");
        assertThat(actual).isEqualToIgnoringHours(date5);
        assertThat(actual).isInSameDayAs(date5);
    }


    @Test
    public void testList() {
        assertThat(Collections.EMPTY_LIST).isEmpty();
        assertThat(newArrayList()).isEmpty();
        assertThat(newArrayList(1, 2, 3)).startsWith(1).endsWith(3);
        assertThat(newArrayList(1, 2, 3)).contains(1, atIndex(0))
                .contains(2, atIndex(1))
                .contains(3, atIndex(2))
                .isSorted();
        assertThat(newArrayList(3, 1, 2)).isSubsetOf(newArrayList(1, 2, 3, 4));
        assertThat(newArrayList("a", "b", "c")).containsOnlyOnce("a");
    }


    @Test
    public void testMap() {
        HashMap<String, Object> foo = newHashMap();
        foo.put("A", 1);
        foo.put("B", 2);
        foo.put("C", 3);

        assertThat(foo).isNotEmpty().hasSize(3);
        assertThat(foo).contains(entry("A", 1), entry("B", 2));
        assertThat(foo).containsKeys("A", "C");
        assertThat(foo).containsValues(3, 1);
    }


    @Test
    public void testClass() {
        assertThat(Magical.class).isAnnotation();
        assertThat(AssertJRing.class).isNotAnnotation();
        assertThat(AssertJRing.class).hasAnnotation(Magical.class);
        assertThat(AssertJRing.class).isNotInterface();
        assertThat("string").isInstanceOf(String.class);
        assertThat(AssertJPerson.class).isAssignableFrom(AssertJEmployee.class);
    }

    @Test
    public void testFail() {
        /**
         *除此之外，还提供包括Exception、Iterable、JodaTime、Guava等等很多的断言支持。
         */
        try {
            fail("在不检查任何条件的情况下使断言失败。显示一则消息");
        } catch (AssertionError e) {
            log.warn("可以通过catch捕获该Error");
        }

        try {
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (AssertionError e) {
            log.warn("可以通过catch捕获该Error");
        }
    }


}
