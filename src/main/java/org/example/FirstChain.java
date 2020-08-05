package org.example;

import org.example.model.Block;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class FirstChain {

    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static Jsonb jsonb = JsonbBuilder.create();

    public static void main(String[] args) {
        Block genesisBlock = new Block("First Block", "0");
        System.out.println("Hash for block 1: " + genesisBlock.hash);

        Block secondBlock = new Block("Second Block", genesisBlock.hash);
        System.out.println("Hash for block 2: " +secondBlock.hash);

        Block thirdBlock = new Block("Third Block", secondBlock.hash);
        System.out.println("Hash for block 3: " + thirdBlock.hash);

        blockChain.add(genesisBlock);
        blockChain.add(secondBlock);
        blockChain.add(thirdBlock);

        String blockchainJson = jsonb.toJson(blockChain);
        System.out.println(blockchainJson);


    }

    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);

            //compare registered hash and calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes are not equal.");
                return  false;
            }
            //compare previous hash and registerd previous has
            if(!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes are not equal.");
                return false;
            }
        }
        return true;
    }
}
