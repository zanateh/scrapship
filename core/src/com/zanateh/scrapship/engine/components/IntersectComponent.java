package com.zanateh.scrapship.engine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.zanateh.scrapship.engine.components.subcomponents.Hitbox;

public class IntersectComponent implements Component {
	public Array<Hitbox> hitboxes = new Array<Hitbox>();
}
