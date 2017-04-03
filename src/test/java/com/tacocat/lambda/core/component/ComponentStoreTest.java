package com.tacocat.lambda.core.component;

import org.junit.Assert;
import org.junit.Test;

import com.tacocat.lambda.core.entity.Behavior;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.core.entity.NamedComponent;

import java.util.List;

public class ComponentStoreTest {

    private class Position {}

    private class Health {}

    private static class MockBehavior extends Behavior {
        public static final NamedComponent p1 = new NamedComponent();
        public static final NamedComponent p2 = new NamedComponent();

        MockBehavior() {
            defineComponent(p1, Position.class, new Component(Position.class));
            defineComponent(p2, Position.class, new Component(Position.class));
        }
    }

    /**
     * Test method for {@link com.tacocat.lambda.core.component.ComponentStore#add(com.tacocat.lambda.core.component.Component)}.
     */
    @Test
    public void testFind() {
        ComponentStore store = new ComponentStore();
        Component position1 = new Component(Position.class);
        Component position2 = new Component(Position.class);
        Component health = new Component(Health.class);

        store.add(position1);
        store.add(position2);
        store.add(health);

        Component[] expected = {position1, position2};
        Assert.assertArrayEquals("Components with same type are grouped together",
            expected,
            store.find(Position.class).toArray()
        );

        Component[] emptyArray = {};
        Assert.assertArrayEquals("Empty list is returned when no component of the given type has been added",
            emptyArray,
            store.find(ComponentStore.class).toArray()
        );
    }

    /**
     * Test method for {@link com.tacocat.lambda.core.component.ComponentStore#addWithName(com.tacocat.lambda.core.entity.NamedComponent, com.tacocat.lambda.core.component.Component)}.
     */
    @Test
    public void testFindByName() {
        ComponentStore store = new ComponentStore();
        Component position1 = new Component(Position.class);
        Component position2 = new Component(Position.class);
        Component position3 = new Component(Position.class);
        NamedComponent position = new NamedComponent();
        NamedComponent targetPosition = new NamedComponent();

        store.addWithName(position, position1);
        store.addWithName(position, position2);
        store.addWithName(targetPosition, position3);

        Component[] expectedPositions = {position1, position2};
        Assert.assertArrayEquals("Components with same name are grouped together",
            expectedPositions,
            store.findByName(position).toArray()
        );

        Component[] expectedTargets = {position3};
        Assert.assertArrayEquals("Components with different name, but same type, are not grouped together",
            expectedTargets,
            store.findByName(targetPosition).toArray()
        );

        Component[] emptyArray = {};
        Assert.assertArrayEquals("Empty list is returned when no component of the given name has been added",
            emptyArray,
            store.findByName(new NamedComponent()).toArray()
        );
    }

    /**
     * Test method for {@link com.tacocat.lambda.core.component.ComponentStore#remove(com.tacocat.lambda.core.component.Component)}.
     */
    @Test
    public void testRemove() {
        ComponentStore store = new ComponentStore();
        Component position1 = new Component(Position.class);
        Component position2 = new Component(Position.class);

        store.add(position1);
        store.add(position2);
        store.remove(position1);

        Component[] expected = {position2};
        Assert.assertArrayEquals("Removed components can't be found",
            expected,
            store.find(Position.class).toArray()
        );

        // Check that attempting to remove a component that doesn't exist does not throw an error
        store.remove(new Component(Position.class));
    }

    /**
     * Test method for {@link com.tacocat.lambda.core.component.ComponentStore#registerEntity(com.tacocat.lambda.core.entity.Entity)}.
     */
    @Test
    public void testRegisterEntity() {
        Entity entity = new Entity(new MockBehavior());
        ComponentStore store = new ComponentStore();

        // Register
        store.registerEntity(entity);

        Component[] positions1 = {entity.get(MockBehavior.p1)};
        Assert.assertArrayEquals("Store contains components from entity",
            positions1,
            store.findByName(MockBehavior.p1).toArray()
        );

        List<Component> storePositions = store.find(Position.class);
        Assert.assertTrue("Components can be looked up from store by type",
            storePositions.contains(entity.get(MockBehavior.p1)) &&
                storePositions.contains(entity.get(MockBehavior.p2))
        );

        // Remove
        store.remove(entity.get(MockBehavior.p1));

        Component[] emptyArray = {};
        Assert.assertArrayEquals("Entity components can be individually removed from store by name",
            emptyArray,
            store.findByName(MockBehavior.p1).toArray()
        );

        Component[] positions2 = {entity.get(MockBehavior.p2)};
        Assert.assertArrayEquals("Entity components can be individually remove from store by type",
            positions2,
            store.find(Position.class).toArray()
        );
    }

}
