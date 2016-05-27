package nl.oa.demo.cucumber.service;

import org.springframework.stereotype.Service;

@Service
public class AppleServiceImpl implements AppleService {

    private int availableApples;

    @Override
    public boolean hasEnoughApples(int askedAmount) {
        return availableApples>=askedAmount;
    }

    @Override
    public void addApples(int amountOfApples) {
        this.availableApples += amountOfApples;
    }

    @Override
    public int receiveApples(int amountApplesAsked) {
        if (hasEnoughApples(amountApplesAsked)) {
            this.availableApples -= amountApplesAsked;
            return amountApplesAsked;
        }
        return 0;
    }

    @Override
    public void setAvailableApples(int amountOfApples) {
        this.availableApples = amountOfApples;
    }
}