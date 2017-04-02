package com.tacocat.lambda.core;

/**
 * Class for handling game rendering
 */
public abstract class RenderEngine {
    /**
     * Render items from render queue to screen
     * @param window window to render game inside
     */
    public abstract void render(Window window);

    /**
     * @return reference to RenderQueue
     */
    public abstract RenderQueue getRenderQueue();
}
