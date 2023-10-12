package demos;

import java.awt.*;
import java.util.Set;
import java.util.Queue;
import java.util.Stack;

public class Maze {

    public static void main(String[] args) {
        Maze maze = new Maze(MAZE1);
        Maze maze1 = new Maze(MAZE2);
        solveMazes(new MazeSolver() {
            @Override
            public String getName() {
                return "BreadthFirstSearch";
            }

            @Override
            public Maze solve(String[] map) {
                return breadthFirstSearch(map);
            }
        });

//        solveMazes(new MazeSolver() {
//            @Override
//            public String getName() {
//                return "DepthFirstSearch";
//            }
//
//            @Override
//            public Maze solve(String[] map) {
//                return depthFirstSearch(map);
//            }
//        });
    }

    private static Maze breadthFirstSearch(String[] map) {
        Maze maze = new Maze(map);
        Queue<Step> toVisit = new java.util.LinkedList<>();
        Set<Point> visited = new java.util.HashSet<>();
        toVisit.add(new Step(null, maze.getStartPoint(), 0));
        while (!toVisit.isEmpty()) {
            Step next = toVisit.remove();
            if (!visited.contains(next.point())) {
                maze.setVisited(next.point());
                if (next.point().equals(maze.getGoalPoint())) {
                    maze.markPath(next);
                    return maze;
                }
                visited.add(next.point());
                for (Point neighbor : NEIGHBORS) {
                    Point neighborPoint = new Point(next.point().x + neighbor.x, next.point().y + neighbor.y);
                    if (maze.isValidAndFree(neighborPoint)) {
                        toVisit.add(new Step(next, neighborPoint, next.cost() + 1));
                    }
                }
            }
        }
        return null;
    }

    private static Maze depthFirstSearch(String[] map) {
        Maze maze = new Maze(map);
        Stack<Step> toVisit = new Stack<>();
        Set<Point> visited = new java.util.HashSet<>();
        toVisit.add(new Step(null, maze.getStartPoint(), 0));

        return null;

    }

    public static final String[] MAZE1 = {
            "           ",
            " ### ##### ",
            "#  #       ",
            "#    ######",
            "  ## ##   #",
            "  ##    #  "
    };

    ;
    public static final String[] MAZE2 = {
            " #      # ##  ##    ## #   ##  #   #    # # ##  #  ## ##  ",
            "   ####    ##  #### ## ###  ##   # # ##   #  ## # ##     #",
            " # #    ##    ##        ## ##### ### ####   ##     ## ####",
            " # ######## #  ###### ###   ## #     # ### ##### ###      ",
            "#   ##    ####  ##      ## ##    ##    # # # ##      #####",
            "###  ####  # ## ##### ###   ### ##  #### # #  ## ### ### #",
            " ###  # ##   #    ##    ## ##   ### # ##     ###   ### #  ",
            " # ##      #   ##  ####  # ####   #   ###### # # #####   #",
            "   ########### ###  #### #  ##  ### ###  #   #     # ## ##",
            "##   # # ## ## # ##   #    #### ###   ## ###   ##### ##   ",
            "####           #    #   ##  ##    ## ##  # ### # ##     ##",
            "     ## # # ## ###### # #  ##### ### ### #  ##      # #   ",
            "# # ##  ######### ##  # ##  #### ##   ## # ##  # # ## ####",
            "#######  ##   ##     ##  ##  # #  ###  # # #  ######      ",
            "############# #  # #  # #### # # ## ## # #   ## # ## ## ##",
            "# # ## # ## # ## # ## ### #    #  # ## # # #       #    ##",
            "  # ## #       #########    ##   ## ##     ######### ###  ",
            "#    # ###### ##  #    ###########     ## #########  # # #",
            "  ##           ## #### ##     ## # ## ######              ",
            "###  # # # # #  # # #  ######      ##   ###  #### # ### ##",
            "###### # ###### # # ## #  #   # # ##  ### ## # ########## ",
            "       # #             ## #######  ##            # ##  ## ",
            " ## ## # # ### # ## ## #   ###    ##  ## # ####### ### #  ",
            "### ################## # #   #### ## #####               #",
            "#   #         # #####  # # #       #  ##   #### ####### ##",
            "  # # ##### #    ## ##   # ## # # #####  #   ##   ###     ",
            "# # ###   ###### #   # ### #### #     ## ## ##### # ## # #",
            "  # ##  #          #     # #    # ## ##  #      #    # #  "
    };

    public static final Point[] NEIGHBORS = {
            new Point(1, 0),
            new Point(0, 1),
            new Point(0, -1),
            new Point(-1, 0)
    };

    private final MazeState[][] maze;
    private final Point start;
    private final Point goal;
    private final int elements;
    private int visited;

    public Maze(String[] maze) {
        if (maze == null)
            throw new IllegalArgumentException("Maze is null");
        int height = maze.length;
        if (height <= 1)
            throw new IllegalArgumentException("Maze is not high enough");
        int width = maze[0].length();
        if (width <= 1)
            throw new IllegalArgumentException("Maze is not wide enough");
        this.maze = new MazeState[maze.length][width];
        for (int row = 0; row < maze.length; ++row) {
            if (maze[row].length() != width)
                throw new IllegalArgumentException("Maze is not a rectangle");
            for (int column = 0; column < width; ++column) {
                this.maze[row][column] = maze[row].charAt(column) == ' ' ? MazeState.FREE : MazeState.BLOCKED;
            }
        }
        start = new Point(0, 0);
        if (!isValidAndFree(start))
            throw new IllegalArgumentException("Starting point is not valid");
        goal = new Point(this.maze[0].length - 1, this.maze.length - 1);
        if (!isValidAndFree(goal))
            throw new IllegalArgumentException("Goal point is not valid");
        elements = width * height;
        visited = 0;
    }

    public boolean isValidAndFree(Point point) {
        return validCoordinate(point) && maze[point.y][point.x] == MazeState.FREE;
    }

    private boolean validCoordinate(Point point) {
        return point.y >= 0 && point.y < maze.length && point.x >= 0 && point.x < maze[point.y].length;
    }

    private static void execute(MazeSolver solver, String[] maze) {
        Maze maze1 = solver.solve(maze);
        System.out.println(maze1 == null ? "UNSOLVABLE MAZE!" : maze1);
        System.out.printf("Efficiency of >> %s << = %.1f%% (lower is better)\n\n", solver.getName(), 100.0 * maze1.getEfficiency());
        System.out.println("PRESS ENTER to continue");
        try {
            System.in.read();
        } catch (Exception ignored) {
        }
    }

    public static void solveMazes(MazeSolver solver) {
        execute(solver, MAZE1);
        execute(solver, MAZE2);
    }

    public Point getStartPoint() {
        return start;
    }

    public Point getGoalPoint() {
        return goal;
    }

    /**
     * Determine the efficiency of the algorithm by returning the #visitid in relation to #elements in the maze.
     * @return visited / elements
     */
    public float getEfficiency() {
        return (float)visited / (float)elements;
    }

    /**
     * Mark all step points as part of the path
     *
     * @param step last step of the path
     */
    public void markPath(Step step) {
        while (step != null) {
            setPath(step.point());
            step = step.previous();
        }
    }

    public void setPath(Point point) {
        if (!validCoordinate(point))
            throw new IllegalArgumentException("Invalid point");
        if (maze[point.y][point.x] != MazeState.VISITED)
            throw new IllegalStateException("PATH can only be assigned to a VISITED cell@" + point);
        maze[point.y][point.x] = MazeState.PATH;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (MazeState[] mazeStates : maze) {
            for (MazeState mazeState : mazeStates) {
                String colorChar = switch (mazeState) {
                    case FREE -> " ";
                    case BLOCKED -> "" + (char) 27 + "[90m#";
                    case PATH -> "" + (char) 27 + "[91mo";
                    case VISITED -> "" + (char) 27 + "[32m.";
                };
                builder.append(colorChar);
            }
            builder.append('\n');
        }
        builder.append("" + (char) 27 + "[0m");
        return builder.toString();
    }

    public void setVisited(Point point) {
        if (!validCoordinate(point))
            throw new IllegalArgumentException("Invalid point column");
        if (maze[point.y][point.x] != MazeState.FREE)
            throw new IllegalStateException("VISITED can only be assigned to a FREE cell @" + point);
        maze[point.y][point.x] = MazeState.VISITED;
        ++visited;
    }

    public enum MazeState {
        FREE,
        BLOCKED,
        VISITED,
        PATH
    }

    public interface MazeSolver {
        public String getName();
        public Maze solve(String[] map);
    }

    public record Step(Step previous, Point point, int cost, int estimate) implements Comparable<Step> {
        public Step(Step previous, Point point, int cost) {
            this(previous, point, cost, 0);
        }

        @Override
        public int compareTo(Step other) {
            return (cost + estimate) - (other.cost + other.estimate);
        }

        @Override
        public String toString() {
            return "{" + (previous == null ? "" : previous.point.x + ":" + previous.point.y) + "->" + point.x + ":" + point.y + '=' + cost + '+' + estimate + '}';
        }

    }

}

