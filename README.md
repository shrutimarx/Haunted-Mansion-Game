# Haunted Mansion Game

This project implements a text-based command-line adventure game based on Scooby Doo, where the player must escape from a haunted mansion. The game involves exploring rooms, encountering monsters, and collecting Snacks to boost courage.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Setup](#setup)
- [Usage](#usage)

## Overview

The project consists of several Java files representing different aspects of the game:

- `Room.java`: Defines the Room class and its attributes.
- `Monster.java`: Defines the Monster class and its subclasses.
- `MinerFortyNiner.java`, `Ghost.java`, `Ghoul.java`: Subclasses of Monster with specific behaviors.
- `Player.java`: Represents the player exploring the mansion.
- `HauntedMansion.java`: Represents the mansion layout and game logic.
- `HauntedHelper.java`: Utility class to assist with mansion creation and visualization.
- `MansionDriver.java`: Driver class to run the game.

## Features

- Object-oriented design with inheritance and polymorphism.
- Command-line interface for text-based interaction.
- Randomized placement of monsters and Scooby Snacks in the mansion.
- Goal-oriented gameplay: Escape the mansion before running out of courage.

## Setup

1. Make sure you have Java installed on your system.
2. Clone this repository to your local machine:

git clone https://github.com/your-username/scooby-doo-haunted-mansion.git
