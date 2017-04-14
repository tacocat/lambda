# Lambda
A game engine that enables writing effortlessly parallel, highly reusable code.

# Effortlessly parallel
Write your game logic the way you think about it. The player gets hit with a fire spell, lose health. Player steps on a spike, lose health. Player uses a potion, gain health. How can we parallelize all this logic that updates the same variable? Let Lamba handle that.

No worrying about mutexes, semaphores, atomic operations, synchronized methods. Just straight forward game logic and the confidence it'll run in parallel without race conditions or costly thread synchronization.

# Highly reusable
Write your game logic once. Just programmed a system for moving your player? Great. Use the same logic to move every object: player, enemy, particle, UI cursor.

In fact, use that code to move every object in every game you create. Share that code, and no one will have to write move() again.

# Quick Start
Clone or fork the [Starter Kit](https://github.com/tacocat/lambda-starter-kit)
 - `git clone https://github.com/tacocat/lambda-starter-kit.git`

Run your game
 - `./gradlew run`

Congrats! You are ready to go.

[Read the full Getting Started guide here.](https://github.com/tacocat/lambda-starter-kit/blob/master/README.md)

# Contributing

## Prerequisites
 - [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (8 or higher)
 - [Gradle](https://gradle.org/)  (3.4.1 or higher)

## Building
 - `./gradlew build` - build and test
 - `./gradlew assemble` - build only
 - `./gradlew test` - test only
