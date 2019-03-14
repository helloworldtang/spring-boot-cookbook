package com.tangcheng.learning.string;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.TextRandomProvider;
import org.junit.Test;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 7/25/2018 7:25 AM
 */
public class RandomStringGeneratorTest {

    /**
     * https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/RandomStringGenerator.html
     */

    /**
     * <p>
     * Generates random Unicode strings containing the specified number of code points.
     * Instances are created using a builder class, which allows the
     * callers to define the properties of the generator. See the documentation for the
     * {@link RandomStringGenerator.Builder} class to see available properties.
     * </p>
     * <pre>
     * // Generates a 20 code point string, using only the letters a-z
     * RandomStringGenerator generator = new RandomStringGenerator.Builder()
     *     .withinRange('a', 'z').build();
     * String randomLetters = generator.generate(20);
     * </pre>
     * <pre>
     * // Using Apache Commons RNG for randomness
     * UniformRandomProvider rng = RandomSource.create(...);
     * // Generates a 20 code point string, using only the letters a-z
     * RandomStringGenerator generator = new RandomStringGenerator.Builder()
     *     .withinRange('a', 'z')
     *     .usingRandom(rng::nextInt) // uses Java 8 syntax
     *     .build();
     * String randomLetters = generator.generate(20);
     * </pre>
     * <p>
     * {@code RandomStringBuilder} instances are thread-safe when using the
     * default random number generator (RNG). If a custom RNG is set by calling the method
     * {@link RandomStringGenerator.Builder#usingRandom(TextRandomProvider) Builder.usingRandom(TextRandomProvider)}, thread-safety
     * must be ensured externally.
     * </p>
     *
     * @since 1.1
     */
    @Test
    public void generateRandomStringDemo() {
        //使用字母a-z，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator1 = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        String randomLetters = generator1.generate(20);
        System.out.println(StringUtils.center("随机字母字符串", 20, "="));
        System.out.println(randomLetters);

        //使用数字0-9，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator2 = new RandomStringGenerator.Builder()
                .withinRange('0', '9').build();
        String randomNumbers = generator2.generate(20);
        System.out.println(StringUtils.center("随机数字字符串", 20, "="));
        System.out.println(randomNumbers);

        //使用码位为0到z的字符，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator3 = new RandomStringGenerator.Builder()
                .withinRange('0', 'z').build();
        String random = generator3.generate(20);
        System.out.println(StringUtils.center("随机混合字符串", 20, "="));
        System.out.println(random);
    }


    /**
     * <p>Operations for random {@code String}s.</p>
     * <p>Currently <em>private high surrogate</em> characters are ignored.
     * These are Unicode characters that fall between the values 56192 (db80)
     * and 56319 (dbff) as we don't know how to handle them.
     * High and low surrogates are correctly dealt with - that is if a
     * high surrogate is randomly chosen, 55296 (d800) to 56191 (db7f)
     * then it is followed by a low surrogate. If a low surrogate is chosen,
     * 56320 (dc00) to 57343 (dfff) then it is placed after a randomly
     * chosen high surrogate. </p>
     *
     * <p>#ThreadSafe#</p>
     *
     * @since 1.0
     * @deprecated as of 3.6, use commons-text
     * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/RandomStringGenerator.html">
     * RandomStringGenerator</a> instead
     */
    @Test
    public void test() {
        String random = RandomStringUtils.random(5, true, true);
        System.out.println(random);
    }


}
