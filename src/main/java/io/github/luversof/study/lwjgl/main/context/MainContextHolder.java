package io.github.luversof.study.lwjgl.main.context;

public class MainContextHolder {
	
	private static final ThreadLocal<MainContext> contextHolder = new ThreadLocal<>();

	public static MainContext getContext() {
		var ctx = contextHolder.get();

		if (ctx == null) {
			ctx = new MainContext();
			contextHolder.set(ctx);
		}

		return ctx;
	}
	
}