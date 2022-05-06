package io.github.luversof.study.lwjgl.main.domain;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import io.github.luversof.study.lwjgl.main.renderer.Shader;
import io.github.luversof.study.lwjgl.util.TimeUtil;

public class LevelEditorScene extends Scene {
	
	private float[] vertexArray = {
			// position				//color
			100.5f, 0.5f, 0.0f,		1.0f, 0.0f, 0.0f, 1.0f,	// Bottom right
			0.5f, 100.5f, 0.0f,		0.0f, 1.0f, 0.0f, 1.0f,	// Top left
			100.5f, 100.5f, 0.0f,		0.0f, 0.0f, 1.0f, 1.0f,	// Top right
			0.5f, 0.5f, 0.0f,		1.0f, 1.0f, 0.0f, 1.0f,	// Bottom right
	};
	
	// IMPORTANT Must be in counter-clockwise order
	private int[] elementArray = {
			/*
			     x     x
			 
			     x     x
			 */
			2, 1, 0, // Top right triangle
			0, 1, 3, // bottom left trangle
	};
	
	private int vaoID, vboID, eboID;
	
	private Shader defaultShader;

	public LevelEditorScene() {

	}	
	
	@Override
	public void init() {
		this.camera = new Camera(new Vector2f());
		defaultShader = new Shader("src/main/resources/assets/shaders/default.glsl");
		defaultShader.compile();
		
		// ================================================================
		// Generate VAO, VBO, and EBO buffer objects, and send to GPU
		// ================================================================
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		// Create a float buffer of vertices
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
		vertexBuffer.put(vertexArray).flip();
		
		// Create VBO upload the vertex buffer
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		
		// Create the indices and upload
		IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
		elementBuffer.put(elementArray).flip();
		
		eboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);
		
		// Add the vertex attribute pointers
		int positionsSize = 3;
		int colorSize = 4;
		int floatSizeBytes = 4;
		int vertexSizeBytes = (positionsSize + colorSize) * floatSizeBytes;
		glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
		glEnableVertexAttribArray(0);
		
		glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * floatSizeBytes);
		glEnableVertexAttribArray(1);
		
	}
	
	
	
	@Override
	public void update(float dt) {
		camera.position.x -= dt * 50.0f;
		
		defaultShader.use();
		defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
		defaultShader.uploadMat4f("uView", camera.getViewMatrix());
		defaultShader.uploadFloat("uTime", TimeUtil.getTime());

		// Bind the VAO that we're using
		glBindVertexArray(vaoID);
		
		// Enable the vertex attribute pointers
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);
		
		// Unbind everything
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
		glBindVertexArray(0);
		
		defaultShader.detach();
	}
	
}