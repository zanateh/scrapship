package com.zanateh.scrapship.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import com.zanateh.scrapship.engine.IShipControl;
import com.zanateh.scrapship.engine.ShipControlVisitor;
import com.zanateh.scrapship.engine.components.PlayerControlComponent;
import com.zanateh.scrapship.engine.components.ShipComponent;

public class PlayerControlSystem extends IteratingSystem {

	private Array<Entity> playerControlledEntities = new Array<Entity>();
	
	private ComponentMapper<ShipComponent> shipMapper = ComponentMapper.getFor(ShipComponent.class);
	private ComponentMapper<PlayerControlComponent> playerControlMapper = ComponentMapper.getFor(PlayerControlComponent.class);
	
	public PlayerControlSystem() {
		super( Family.all(PlayerControlComponent.class).get());		
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		for(Entity entity : playerControlledEntities) {
			PlayerControlComponent pcc = playerControlMapper.get(entity);
			pcc.shipControl.visit(entity);
		}
		
		playerControlledEntities.clear();
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		playerControlledEntities.add(entity);

	}

}
