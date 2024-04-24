package com.example.login;

public class Show {
    private String showName;
    private String status;
    private int numOfSeasons;

    public Show(String showName,String status, int numOfSeasons)
    {
        this.showName = showName;
        this.status = status;
        this.numOfSeasons = numOfSeasons;
    }

    public String getShowName()
    {
        return showName;
    }

    public String getStatus()
    {
        return status;
    }

    public int getNumOfSeasons()
    {
        return numOfSeasons;
    }

    public void setShowName(String showName)
    {
        this.showName = showName;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setNumOfSeasons(int numOfSeasons)
    {
        this.numOfSeasons = numOfSeasons;
    }
}
