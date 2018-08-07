package com.tangcheng.learning.collect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/08/07 11:01
 */
@Slf4j
public class CollectShuttleTest {

    /**
     * 从1000个中去掉100个，总耗时18ms
     * 混淆后的数据，没有发现明显的问题
     */
    @Test
    public void shuffleTest() {
        List<Integer> total = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            total.add(i);
        }
        StopWatch stopWatch = new StopWatch("Collections.shuffle()");
        int count = 100;
        List<Integer> winnerList = new ArrayList<>(100);
        for (int i = 0; i < count; i++) {
            stopWatch.start("time_" + i);
            Collections.shuffle(total);
            int index = total.size() - 1;
            Integer winner = total.remove(index);
            winnerList.add(winner);
            log.info("taskName:{},winner:{}", i, winner);
            stopWatch.stop();
        }
        log.info("winnerList:{},stopWatch.prettyPrint():{}", winnerList, stopWatch.prettyPrint());
        /**
         * winnerList:[
         * 163, 905, 954, 828, 387, 217, 272, 662, 753, 160,
         * 636, 629, 132, 318, 655, 388, 501, 879, 625, 515,
         * 339, 897, 497, 959, 819, 654, 71, 984, 356, 256,
         * 539, 330, 888, 643, 928, 10, 213, 878, 935, 206,
         * 53, 875, 437, 422, 997, 464, 276, 65, 451, 732,
         * 432, 154, 385, 955, 161, 719, 352, 383, 37, 853,
         * 675, 696, 646, 223, 742, 807, 76, 738, 415, 516,
         * 890, 656, 610, 910, 80, 7, 561, 548, 947, 390,
         * 949, 236, 382, 338, 112, 240, 162, 642, 754, 571,
         * 8, 802, 532, 410, 372, 462, 880, 38, 744, 360
         * ],
         * stopWatch.prettyPrint():StopWatch 'Collections.shuffle()': running time (millis) = 18
         * -----------------------------------------
         * ms     %     Task name
         * -----------------------------------------
         * 00007  039%  time_0
         * 00000  000%  time_1
         * 00000  000%  time_2
         * 00001  006%  time_3
         * 00000  000%  time_4
         * 00000  000%  time_5
         * 00000  000%  time_6
         * 00000  000%  time_7
         * 00000  000%  time_8
         * 00000  000%  time_9
         * 00000  000%  time_10
         * 00000  000%  time_11
         * 00001  006%  time_12
         * 00000  000%  time_13
         * 00000  000%  time_14
         * 00000  000%  time_15
         * 00000  000%  time_16
         * 00000  000%  time_17
         * 00000  000%  time_18
         * 00000  000%  time_19
         * 00000  000%  time_20
         * 00000  000%  time_21
         * 00000  000%  time_22
         * 00000  000%  time_23
         * 00001  006%  time_24
         * 00000  000%  time_25
         * 00000  000%  time_26
         * 00000  000%  time_27
         * 00001  006%  time_28
         * 00000  000%  time_29
         * 00001  006%  time_30
         * 00000  000%  time_31
         * 00000  000%  time_32
         * 00000  000%  time_33
         * 00000  000%  time_34
         * 00000  000%  time_35
         * 00000  000%  time_36
         * 00000  000%  time_37
         * 00000  000%  time_38
         * 00000  000%  time_39
         * 00000  000%  time_40
         * 00001  006%  time_41
         * 00000  000%  time_42
         * 00000  000%  time_43
         * 00000  000%  time_44
         * 00000  000%  time_45
         * 00000  000%  time_46
         * 00000  000%  time_47
         * 00000  000%  time_48
         * 00000  000%  time_49
         * 00000  000%  time_50
         * 00001  006%  time_51
         * 00000  000%  time_52
         * 00000  000%  time_53
         * 00000  000%  time_54
         * 00000  000%  time_55
         * 00000  000%  time_56
         * 00000  000%  time_57
         * 00000  000%  time_58
         * 00000  000%  time_59
         * 00000  000%  time_60
         * 00000  000%  time_61
         * 00000  000%  time_62
         * 00001  006%  time_63
         * 00000  000%  time_64
         * 00000  000%  time_65
         * 00001  006%  time_66
         * 00000  000%  time_67
         * 00000  000%  time_68
         * 00000  000%  time_69
         * 00000  000%  time_70
         * 00000  000%  time_71
         * 00000  000%  time_72
         * 00000  000%  time_73
         * 00000  000%  time_74
         * 00000  000%  time_75
         * 00000  000%  time_76
         * 00001  006%  time_77
         * 00000  000%  time_78
         * 00000  000%  time_79
         * 00000  000%  time_80
         * 00000  000%  time_81
         * 00000  000%  time_82
         * 00000  000%  time_83
         * 00000  000%  time_84
         * 00000  000%  time_85
         * 00000  000%  time_86
         * 00000  000%  time_87
         * 00000  000%  time_88
         * 00000  000%  time_89
         * 00000  000%  time_90
         * 00000  000%  time_91
         * 00001  006%  time_92
         * 00000  000%  time_93
         * 00000  000%  time_94
         * 00000  000%  time_95
         * 00000  000%  time_96
         * 00000  000%  time_97
         * 00000  000%  time_98
         * 00000  000%  time_99
         */
    }


}
