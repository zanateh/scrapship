package com.zanateh.scrapship.engine.helpers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.zanateh.scrapship.engine.components.HardpointComponent;
import com.zanateh.scrapship.engine.components.TransformComponent;
import com.zanateh.scrapship.engine.components.subcomponents.Hardpoint;

public class HardpointHelper {
	
	public static void attach(Hardpoint hp1, Hardpoint hp2) {
		if( hp1.attached != null ) {
			throw new RuntimeException("Cannot attach hardpoints: hardpoint " + hp1.toString() + " already attached to " + hp1.attached.toString());
		}
		if( hp2.attached != null ) {
			throw new RuntimeException("Cannot attach hardpoints: hardpoint " + hp2.toString() + " already attached to " + hp2.attached.toString());
		}
		
		hp1.attached = hp2;
		hp2.attached = hp1;
	}
	
	public static void detach(Hardpoint hp1) {
		Hardpoint hp2 = hp1.attached;
		if( hp2 == null ) {
			throw new RuntimeException("Cannot attach hardpoints: hardpoint " + hp1.toString() + " not attached.");			
		}
			
		hp1.attached = null;
		hp2.attached = null;
	}
	
	public static void detachAll(Array<Hardpoint> hardpoints) {
		for( Hardpoint hardpoint : hardpoints) {
			if(hardpoint.attached != null) {
				detach(hardpoint);
			}
		}
		
	}


	public static boolean intersect(TransformComponent tc, Hardpoint hardpoint, TransformComponent otc, Hardpoint otherHardpoint) {
		Vector2 thisPos = new Vector2(hardpoint.position);
		thisPos.rotate(tc.rotation);
		thisPos.add(tc.position);
		
		Vector2 otherPos = new Vector2(otherHardpoint.position);
		otherPos.rotate(otc.rotation);
		otherPos.add(otc.position);
		
		thisPos.sub(otherPos);
		float len2 = thisPos.len2();
		if(len2 <= hardpoint.hardpointRadius && len2 <= otherHardpoint.hardpointRadius ) {
			return true;
		}
		
		return false;
	}

	
	
}
