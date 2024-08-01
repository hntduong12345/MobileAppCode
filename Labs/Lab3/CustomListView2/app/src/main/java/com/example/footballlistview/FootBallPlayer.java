package com.example.footballlistview;

public class FootBallPlayer {
    private String Name, BirthDay;
    private int PlayerImg, FlagImg;

    public FootBallPlayer(String name, String birthDay, int playerImg, int flagImg) {
        Name = name;
        BirthDay = birthDay;
        PlayerImg = playerImg;
        FlagImg = flagImg;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public int getPlayerImg() {
        return PlayerImg;
    }

    public void setPlayerImg(int playerImg) {
        PlayerImg = playerImg;
    }

    public int getFlagImg() {
        return FlagImg;
    }

    public void setFlagImg(int flagImg) {
        FlagImg = flagImg;
    }
}
