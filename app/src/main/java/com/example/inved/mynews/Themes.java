package com.example.inved.mynews;

public enum Themes {

    HOME("home",false,"TOP STORIES"),MOSTPOPULAR("all-sections",true,"MOST POPULAR"),TECHNOLOGY("technology",false,"TECH"),SCIENCE("science",false,"SCIENCE"),SPORTS("sports",false,"SPORTS"),
    FOOD("food",false,"FOOD"),TRAVEL("travel",false,"TRAVEL"),WORLD("world",false,"WORLD");

    String mName;
    boolean mIsMostPopular;
    String mTitle;

    Themes(String name, boolean isMostPopular, String title){
         mName =name;
         mIsMostPopular=isMostPopular;
         mTitle = title;

    }

    public String getName() {
        return mName;
    }

    public boolean getIsMostPopular() {
        return mIsMostPopular;
    }

    public String getTitle() {
        return mTitle;
    }
}
