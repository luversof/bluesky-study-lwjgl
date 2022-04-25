package io.github.luversof.study.lwjgl.main;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.luversof.study.lwjgl.main.domain.MainWindow;
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
		var mainWindow = new MainWindow();
		mainWindow.run();
		
		log.debug("TEST ");
	}
	
	
	public static class MouseListener {
		private static MouseListener instance;
		
		private double scrollX, scrollY;
		private double xPos, yPos, lastY, lastX;
		private boolean mouseButtonPressed[] = new boolean[3];
		private boolean isDragging;
		
		private MouseListener() {
			this.scrollX = 0.0;
			this.scrollY = 0.0;
			this.xPos = 0.0;
			this.yPos = 0.0;
			this.lastX = 0.0;
			this.lastY = 0.0;
		}
		
		public static MouseListener get() {
			if (MouseListener.instance == null) {
				MouseListener.instance = new MouseListener();
			}
			
			return MouseListener.instance;
		}
		
		public static void mousePosCallback(long window, double xpos, double ypos) {
			get().lastX = get().xPos;
			get().lastY = get().yPos;
			get().xPos = xpos;
			get().yPos = ypos;
		}
	}
}
