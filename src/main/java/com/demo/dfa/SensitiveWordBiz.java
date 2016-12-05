package com.demo.dfa;

import java.util.*;

/**
 * Created by tang.cheng on 2016/10/29.
 */
public class SensitiveWordBiz {

    /**
     * 将敏感词词条加入到敏感词词库
     *
     * @param keyWordSet 敏感词词条
     * @return
     */
    public Map addSensitiveWordToHashMap(Set<String> keyWordSet) {
        Map sensitiveWordMap = new HashMap<>(keyWordSet.size());     //初始化敏感词容器，减少扩容操作

        //迭代keyWordSet
        for (String key : keyWordSet) {//总的敏感词
            Map currentNode = sensitiveWordMap;//每条敏感词之间是相互独立的，都从sensitiveWordMap开始匹配
            for (char keyChar : key.toCharArray()) {//一个敏感词条
                Map nextNode = (Map) currentNode.get(keyChar);       //获取
                if (nextNode == null) {//往词库中放数据，如果这个词不存在，则map的value肯定是null
                    Map newNode = new HashMap<>();//使用默认的DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16 ，因为不知道设定多少好。如果已经敏感词的情况，可以调整initial_capacity,节省内存
                    currentNode.put(keyChar, newNode);//当前词对应的下一个词设置了空map,则匹配时，以value.isEmpty来判定是否到最后一个词
                    currentNode = newNode;//新new的map就成为当前节点，用来存放下一个词
                    continue;
                }
                currentNode = nextNode;//当前的词已经存在，以这个词为key的map成为当前节点
            }
        }
        return sensitiveWordMap;
    }

    /**
     * 是否包括敏感词
     *
     * @param input                   待检测的字符串
     * @param sensitiveWordRepository 敏感词词库
     * @return true 包含了敏感词；false 没有包含
     */
    public boolean containSensitiveWord(String input, Map sensitiveWordRepository) {
        char[] source = input.toCharArray();
        Map currentNode = sensitiveWordRepository;
        for (char key : source) {
            Map nextNode = (Map) currentNode.get(key);

            if (nextNode == null) {//没有以这个词打头的 敏感词条
                currentNode = sensitiveWordRepository;//进入下一轮，check以下一个词打头的词
                continue;
            }

            if (nextNode.isEmpty()) {
                return true;//找到最后一个词了，说明包括了敏感词
            }
            currentNode = nextNode;
        }
        return false;
    }


    public List<IndexPair> collectSensitiveRange(String input, Map sensitiveWordRepository) {
        char[] source = input.toCharArray();
        Map currentNode = sensitiveWordRepository;

        List<IndexPair> sensitiveWordList = new ArrayList<>();
        IndexPair indexPair = null;
        for (int i = 0; i < source.length; i++) {
            char key = source[i];
            Map nextNode = (Map) currentNode.get(key);

            if (nextNode == null) {//没有以这个词打头的 敏感词条
                currentNode = sensitiveWordRepository;//进入下一轮，check以下一个词打头的词
                continue;
            }

            if (nextNode.isEmpty()) {
                if (indexPair != null) {//敏感词 如果只有一个字符，indexPair是null
                    sensitiveWordList.add(indexPair.addToIdx(i));
                } else {
                    sensitiveWordList.add(IndexPair.create(i).addToIdx(i));
                }
                currentNode = sensitiveWordRepository;///找到最后一个词了，说明包括了敏感词.
                continue;//因为要获取所有敏感词，进入下一轮，check以下一个词打头的词
            }

            indexPair = IndexPair.create(i);
            currentNode = nextNode;
        }
        return sensitiveWordList;
    }

    public String replaceSensitiveWord(String input, String canDisplayWork, Map sensitiveWordRepository) {
        StringBuilder tmp = new StringBuilder(input);
        List<IndexPair> indexPairs = collectSensitiveRange(input, sensitiveWordRepository);
        for (int i = indexPairs.size() - 1; i >= 0; i--) {
            IndexPair pair = indexPairs.get(i);
            tmp.delete(pair.getFromIdx(), pair.getToIdx() + 1);//将这个字段
            tmp.insert(pair.getFromIdx(), canDisplayWork);
        }
        return tmp.toString();
    }

    /**
     * 测试的输出结果
     * {传={销={}}, j={a={v={a={中={文={乱={码={解={决={之={道={（={八={）={—={–={解={决={U={R={L={中={文={乱={码={问={题={}}}}}}}}}}}}}}}, 四={）={—={–={j={a={v={a={编={码={转={换={过={程={}}}}}}}}}}}}}}, 六={）={—={–={j={a={v={a={W={e={b={中={的={编={码={解={码={}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
     * true
     * 为什么不打击#活动,#并不能让人幸福的生活
     *
     * @param args
     */
    public static void main(String[] args) {
        SensitiveWordBiz wordInit = new SensitiveWordBiz();
        Set<String> source = new HashSet<>();
        source.add("java中文乱码解决之道（八）—–解决URL中文乱码问题");
        source.add("java中文乱码解决之道（六）—–javaWeb中的编码解码");
        source.add("java中文乱码解决之道（四）—–java编码转换过程");
        source.add("传销");
        Map map = wordInit.addSensitiveWordToHashMap(source);
        System.out.println(map);
        String input = "为什么不打击传销活动,传销并不能让人幸福的生活";
        boolean result = wordInit.containSensitiveWord(input, map);
        System.out.println(result);
        String output = wordInit.replaceSensitiveWord(input, "#", map);
        System.out.println(output);
    }


}

class IndexPair {
    private int fromIdx;
    private int toIdx;

    private IndexPair(int fromIdx) {

        this.fromIdx = fromIdx;
    }

    public static IndexPair create(int fromIdx) {
        return new IndexPair(fromIdx);
    }

    public int getFromIdx() {
        return fromIdx;
    }

    public void setFromIdx(int fromIdx) {
        this.fromIdx = fromIdx;
    }

    public int getToIdx() {
        return toIdx;
    }

    public void setToIdx(int toIdx) {
        this.toIdx = toIdx;
    }

    public IndexPair addToIdx(int toIdx) {
        setToIdx(toIdx);
        return this;
    }
}
