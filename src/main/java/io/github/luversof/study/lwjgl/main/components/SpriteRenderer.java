package io.github.luversof.study.lwjgl.main.components;

import io.github.luversof.study.lwjgl.main.domain.Component;

public class SpriteRenderer extends Component {
	
	private boolean firstTime = false;

	@Override
	public void start() {
		System.out.println("I am starting");
	}

	@Override
	public void update(float dt) {
		if (!firstTime) {
			System.out.println("I am updating");
			firstTime = true;
		}
	}

}
