package com.tacocat.lambda.core;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.entity.Behavior;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.core.entity.NamedComponent;
import com.tacocat.lambda.core.platform.Platform;
import com.tacocat.lambda.core.system.GameSystem;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    // Mock outside dependencies
    private Platform platform = mock(Platform.class);
    private RenderEngine renderEngine = mock(RenderEngine.class);

    private GameSystem system1 = mock(GameSystem.class);
    private GameSystem system2 = mock(GameSystem.class);

    private class Position {}
    private static class MockBehavior extends Behavior {
        public static final NamedComponent p1 = new NamedComponent();
        public static final NamedComponent p2 = new NamedComponent();

        MockBehavior() {
            defineComponent(p1, GameTest.Position.class, new Component(GameTest.Position.class));
            defineComponent(p2, GameTest.Position.class, new Component(GameTest.Position.class));
        }
    }

    @Before
    public void before() {
        when(platform.getRenderEngine()).thenReturn(renderEngine);
        when(renderEngine.getRenderQueue()).thenReturn(mock(RenderQueue.class));
    }

    @Test
    public void defaultSystems() {
        Game game = new Game(platform);

        Assert.assertEquals("A newly created game starts with default systems",
            2,
            game.getSystems().size()
        );
    }


    @Test
    public void init() {
        Game game = new Game(platform);

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
