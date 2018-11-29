# Graze

Welcome to Graze, the king of the hill game that's simply bovine.

## Game description

In the pastures of Graze, various types of cows compete for precious patches of grass.
Cows that go without grass for too long will go hungry and return to their home planet.
The last type of cow standing wins!

## Adding a cow

Create a new class under `graze.actor.imp` that extends the `Cow` class and implements `getIcon`, `move` and `act`.

## Building Graze

Graze makes use of Gradle for all of its build steps.
You don't need to install Gradle to run the build steps, simply calling the Gradle Wrapper will do.

Run `./gradlew build` to build Graze, run the tests and drop test and coverage results in the `build/` dir.

Run `./gradle installDist` to drop Graze executables in the `build/` dir.

## Running Graze

Simple execute the Graze executable to run a session of Graze with the default settings.
To execute the game with custom configuration, _hold on to your horses, I'm working on it_.
