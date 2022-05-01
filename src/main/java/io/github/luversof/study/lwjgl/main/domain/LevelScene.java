package io.github.luversof.study.lwjgl.main.domain;

public class LevelScene extends Scene {
	
	public LevelScene() {
		System.out.println("Inside level scene");
		
		MainWindow.get().setR(1);
		MainWindow.get().setG(1);
		MainWindow.get().setB(1);
	}

	@Override
	public void update(float dt) {
		
	}

}
