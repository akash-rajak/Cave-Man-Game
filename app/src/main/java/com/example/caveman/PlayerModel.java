package com.example.caveman;

public class PlayerModel {
    private int id;
    private int player_score;

    //
    public PlayerModel(int id, int player_score) {
        this.id = id;
        this.player_score = player_score;
    }

    public PlayerModel() {

    }

    //
    @Override
    public String toString() {
        return "PlayerModel{" +
                "id=" + id +
                ", player_score=" + player_score +
                '}';
    }

    //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer_score() {
        return player_score;
    }

    public void setPlayer_score(int player_score) {
        this.player_score = player_score;
    }
}
