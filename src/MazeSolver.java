/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
// MazeSolver by Lucas Huang

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        // Creates ArrayList of MazeCells
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        // Initialize the variable to track the current cell
        MazeCell current = maze.getEndCell();
        // Makes it so that while you can backtrack you add it to the solution from start to end
        while (current != null) {
            solution.add(0, current);
            current = current.getParent();
        }
        // Return the ArrayList
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Creates and initializes stack for DFS
        Stack<MazeCell> explore = new Stack<MazeCell>();
        // Initializes the variable used to keep track of cell you are on
        MazeCell current = maze.getStartCell();
        current.setExplored(true);
        explore.push(current);
        // Makes it so that the loop will continue until it runs out of possible areas to explore
        while (!explore.isEmpty()) {
            // Sets the current cell to the one on top of the stack
            current = explore.pop();
            // If reached the end returns the solution
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            // Creates variable for row and col values
            int row = current.getRow();
            int col = current.getCol();
            // Checks to see if you can explore north and adds to stack
            if (maze.isValidCell(row - 1, col)) {
                explore.push(setChild(row - 1, col, current));
            }
            // East
            if (maze.isValidCell(row, col + 1)) {
                explore.push(setChild(row , col + 1, current));
            }
            // South
            if (maze.isValidCell(row + 1, col)) {
                explore.push(setChild(row + 1, col, current));
            }
            // West
            if (maze.isValidCell(row, col - 1)) {
                explore.push(setChild(row, col - 1, current));
            }
        }
        // If no solution returns null;
        return null;
    }

    // Sets up all the children of that cell
    public MazeCell setChild(int row, int col, MazeCell current) {
        MazeCell child = maze.getCell(row, col);
        // Makes them already explored and sets the parent to the correct one
        child.setExplored(true);
        child.setParent(current);
        return child;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Creates and initializes queue for BFS
        Queue<MazeCell> explore = new LinkedList<MazeCell>();
        // Initialize variable to keep track of current cell
        MazeCell current = maze.getStartCell();
        current.setExplored(true);
        explore.add(current);
        // Same as DFS except with a queue instead of stack
        while (!explore.isEmpty()) {
            current = explore.remove();
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            int row = current.getRow();
            int col = current.getCol();
            if (maze.isValidCell(row - 1, col)) {
                explore.add(setChild(row - 1, col, current));
            }
            if (maze.isValidCell(row, col + 1)) {
                explore.add(setChild(row , col + 1, current));
            }
            if (maze.isValidCell(row + 1, col)) {
                explore.add(setChild(row + 1, col, current));
            }
            if (maze.isValidCell(row, col - 1)) {
                explore.add(setChild(row, col - 1, current));
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
