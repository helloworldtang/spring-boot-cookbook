package com.tangcheng.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tang.cheng on 2016/12/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SecurityTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testHome() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        ResponseEntity<String> entity = this.restTemplate.exchange("/", HttpMethod.GET,
                new HttpEntity<Void>(headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(entity.getHeaders().getLocation().toString())
                .endsWith(this.port + "/login");
    }

    @Test
    public void testLoginPage() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        ResponseEntity<String> entity = this.restTemplate.exchange("/login",
                HttpMethod.GET, new HttpEntity<Void>(headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("_csrf");
    }

    @Test
    public void testLogin() throws Exception {
        HttpHeaders headers = getHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.set("username", "user");
        form.set("password", "user");
        ResponseEntity<String> entity = this.restTemplate.exchange("/login",
                HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(form, headers),
                String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(entity.getHeaders().getLocation().toString())
                .endsWith(this.port + "/");
        assertThat(entity.getHeaders().get("Set-Cookie")).isNotNull();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> page = this.restTemplate.getForEntity("/login",
                String.class);
        assertThat(page.getStatusCode()).isEqualTo(HttpStatus.OK);
        String cookie = page.getHeaders().getFirst("Set-Cookie");
        headers.set("Cookie", cookie);
        Pattern pattern = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*");
        Matcher matcher = pattern.matcher(page.getBody());
        assertThat(matcher.matches()).as(page.getBody()).isTrue();
        headers.set("X-CSRF-TOKEN", matcher.group(1));
        return headers;
    }

    @Test
    public void testCss() throws Exception {
        ResponseEntity<String> entity = this.restTemplate
                .getForEntity("/css/bootstrap.min.css", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("body");
    }


}
