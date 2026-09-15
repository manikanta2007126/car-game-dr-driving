# Dr. Driving - Car Game

A Java-based car driving game inspired by Dr. Driving mobile game. Navigate your car through traffic, avoid obstacles, and earn points!

## Features

- **Player Car Control**: Use LEFT and RIGHT arrow keys to steer your car
- **Dynamic Enemies**: AI-controlled cars moving down the road
- **Obstacles**: Avoid orange barriers on the road
- **Scoring System**: Earn points as you survive longer
- **Level Progression**: Game difficulty increases as you level up
- **Collision Detection**: Game ends when you hit enemies or obstacles
- **Game Over Screen**: Shows final score with option to restart (press R)

## How to Play

1. **Compile the game:**
   ```bash
   javac src/*.java
   ```

2. **Run the game:**
   ```bash
   java -cp src Main
   ```

3. **Controls:**
   - `LEFT ARROW` - Move car left
   - `RIGHT ARROW` - Move car right
   - `R` - Restart after game over

## Game Mechanics

- Your red car starts at the bottom of the screen
- Blue enemy cars approach from the top
- Orange obstacles appear randomly on the road
- Avoid all collisions to survive
- Score increases automatically over time
- Level increases every 1000 points
- Enemies spawn more frequently as levels increase

## Game Elements

### Player Car (Red)
- Controlled by arrow keys
- Located at the bottom of the screen
- Has collision detection with enemies and obstacles

### Enemy Cars (Blue)
- Move downward at varying speeds
- Respawn at the top when off-screen
- Random movement patterns

### Obstacles (Orange)
- Fixed barriers on the road
- Move downward continuously
- Cannot be avoided permanently

## Game Display

- Green road background with yellow lane markings
- White HUD showing current score and level
- Red "GAME OVER" message with final score when collision occurs

## Technical Details

- **Game Loop**: 60 FPS (frames per second)
- **Graphics**: Java Swing (JPanel with Graphics2D)
- **Input Handling**: KeyListener for real-time input
- **Threading**: Game runs on a separate thread for smooth gameplay
- **Collision Detection**: Rectangle-based bounding box collision

## Requirements

- Java 8 or higher
- No external dependencies

## Future Enhancements

- [ ] Sound effects and background music
- [ ] Multiple difficulty levels
- [ ] Power-ups and special items
- [ ] Leaderboard system
- [ ] Different car skins
- [ ] Weather effects
- [ ] Mobile platform support

## License

MIT License - Feel free to use and modify!

## Author

Created by manikanta2007126
