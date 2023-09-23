package com.example.coindropbox;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AnimatedGame extends Application {
    private Pane root;
    private final BoxModel model = new BoxModel();
    private final ArrayList<Button> buttons = new ArrayList<>();
    private final Circle coin = new Circle(20);
    private final CoinAnimation animation = new CoinAnimation();
    private int coins = 10;
    private final Text winsT = new Text(20,100,"Wins: " + model.getWins() * 10);
    private final Text coinsT = new Text(20,120,"Coins: " + coins);



    public static void main(String[] args) {
        launch(args);
    }
    public static class Triangle{

        private final Polygon triangle;

        public Triangle(double x,double y){
            this.triangle = new Polygon(x*50, y*50 + 50, x*50 + 50.0, y*50 + 50, x*50 + 25 , y*50 - (Math.sqrt(3) * 25) + 50);
        }

        public Polygon getTriangle() {
            return this.triangle;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Rectangle backGround = new Rectangle(1100,1100);
        backGround.setFill(Color.BLACK);
        Rectangle colorBack = new Rectangle(50,0,950,1000);
        colorBack.setFill(Color.rgb(94,90,91));


        root.getChildren().addAll(backGround,colorBack);
        setBox(root);
        setButton(root);

        winsT.setFill(Color.TAN);
        winsT.setFont(new Font(20));
        coinsT.setFill(Color.TAN);
        coinsT.setFont(new Font(20));
        root.getChildren().addAll(winsT,coinsT);


        Scene scene = new Scene(root,1050,1050);
        primaryStage.setTitle("CoinDropBox");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void setBox(Pane root){

        char[][] structure = model.getPic();

        for(int i = 1; i < 21; i ++ ){
            for (int j = 1; j < 20; j ++){
                if(structure[i][j] == '*'){
                    Polygon tri = new Triangle(j, i).getTriangle();
                    tri.setFill(Color.YELLOW);
                    root.getChildren().add(tri);

                }else if(structure[i][j] == 'W'){
                    Rectangle winSquare = new Rectangle(j*50,i*50,  50,  50);
                    winSquare.setFill(Color.ORANGE);
                    root.getChildren().add(winSquare);

                }
            }

        }
    }

    private void setButton(Pane root){
        for (int i = 1; i < 20; i ++){
            Button b = new Button(Integer.toString(i));
            b.setId(Integer.toString(i));
            b.setLayoutX(i*50 + 15);
            b.setLayoutY(5);

            buttons.add(b);
            root.getChildren().add(b);

        }

        for (Button b:
                buttons) {
            b.setOnAction(new handler());

        }

    }

    private class handler implements EventHandler<ActionEvent>{


        @Override
        public void handle(ActionEvent actionEvent) {
            if(coins > 0) {
                coins--;
                root.getChildren().remove(coin);
                Button b = (Button) actionEvent.getSource();

                int newVal = Integer.parseInt(b.getId());
                model.setRow(0);
                model.setCol(newVal);
                coin.setCenterX((newVal) * 50 + 25);
                coin.setCenterY(0);

                coin.setFill(Color.BURLYWOOD);
                root.getChildren().add(coin);
                animation.start();

                coinsT.setText("Coins: " + coins);
            }

        }

    }

    private class CoinAnimation extends AnimationTimer {
        @Override
        public void handle(long now) {
            model.singleMove();

            int x = model.getCol()*50 + 25;
            int y = model.getRow() * 50;

            coin.setCenterX(x);
            coin.setCenterY(y);

            winsT.setText("Wins: "  + model.getWins() * 10);

        }
    }
}
