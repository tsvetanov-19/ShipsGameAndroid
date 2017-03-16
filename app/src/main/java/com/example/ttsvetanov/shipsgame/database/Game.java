package com.example.ttsvetanov.shipsgame.database;

/**
 * Created by ttsvetanov on 24.02.17.
 */

public class Game {
    private int _id;
    private String date;
    private String result;
    private String turns;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTurns() {
        return turns;
    }

    public void setTurns(String turns) {
        this.turns = turns;
    }
}
