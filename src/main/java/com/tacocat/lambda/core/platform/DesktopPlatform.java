package com.tacocat.lambda.core.platform;

import com.tacocat.lambda.core.RenderEngine;
import com.tacocat.lambda.core.Window;
import com.tacocat.lambda.graphics.OpenGLEngine;

/**
 * Support for Windows, Mac, Linux
 */
public class DesktopPlatform implements Platform {

    /**
     *
     */
    private Window window;

    /**
     *
     */
    private RenderEngine renderEngine;

    /**
     * Use default window and OpenGL rendering
     */
    public DesktopPlatform() {
        this.window = new Window(640, 480, "Lambda Engine");
        this.renderEngine = new OpenGLEngine();
    }

    /**
     * @param window custom window settings
     */
    public DesktopPlatform(Window window) {
        this.window = window;
        this.renderEngine = new OpenGLEngine();
    }

    @Override
    public Window getWindow() {
        return window;
    }

    @Override
    public RenderEngine getRenderEngine() {
        return renderEngine;
    }
}
