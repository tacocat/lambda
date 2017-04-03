package com.tacocat.lambda.core;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.entity.Behavior;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.core.entity.NamedComponent;
import com.tacocat.lambda.core.platform.Platform;
import com.tacocat.lambda.core.system.GameSystem;

import org.junit.Test;
import org.junit.Assert;

public class GameTest {

    private class TestPlatform implements Platform {

        @Override
        public Window getWindow() {
            return null;
        }

        @Override
        public RenderEngine getRenderEngine() {
            return null;
        }
    }

    private class Position {}

    private static class MockBehavior extends Behavior {
        public static final NamedComponent p1 = new NamedComponent();
        public static final NamedComponent p2 = new NamedComponent();

        MockBehavior() {
            defineComponent(p1, GameTest.Position.class, new Component(GameTest.Position.class));
            defineComponent(p2, GameTest.Position.class, new Component(GameTest.Position.class));
        }
    }

    private class MockSystem1 extends GameSystem {
        @Override
        protected void update() {}
    }

    private class MockSystem2 extends GameSystem {
        @Override
        protected void update() {}
    }

    @Test
    public void init() throws Exception {
        Game game = new Game(new TestPlatform());

        GameSystem system1 = new MockSystem1();
        GameSystem system2 = new MockSystem2();
        Behavior behavior = new MockBehavior();
        Entity entity = new Entity(behavior);

        game.init((systems, entities, components) -> {
            systems.add(
                system1,
                system2
            );

            entities.add(
                entity
            );
        });

        Assert.assertTrue("All systems added during init are present in the game",
            game.getSystems().contains(system1) && game.getSystems().contains(system2));

        Component[] positions = {entity.get(MockBehavior.p1)};
        Assert.assertArrayEquals("Store contains components from entity added during init",
            positions,
            game.getComponents().findByName(MockBehavior.p1).toArray()
        );
    }

}
