package ViewModel;

import Model.Direction;
import Model.IModel;
import algorithms.mazeGenerators.Maze;

import algorithms.search.AState;
import algorithms.search.*;
import javafx.scene.input.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class MyViewModel extends Observable implements Observer {
    private final IModel model;

    public MyViewModel(IModel model) {
        this.model = model;
        this.model.assignObserver(this); //Observe the Model for it's changes
    }
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
    public int[][] getMaze(){
        Maze maze =  model.getMaze();
        if (maze != null) {
            int rows = maze.getRows();
            int cols = maze.getColumns();
            int[][] mazeArray = new int[rows][cols];
            for (int row = 0; row < rows; ++row)
                for (int column = 0; column < cols; ++column) {
                    mazeArray[row][column] = maze.containsPath(row, column) ? 0 : 1;
                }
            return mazeArray;
        }
        return null;
    }
    public boolean containsPath(int row, int col){
        return model.containsPath(row, col);
    }
    public int getPlayerRow(){
        return model.getPlayerRow();
    }
    public int getPlayerCol(){
        return model.getPlayerCol();
    }
    public void generateMaze(int rows, int cols){
        model.generateMaze(rows, cols);
    }
    public ArrayList<int[]> getSolution(){
        ArrayList<int[]> solutionPath = new ArrayList<>();
        for(AState step : model.getSolution().getSolutionPath()){
            int row = ((MazeState)step).getRow(); int col = ((MazeState)step).getColumn();
            solutionPath.add(new int[]{row, col});
        }
        return solutionPath;
    }
    public void solveMaze(){
        model.solveMaze();
    }
    public void movePlayer(KeyEvent keyEvent){
        Direction direction;
        switch (keyEvent.getCode()){
            case NUMPAD8, W -> direction = Direction.UP;
            case NUMPAD2, S -> direction = Direction.DOWN;
            case NUMPAD4, A -> direction = Direction.LEFT;
            case NUMPAD6, D -> direction = Direction.RIGHT;
            case NUMPAD7, Q -> direction = Direction.UP_LEFT;
            case NUMPAD9, E -> direction = Direction.UP_RIGHT;
            case NUMPAD1, Z -> direction = Direction.DOWN_LEFT;
            case NUMPAD3, C -> direction = Direction.DOWN_RIGHT;
            default -> {return;}
        }
        model.updatePlayerLocation(direction);
    }
    public void setPlayerLocation(int row, int col){
        if(containsPath(row, col))
            model.movePlayer(row, col);
    }
    public void openMaze(File maze){
        model.loadMaze(maze);
    }
    public void saveMaze(File maze){model.saveMaze(maze);}
    public void setMaze(Maze maze){model.setMaze(maze);}
    public void isSolved(){model.isSolved();}
}