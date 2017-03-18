package com.example.ttsvetanov.shipsgame.database;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by ttsvetanov on 28.02.17.
 */

public class Ships implements Serializable{
    private int _id;

    //0 = does not exist
    //1 - 5 = ship size
    private int ship1;
    private int ship2;
    private int ship3;
    private int ship4;
    private int ship5;


    //0 horisontal, 1 vertical
    private int ship1_orientation;
    private int ship2_orientation;
    private int ship3_orientation;
    private int ship4_orientation;
    private int ship5_orientation;
    //
    private int shipSquaresTotal;
    private int maxGameShots;

    public Ships(int s1, int s2, int s3, int s4, int s5, boolean s1o, boolean s2o, boolean s3o, boolean s4o, boolean s5o) {
        setShip1(s1);
        setShip2(s2);
        setShip3(s3);
        setShip4(s4);
        setShip5(s5);

        ship1_orientation = (s1o) ? 1 : 0;
        ship2_orientation = (s2o) ? 1 : 0;
        ship3_orientation = (s3o) ? 1 : 0;
        ship4_orientation = (s4o) ? 1 : 0;
        ship5_orientation = (s5o) ? 1 : 0;

        setMaxGameShots();
        setShipSquaresTotal(sumShipSquaresTotal());

    }

    public Ships() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getMaxGameShots() {
        setMaxGameShots();
        return maxGameShots;
    }

    public void setMaxGameShots() {
        Random rand = new Random();
        this.maxGameShots = getShipSquaresTotal() + rand.nextInt(12);
//        return maxGameShots;
    }

    public int sumShipSquaresTotal() {
        int total = getShip1()+ getShip2()+getShip3()+getShip4()+getShip5();
        setShipSquaresTotal(total);
        return total;
    }

    public int getShipSquaresTotal() {
        return shipSquaresTotal;
    }


    public void setShipSquaresTotal(int value) {
        this.shipSquaresTotal = value;
    }

    public int getShip1_orientation() {
        return ship1_orientation;
    }

    public void setShip1_orientation(int ship1_orientation) {
        this.ship1_orientation = ship1_orientation;
    }

    public int getShip2_orientation() {
        return ship2_orientation;
    }

    public void setShip2_orientation(int ship2_orientation) {
        this.ship2_orientation = ship2_orientation;
    }

    public int getShip3_orientation() {
        return ship3_orientation;
    }

    public void setShip3_orientation(int ship3_orientation) {
        this.ship3_orientation = ship3_orientation;
    }

    public int getShip4_orientation() {
        return ship4_orientation;
    }

    public void setShip4_orientation(int ship4_orientation) {
        this.ship4_orientation = ship4_orientation;
    }

    public int getShip5_orientation() {
        return ship5_orientation;
    }

    public void setShip5_orientation(int ship5_orientation) {
        ship5_orientation = ship5_orientation;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getShip1() {
        return ship1;
    }

    public void setShip1(int ship1) {
        this.ship1 = ship1;
    }

    public int getShip2() {
        return ship2;
    }

    public void setShip2(int ship2) {
        this.ship2 = ship2;
    }

    public int getShip3() {
        return ship3;
    }

    public void setShip3(int ship3) {
        this.ship3 = ship3;
    }

    public int getShip4() {
        return ship4;
    }

    public void setShip4(int ship4) {
        this.ship4 = ship4;
    }

    public int getShip5() {
        return ship5;
    }

    public void setShip5(int ship5) {
        this.ship5 = ship5;
    }


}
