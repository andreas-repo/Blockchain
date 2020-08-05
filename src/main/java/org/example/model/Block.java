package org.example.model;

import org.example.util.StringUtil;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private final long timeStamp; //ms since 1/1/1970

    //Block Constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); //Make sure to set this AFTER we set the other variables
    }

    public String calculateHash() {
        String calculateHash = StringUtil.applySha256(
                previousHash + Long.toString(timeStamp) + data
        );
        return calculateHash;
    }
}
