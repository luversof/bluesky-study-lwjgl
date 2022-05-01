package io.github.luversof.study.lwjgl.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.lwjgl.glfw.GLFW.*;

import java.util.List;

import org.junit.jupiter.api.Test;


import io.github.luversof.study.lwjgl.main.domain.MainWindow;
import io.github.luversof.study.lwjgl.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MainTest {

	@Test
	void test() {
		String a = "test";
		assertThat(a).isEqualTo("test");
		assertThat(a).hasSize(4);
		assertThat(a).isNotEmpty();
		
		int b = 123;
		assertThat(b).isEqualTo(123);
		assertThat(b).isGreaterThan(122);
		assertThat(b).isNotZero();
		
		List<String> c = List.of("valueA", "valueB");
		assertThat(c).hasSize(2);
		assertThat(c).isNotEmpty();
	}
	
	@Test
	void mainTest() {
		var mainWindow = MainWindow.get();
		mainWindow.run();
		
		log.debug("TEST ");
	}
	
	@Test
	void test2() {
		System.out.println(TimeUtil.getTime());
	}
}
