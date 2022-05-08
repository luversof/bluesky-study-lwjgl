package io.github.luversof.study.lwjgl.main.domain;

public abstract class Component {
	
	public GameObject gameObject = null;

	public void start() {
		
	};
	
	public abstract void update(float dt);

}
