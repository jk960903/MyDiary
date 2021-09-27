package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j // 로깅 Facade이다. 로깅에 대한 추상 레이어를 제공하는 interface 모음
@RunWith(SpringRunner.class)
@SpringBootTest
class SampleProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
