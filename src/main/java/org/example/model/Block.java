package org.example.model;

import org.example.util.StringUtil;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private int nonce;
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
                previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data
        );
        return calculateHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');    //create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined!! : " +hash);
    }
}
