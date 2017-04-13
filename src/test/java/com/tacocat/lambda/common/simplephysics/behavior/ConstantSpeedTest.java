package com.tacocat.lambda.common.simplephysics.behavior;

import com.tacocat.lambda.common.render.behavior.Renderable;
import com.tacocat.lambda.common.render.component.Transform;
import com.tacocat.lambda.common.simplephysics.component.Velocity;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.ComponentStore;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.graphics.Graphic;
import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

public class ConstantSpeedTest {
    @Test
    public void testConstantSpeed() {
        Entity entity = new Entity(
            new ConstantSpeed(1f, 2f, 3f),
            new Renderable(Mockito.mock(Graphic.class))
        );
        entity.registerWith(new ComponentStore());

        Component transform = entity.get(Renderable.TRANSFORM);
        Component constantVelocity = entity.get(ConstantSpeed.VELOCITY);

        Assert.assertEquals("The constant speed velocity points at the entity's transform component",
            transform,
            constantVelocity.get(Velocity.TARGET)
        );

        Float[] actualPosition = {
            constantVelocity.get(Transform.X),
            constantVelocity.get(Transform.Y),
            constantVelocity.get(Transform.Z)
        };
        Float[] expectedPosition = { 1f, 2f, 3f };
        Assert.assertArrayEquals("The (x,y,z) values given to the constructor are used in the velocity vector",
            expectedPosition,
            actualPosition
        );
    }
}
