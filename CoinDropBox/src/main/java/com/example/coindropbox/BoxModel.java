// Student name: Jiaming Zhang
// St#: 100 379 756

package com.example.coindropbox;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoxModel {
    private char[][] pic = new char[21][21];
    private int row;
    private int col;
    private int wins = 0;

    public BoxModel(){
        try {
            fillPic(new File("TextShapes\\Tree.txt"));
            setWinningPoint();
            this.row = 0;
            this.col = (int)(Math.random()*19 + 1);


        } catch (FileNotFoundException e) {
            System.out.println("File Not found");

        }
    }

    public BoxModel(int r, int c){
        try {
            fillPic(new File("TextShapes\\Tree.txt"));
            setWinningPoint();
            this.row = r;
            this.col = c;

        } catch (FileNotFoundException e) {

            System.out.println("File Not found");

        }
    }
    public BoxModel(File f) {
        try {
            fillPic(f);
            setWinningPoint();
            this.row = 0;
            this.col = (int)(Math.random()*20 + 1);
        } catch (FileNotFoundException e) {

            System.out.println("File Not found");

        }
    }

    public void setPic(File F) {
        try {
            fillPic(F);
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }

    }



    public char[][] getPic() {
        return this.pic;
    }

    public int getWins() {
        return wins;
    }

    public int getCol() {
        return col;
    }

    public int getRow() { return row; }


    public void setPic(char[][] pic) {
        this.pic = pic;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }


    private void setWinningPoint() {
        int newRow = this.pic.length - 1;
        for (int i = 0; i < 5; i++) {
            int newCol = (int) ((19 * Math.random())+1);
            this.pic[newRow][newCol] = 'W';
        }
    }

    private void fillPic(File f) throws FileNotFoundException {


        Scanner reader = new Scanner(f);
        for (int i = 0; i < this.pic.length; i++) {
            if (reader.hasNextLine()) {
                String copy = reader.nextLine();

                for (int j = 0; j < this.pic[i].length; j++) {
                    this.pic[i][j] = copy.charAt(j);
                }
            }
        }
    }

    public void printPic() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                System.out.print(this.pic[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void singleMove(){

        if(row + 1 < 21){
            if (pic[row + 1][col] == '*') {

                if (pic[row + 1][col + 1] == '.' && pic[row + 1][col - 1] == '.') {
                    int ran = (int) (Math.random() * 2);
                    while(!randomDecider(ran)){
                        ran = (int) (Math.random() * 2);
                    }

                    if(ran == 0){

                        this.col = col - 1;
                    }else{

                        this.col = col + 1;
                    }

                } else if (pic[row + 1][col + 1] == '.' && pic[row][col + 1] != '*') {

                    this.col = col + 1;
                } else if(pic[row + 1][col - 1] == '.'&& pic[row][col - 1] != '*'){

                    this.col = col - 1;
                }
            }

            this.row ++;

            if(row < 20){
                if(pic[row + 1][col] == 'W'){
                    wins ++;
                }
            }


        }

    }



    private boolean randomDecider(int i){
        if(i == 0){
            return pic[row][col - 1] != '*';
        }else{
            return pic[row][col + 1] != '*';
        }

    }




}