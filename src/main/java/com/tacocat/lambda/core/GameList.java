package com.tacocat.lambda.core;

import java.util.ArrayList;

public class GameList<E> extends ArrayList<E> {
    public void add(E ... elements) {
        for (E element : elements) {
            add(element);
        }
    }
}
