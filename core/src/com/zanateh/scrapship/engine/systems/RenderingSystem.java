package com.zanateh.scrapship.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.zanateh.scrapship.camera.CameraManager;
import com.zanateh.scrapship.engine.RenderVisitor;
import com.zanateh.scrapship.engine.components.RenderComponent;
import com.zanateh.scrapship.engine.components.TransformComponent;

public class RenderingSystem extends IteratingSystem {

	private Array<Entity> renderQueue;
	private SpriteBatch batch;
	private CameraManager camManager;
	
	private RenderVisitor shipRenderVisitor = new RenderVisitor();
	
	public RenderingSystem(SpriteBatch batch, CameraManager camManager) {
		super(Family.all(RenderComponent.class).get());
		renderQueue = new Array<Entity>();
		
		this.batch = batch;
		this.camManager = camManager;
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camManager.setupRenderCamera();
		
		batch.enableBlending();
		batch.begin();
		
		for(Entity entity : renderQueue) {
			shipRenderVisitor.visit(entity, batch, delta);
		}
		batch.end();
		
		camManager.finalizeRender();
		
		renderQueue.clear();
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}
	
}
