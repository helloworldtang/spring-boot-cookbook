/**
 * @Auther: cheng.tang
 * @Date: 2019/3/14
 * @Description:
 */
package com.tangcheng.learning.adapter.web.config;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.TextRandomProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cheng.tang
 * @Date: 2019/3/14
 * @Description:
 */
@Configuration
public class RandomStringConfig {

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
    @Bean
    public RandomStringGenerator randomStringGenerator() {
        UniformRandomProvider rng = RandomSource.create(RandomSource.MT);
        return new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .usingRandom(rng::nextInt) // uses Java 8 syntax
                .build();
    }


}
