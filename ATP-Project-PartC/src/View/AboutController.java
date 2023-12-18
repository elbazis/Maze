package View;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    public javafx.scene.control.Button exit;
    public javafx.scene.control.Label text;

    public void closePane() {
        Stage s = (Stage) exit.getScene().getWindow();
        s.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text.setWrapText(true);
        text.setText("""
                Hello and Welcome to Itzik and Yoav's project!
                This application allows you to generate and solve mazes using various algorithms. 
                Create random mazes of different sizes and complexities, and challenge yourself by finding the optimal 
                path from the entrance to the exit. Enjoy the visually appealing representation of mazes and interact with 
                them using keyboard or mouse controls. Whether you're a puzzle enthusiast, algorithm lover, or simply looking 
                for a fun activity, this application provides an immersive experience in the intriguing world of mazes. 
                Get ready to explore and solve mazes like never before!
                
                Maze Generating algorithms:
                    EmptyMazeGenerator - maze without walls.
                    SimpleMazeGenerator - maze with rows of paths and walls (breaking a wall in every wall row).
                    MyMazeGenerator - maze generated by binary tree algorithm.
                    
                Maze solving algorithms:
                    BFS - Breadth First Search
                    DFS - Depth First Search
                    Best First Search
                """
        );
    }
}