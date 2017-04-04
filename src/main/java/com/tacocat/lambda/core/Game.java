package com.tacocat.lambda.core;

import com.tacocat.lambda.common.render.Render;
import com.tacocat.lambda.common.simplephysics.SimplePhysics;
import com.tacocat.lambda.core.component.ComponentStore;
import com.tacocat.lambda.core.platform.Platform;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.core.system.GameSystem;

public class Game {
    private GameList<GameSystem> systems;
    private ComponentStore components;
    private GameEngine gameEngine;
    private RenderEngine renderEngine;
    private Window window;

    public Game(Platform platform) {
        systems = new GameList<>();
        components = new ComponentStore();
        gameEngine = new GameEngine();

        this.renderEngine = platform.getRenderEngine();
        this.window = platform.getWindow();

        addDefaultSystems();
    }

    /**
     * Adds systems common to most games to simplify user configuration
     */
    private void addDefaultSystems() {
        systems.add(
            new Render(renderEngine.getRenderQueue()),
            new SimplePhysics()
        );
    }

    /**
     * Programmatically register GameSystems and define the game's starting state
     *
     * <pre>
     * game.init((systems, entities, components) -> {
     *     systems.add(
     *         new CombatSystem()
     *     );
     *
     *     entities.add(
     *         new Entity(new Health(100), new Damage(25)),
     *         new Entity(new Health(150), new Damage(15)
     *     );
     * }
     * </pre>
     * @param initFunction
     */
    public void init(InitFunction<GameList<GameSystem>, GameList<Entity>, ComponentStore> initFunction) {
        GameList<Entity> entities = new GameList<>();
        initFunction.apply(systems, entities, components);
        entities.forEach(components::registerEntity);
    }

    public void run() {
        // Game loop
        while (!window.shouldClose()) {
            window.pollForEvents();

            gameEngine.update(systems, components);

            renderEngine.render(window);
        }
    }

    /**
     * @return GameSystems currently being used
     */
    public GameList<GameSystem> getSystems() {
        return systems;
    }

    /**
     * @return all Components in the game's current state
     */
    public ComponentStore getComponents() {
        return components;
    }

    @FunctionalInterface
    public interface InitFunction<A,B,C> {
        void apply(A a, B b, C c);
    }
}
