package com.tacocat.lambda.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tacocat.lambda.core.component.ComponentStore;
import com.tacocat.lambda.core.system.GameSystem;
import com.tacocat.lambda.core.system.action.Action;
import com.tacocat.lambda.core.system.action.ActionQueue;

/**
 * Class for handling game logic
 */
public class GameEngine {

    /**
     * Updates components based on logic from systems
     *
     * @param systems list of systems to run
     * @param components list of components to run systems with
     */
    public void update(List<GameSystem> systems, ComponentStore components) {
        // Run systems
        List<Action> actions = systems.parallelStream().flatMap(system -> system.getActionList(components).stream()).collect(Collectors.toList());

        // Create action map
        Map<String, ActionQueue> actionManager = new HashMap<String, ActionQueue>();
        for (Action action : actions) {
            // Create action queue if needed
            String queueName = action.getQueueName();
            if (!actionManager.containsKey(queueName)) {
                actionManager.put(queueName, action.startActionQueue());
            } else {
                actionManager.get(queueName).add(action);
            }
        }

        // Flush action queues
        actionManager.entrySet().parallelStream().forEach((entry) -> {
            entry.getValue().execute();
        });
    }
}
