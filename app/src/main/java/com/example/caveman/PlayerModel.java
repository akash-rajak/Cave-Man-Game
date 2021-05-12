package com.example.caveman;

public class PlayerModel {
    // created three variable required
    private int id;
    private int level_no;
    private String player_score;

    //created parametrized constructor
    public PlayerModel(int id,int level_no, String player_score) {
        this.id = id;
        this.level_no = level_no;
        this.player_score = player_score;
    }

    // created void constructor
    public PlayerModel() {

    }

    //created toString method
    @Override
    public String toString() {
        return "Player No. : " + id + "      |      Level No. : " + level_no + "      |      Status : " + player_score;
    }

    //created getter and setter for three variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel_no() {
        return level_no;
    }

    public void setLevel_no(int id) {
        this.level_no = level_no;
    }

    public String getPlayer_score() {
        return player_score;
    }

    public void setPlayer_score(String player_score) {
        this.player_score = player_score;
    }
}
