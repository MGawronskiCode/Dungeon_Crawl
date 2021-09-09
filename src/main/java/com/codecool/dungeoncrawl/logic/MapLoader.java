package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.elements.actors.*;
import com.codecool.dungeoncrawl.logic.elements.items.Doors;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map4.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'D':
                            cell.setType(CellType.FLOOR);
                            new Doors(cell);
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
//                            new Key(cell);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            new Spider(cell);
                            break;
                        case 'G':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Bat(cell);
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Troll(cell);
                            break;
                        case 'e':
                            cell.setType(CellType.FLOOR);
                            new Echinops(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Warrior(cell);
                            break;
                        case 'q':
                            cell.setType(CellType.getRandomDecoration());
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
