/*
This JavaFX application allows the user to play the game
Boulder, Parchment, Shears (aka Rock, Paper, Scissors)
against a fair computer opponent.
(The computer chooses randomly each game.)

Program by Alex Marty
*/

import java.util.*;
import java.io.*;
import java.text.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoulderParchmentShears extends Application
{
   //Declare fields
   private int yourBPS = 0; //0, 1, or 2 
   private int compBPS = 0; //Will get randomized to 0, 1, or 2
   private String win = "You won! Hooray!";
   private String tie = "It's a tie! So close!";
   private String lose = "You lost... Bummer!";
   private String[] bigNames = {"boulderBig", "parchmentBig", "shearsBig"};
   
   //Create the Label controls
   Label letsPlay = new Label("Let's play Boulder, Parchment, Shears!");
   Label makeSelection = new Label("Make a selection:");
   Label yourChoice = new Label("Your choice:");
   Label compChoice = new Label("Computer's choice:");
   Label winLoss = new Label("Who will win?");
   
   //Create the Button Controls
   Button submit = new Button("Submit!");
   Button playAgain = new Button("Play again!");
   
   public static void main(String[] args)
   {
      //Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage primaryStage) throws FileNotFoundException
   {
         //Create the Image and ImageView controls
      Image b = new Image("file:boulder.jpg");
      Image p = new Image("file:parchment.jpg");
      Image s = new Image("file:shears.jpg");
      ImageView boulderBig = new ImageView(b);
      ImageView boulderSmall = new ImageView(b);
      ImageView parchmentBig = new ImageView(p);
      ImageView parchmentSmall = new ImageView(p);
      ImageView shearsBig = new ImageView(s);
      ImageView shearsSmall = new ImageView(s);
      boulderBig.setFitWidth(400);
      boulderBig.setPreserveRatio(true);
      boulderSmall.setFitWidth(200);
      boulderSmall.setPreserveRatio(true);
      parchmentBig.setFitWidth(400);
      parchmentBig.setPreserveRatio(true);
      parchmentSmall.setFitWidth(200);
      parchmentSmall.setPreserveRatio(true);
      shearsBig.setFitWidth(400);
      shearsBig.setPreserveRatio(true);
      shearsSmall.setFitWidth(200);
      shearsSmall.setPreserveRatio(true);
      ImageView boulderBig2 = new ImageView(b);
      ImageView boulderSmall2 = new ImageView(b);
      ImageView parchmentBig2 = new ImageView(p);
      ImageView parchmentSmall2 = new ImageView(p);
      ImageView shearsBig2 = new ImageView(s);
      ImageView shearsSmall2 = new ImageView(s);
      boulderBig2.setFitWidth(400);
      boulderBig2.setPreserveRatio(true);
      boulderSmall2.setFitWidth(200);
      boulderSmall2.setPreserveRatio(true);
      parchmentBig2.setFitWidth(400);
      parchmentBig2.setPreserveRatio(true);
      parchmentSmall2.setFitWidth(200);
      parchmentSmall2.setPreserveRatio(true);
      shearsBig2.setFitWidth(400);
      shearsBig2.setPreserveRatio(true);
      shearsSmall2.setFitWidth(200);
      shearsSmall2.setPreserveRatio(true);
      
      //Create ListView control for user selection
      ListView<ImageView> userLV = new ListView<>();
      userLV.setPrefSize(700, 250);
      userLV.setOrientation(Orientation.HORIZONTAL);
      userLV.getItems().addAll(boulderSmall, parchmentSmall, shearsSmall);
      
      //Create VBox containers
      VBox userBox = new VBox(10); //spacing = 10
      VBox userChoiceBox = new VBox(10);
      VBox compChoiceBox = new VBox(10);
      VBox resultBox = new VBox(10);
      
      //Add the controls to the VBox's
      userBox.getChildren().addAll(letsPlay, makeSelection, userLV, submit);
      userChoiceBox.getChildren().add(yourChoice);
      compChoiceBox.getChildren().add(compChoice);
      resultBox.getChildren().addAll(winLoss, playAgain);
      
      //Set alignment
      userBox.setAlignment(Pos.CENTER);
      userChoiceBox.setAlignment(Pos.CENTER);
      userChoiceBox.setPrefWidth(450);
      userChoiceBox.setPrefHeight(450);
      compChoiceBox.setAlignment(Pos.CENTER);
      compChoiceBox.setPrefWidth(450);
      compChoiceBox.setPrefHeight(450);
      resultBox.setAlignment(Pos.CENTER);
      /*
      //Create GridPane for choice area
      GridPane choicePane = new GridPane();
      choicePane.setPrefSize(900,500);
      RowConstraints row1 = new RowConstraints();
      row1.setPercentHeight(10);
      RowConstraints row2 = new RowConstraints();
      row2.setPercentHeight(90);
      choicePane.getRowConstraints().addAll(row1,row2);
      */
      //Create HBox container for choiceBoxes
      HBox choiceBox = new HBox(10);
      choiceBox.getChildren().addAll(userChoiceBox, compChoiceBox);
      
      //Create and fill VBox for for the whole application
      VBox BPSBox = new VBox();
      BPSBox.getChildren().addAll(userBox, choiceBox, resultBox);
      
      //Create a Scene with BPSBox as its root node and get style sheet
      Scene BPSScene = new Scene(BPSBox);
      BPSScene.getStylesheets().add("BPS.css");
            
      //Add the Scene to the Stage
      primaryStage.setScene(BPSScene);
      
      //Set the state title
      primaryStage.setTitle("Boulder, Parchment, Shears");
      
      //Show the window
      primaryStage.show();
      
      //Event handler for submit Button
      submit.setOnAction(event ->
      {
         //Get index of selected item
         yourBPS = userLV.getSelectionModel().getSelectedIndex();
         //Get computer's random selection
         Random randomNumber = new Random();
         compBPS = randomNumber.nextInt(3);
         //Switch statements to display choices and results
         switch (yourBPS)
         {
            case 0:
               userChoiceBox.getChildren().add(boulderBig);
               switch (compBPS)
               {
                  case 0:
                  compChoiceBox.getChildren().add(boulderBig2);
                  winLoss.setText(tie);
                  break;
                  case 1:
                  compChoiceBox.getChildren().add(parchmentBig2);
                  winLoss.setText(lose);
                  break;
                  case 2:
                  compChoiceBox.getChildren().add(shearsBig2);
                  winLoss.setText(win);
                  break;
               }
               break;
            case 1:
               userChoiceBox.getChildren().add(parchmentBig); 
               switch (compBPS)
               {
                  case 0:
                  compChoiceBox.getChildren().add(boulderBig2); 
                  winLoss.setText(win);
                  break;
                  case 1:
                  compChoiceBox.getChildren().add(parchmentBig2);
                  winLoss.setText(tie);
                  break;
                  case 2:
                  compChoiceBox.getChildren().add(shearsBig2);
                  winLoss.setText(lose);
                  break;
               }
               break;
            case 2:
               userChoiceBox.getChildren().add(shearsBig);
               switch (compBPS)
               {
                  case 0:
                  compChoiceBox.getChildren().add(boulderBig2);
                  winLoss.setText(lose);
                  break;
                  case 1:
                  compChoiceBox.getChildren().add(parchmentBig2);
                  winLoss.setText(win);
                  break;
                  case 2:
                  compChoiceBox.getChildren().add(shearsBig2);
                  winLoss.setText(tie);
                  break;
               }
               break;
         }
      });
      
      //Event handler for reset Button
      playAgain.setOnAction(event ->
      {
         userChoiceBox.getChildren().clear();
         compChoiceBox.getChildren().clear();
         userChoiceBox.getChildren().add(yourChoice);
         compChoiceBox.getChildren().add(compChoice);
         winLoss.setText("Who will win?");
      });
      
   }
}