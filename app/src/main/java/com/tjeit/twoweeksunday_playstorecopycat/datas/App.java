package com.tjeit.twoweeksunday_playstorecopycat.datas;

public class App {

    public int rank;        //순위
    public String title;    //앱제목
    public String companyname;//제조사명
    public int userRating;  //평균평점 별몇개..
    public int price;       //구매가격
    public boolean isMine;  //내가구매한것인지.. True :설치된 항목, false : 가격표시

    //alt + insert => constructor

    public App(int rank, String title, String companyname, int userRating, int price, boolean isMine) {
        this.rank = rank;
        this.title = title;
        this.companyname = companyname;
        this.userRating = userRating;
        this.price = price;
        this.isMine = isMine;
    }
}
