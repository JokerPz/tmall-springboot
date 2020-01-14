package com.joker.tmall_springboot;

import com.joker.tmall.TmallSpringbootApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TmallSpringbootApplication.class)
@WebAppConfiguration
public class BaseTest {

}
