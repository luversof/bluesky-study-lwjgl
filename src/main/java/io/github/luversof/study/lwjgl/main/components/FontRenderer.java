package io.github.luversof.study.lwjgl.main.components;

import io.github.luversof.study.lwjgl.main.domain.Component;

public class FontRenderer extends Component {
	
	@Override
	public void start() {
		if (gameObject.getComponent(SpriteRenderer.class) != null) {
			System.out.println("Found Font Renderer!");
		}
	}

	@Override
	public void update(float dt) {
		
	}

}
