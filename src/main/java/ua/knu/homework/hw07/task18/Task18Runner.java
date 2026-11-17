package ua.knu.homework.hw07.task18;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task18Runner {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}
        };

        System.out.println("~ Paper Grid (1 = colored, 0 = empty) ~");
        printGrid(grid);

        List<Shape> allFoundShapes = extractAllShapes(grid);
        Set<Shape> distinctShapes = new HashSet<>(allFoundShapes);

        System.out.println("\nTotal connected shapes found on grid: " + allFoundShapes.size());
        System.out.println("Distinct shapes (up to 90-deg rotations & translation): " + distinctShapes.size());

        int index = 1;
        for (Shape shape : distinctShapes) {
            System.out.println("\n~ Distinct Shape #" + index++ + " ~");
            System.out.println(shape.renderAscii());
        }
    }

    public static List<Shape> extractAllShapes(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        List<Shape> shapes = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    List<Cell> component = bfs(grid, visited, r, c);
                    shapes.add(new Shape(component));
                }
            }
        }
        return shapes;
    }

    private static List<Cell> bfs(int[][] grid, boolean[][] visited, int startR, int startC) {
        int rows = grid.length;
        int cols = grid[0].length;
        List<Cell> cells = new ArrayList<>();
        Deque<Cell> queue = new ArrayDeque<>();

        visited[startR][startC] = true;
        Cell start = new Cell(startR, startC);
        queue.add(start);

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            cells.add(curr);

            for (int d = 0; d < 4; d++) {
                int nr = curr.getR() + DR[d];
                int nc = curr.getC() + DC[d];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                        grid[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new Cell(nr, nc));
                }
            }
        }
        return cells;
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print((val == 1 ? '#' : '.') + " ");
            }
            System.out.println();
        }
    }
}