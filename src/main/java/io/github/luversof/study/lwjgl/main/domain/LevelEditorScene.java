package io.github.luversof.study.lwjgl.main.domain;

import java.awt.event.KeyEvent;

import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene {

	private String vertexShaderSrc = "#version 330 core\r\n"
			+ "layout (location=0) in vec3 aPos;\r\n"
			+ "layout (location=1) in vec4 aColor;\r\n"
			+ "\r\n"
			+ "out vec4 fColor;\r\n"
			+ "\r\n"
			+ "void main()\r\n"
			+ "{\r\n"
			+ "	fColor = aColor;\r\n"
			+ "	gl_Position = vec4(aPos, 1.0); \r\n"
			+ "}";
	
	private String fragmentShaderSrc = "#version 330 core\r\n"
			+ "\r\n"
			+ "in vec4 fColor;\r\n"
			+ "\r\n"
			+ "out vec4 color;\r\n"
			+ "\r\n"
			+ "void main()\r\n"
			+ "{\r\n"
			+ "	color = fColor;\r\n"
			+ "}";
	
	private int vertexID, fragmentID, shaderProgram;

	public LevelEditorScene() {
		
	}	
	
	@Override
	public void init() {
		// Compile and link shaders
		
		// First load and compile the vertex shader
		vertexID = glCreateShader(GL_VERTEX_SHADER);
		
		// pass the shader source to the GPU
		glShaderSource(vertexID, vertexShaderSrc);
		glCompileShader(vertexID);
		
		// Check for errors in compilation
		int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
		if (success == GL_FALSE) {
			int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: 'defaultShader.glsl'\n\tVertex shader compilation failed." );
			System.out.println(glGetShaderInfoLog(vertexID, len));
			assert false : ""; 
		}
		
		// First load and compile the vertex shader
		fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
		
		// pass the shader source to the GPU
		glShaderSource(fragmentID, fragmentShaderSrc);
		glCompileShader(fragmentID);
		
		// Check for errors in compilation
		success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
		if (success == GL_FALSE) {
			int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: 'defaultShader.glsl'\n\tFragment shader compilation failed." );
			System.out.println(glGetShaderInfoLog(fragmentID, len));
			assert false : ""; 
		}
		
		// Link shaders and check for errors
		shaderProgram = glCreateProgram();
		glAttachShader(shaderProgram, vertexID);
		glAttachShader(shaderProgram, fragmentID);
		glLinkProgram(shaderProgram);
		
		// Check for linking errors
		success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
		if (success == GL_FALSE) {
			int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: 'defaultShader.glsl'\n\tLinking of shaders failed." );
			System.out.println(glGetProgramInfoLog(shaderProgram, len));
			assert false : "";
		}
	}
	
	@Override
	public void update(float dt) {
		
	}
	
}