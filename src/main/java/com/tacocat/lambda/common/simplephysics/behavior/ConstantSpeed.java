package com.tacocat.lambda.common.simplephysics.behavior;

import com.tacocat.lambda.common.render.behavior.Renderable;
import com.tacocat.lambda.common.simplephysics.component.Velocity;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.entity.Behavior;
import com.tacocat.lambda.core.entity.NamedComponent;

/**
 * Adds a constant speed to any Renderable entity
 */
public class ConstantSpeed extends Behavior {
    /**
     * Velocity vector representing the constant speed
     */
    public static final NamedComponent VELOCITY = new NamedComponent();

    /**
     * @param x speed in x direction
     * @param y speed in y direction
     */
    public ConstantSpeed(float x, float y) {
        defineComponent(VELOCITY, Velocity.class,
            (entity) ->
                new Component(
                    Velocity.class,
                    Velocity.TARGET, entity.get(Renderable.TRANSFORM),
                    Velocity.X, x,
                    Velocity.Y, y
                )
        );
    }

    /**
     * @param x speed in x direction
     * @param y speed in y direction
     * @param z speed in z direction
     */
    public ConstantSpeed(float x, float y, float z) {
        defineComponent(VELOCITY, Velocity.class,
            (entity) ->
                new Component(
                    Velocity.class,
                    Velocity.TARGET, entity.get(Renderable.TRANSFORM),
                    Velocity.X, x,
                    Velocity.Y, y,
                    Velocity.Z, z
                )
        );
    }
}
