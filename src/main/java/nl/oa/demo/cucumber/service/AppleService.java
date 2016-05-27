package nl.oa.demo.cucumber.service;

public interface AppleService {

    boolean hasEnoughApples(int askedAmount);

    void addApples(int amountOfApples);

    int receiveApples(int amountApplesAsked);

    void setAvailableApples(int amountOfApples);
}
