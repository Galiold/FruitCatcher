package com.KahaniM.Game;

import java.io.*;

/**
 * Created by A. Goldani on 6/28/17.
 * FinalProject-MenuAdded
 */

public class FileStream {
    private static final String INPUT_FILE_NAME = "score.txt";
    Player[] players;
    long[] scores;
    String line = null;
    int Switch = 0;
    int lineCount = 0;


    public FileStream() {
        try {
            BufferedReader scoreReader = null;
            try {

                scoreReader = new BufferedReader(new FileReader(INPUT_FILE_NAME));
                for (lineCount = 0; scoreReader.readLine() != null; lineCount++) ;

            } finally {
                try {
                    scoreReader.close();
                } catch (Exception e) {
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println(ex);
        }

//        System.out.println("lc in cons "+lineCount);

        players = new Player[lineCount + 1];
        read("score.txt");
    }

    public void setPlayer(Player pl) {
        for (int i = 0; i < lineCount; i++) {
            if (players[i].getName().equals(pl.getName())) {
//                lineCount--;
                return;
            }
        }
        System.out.println("lc " + lineCount + pl.getName());
        players[lineCount++] = pl;

        for (int i = 0; i < lineCount; i++) {
            System.out.println(players[i].getName() + "," + players[i].getPlayerScore());
        }

    }


    public void setPlayer(String name, int score) {
        for (int i = 0; i < lineCount; i++) {
            if (players[i].getName().equals(name)) {
                if (players[i].getPlayerScore() < score)
                    players[i].setPlayerScore(score);
                return;
            }
        }
    }


    public void read(String aInputFileName) {
        File file = new File(aInputFileName);
        String fileLine;
        int comaIndex;
        try {
            BufferedReader scoreReader = null;
            try {
                scoreReader = new BufferedReader(new FileReader(file));
                line = "";
                for (int i = 0; i < lineCount; i++) {
                    players[i] = new Player("");
                    fileLine = scoreReader.readLine();
                    comaIndex = fileLine.indexOf(",");
                    players[i].setName(fileLine.substring(0, comaIndex));
                    players[i].setPlayerScore(Integer.parseInt(fileLine.substring(comaIndex + 1)));
                }

            } finally {
                try {
                    scoreReader.close();
                } catch (Exception e) {
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    public void write() {

//        System.out.println("lc in write "+ lineCount);
        sort();
        try {
            PrintWriter scoreWriter = null;
            try {
                scoreWriter = new PrintWriter(new BufferedWriter(new FileWriter(INPUT_FILE_NAME)));
                for (int i = 0; i < lineCount; i++) {
                    scoreWriter.println(players[i].getName() + "," + players[i].getPlayerScore());
                }

            } finally {
                try {
                    scoreWriter.close();
                } catch (Exception e) {
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public void sort() {
        Player smallerScore;
        System.out.println("sort, len"+players.length);
        for (int i = 0; i < lineCount - 1; i++) {
            int index = i;
            for (int j = i + 1; j < lineCount; j++) {
                //System.out.println("i="+i+"j="+j);
                if (players[j].getPlayerScore() > players[index].getPlayerScore())
                    index = j;
            }
            smallerScore = players[index];
            players[index] = players[i];
            players[i] = smallerScore;

        }

    }


    public String printFile(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<lineCount;i++){
            stringBuilder.append(players[i].getName() + " "+players[i].getPlayerScore()+"\n");
        }
        return stringBuilder.toString();

    }


}
