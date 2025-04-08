/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
// MazeSolver by Lucas Huang

import java.util.ArrayList;
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
        MazeCell current = maze.getStartCell();
        Stack<MazeCell> explore = new Stack<MazeCell>();
        explore.push(current);
        while (!explore.isEmpty()) {
            current.setExplored(true);
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            int row = current.getRow();
            int col = current.getCol();
            if (maze.isValidCell(row - 1, col)) {
                explore.push(setChild(row - 1, col, current));
            }
            if (maze.isValidCell(row, col + 1)) {
                explore.push(setChild(row , col + 1, current));
            }
            if (maze.isValidCell(row + 1, col)) {
                explore.push(setChild(row + 1, col, current));
            }
            if (maze.isValidCell(row, col - 1)) {
                explore.push(setChild(row, col - 1, current));
            }
            current = explore.pop();
        }
        return null;
    }

    public MazeCell setChild(int row, int col, MazeCell current) {
        MazeCell child = maze.getCell(row, col);
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
