package com.tacocat.lambda.core.platform;

import com.tacocat.lambda.core.RenderEngine;
import com.tacocat.lambda.core.Window;

/**
 * Provides support for running a game
 */
public interface Platform {
    /**
     * @return operating system window to run game in
     */
    public Window getWindow();

    /**
     * @return engine for drawing game graphics to the window
     */
    public RenderEngine getRenderEngine();
}
