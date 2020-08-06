package org.example;

import org.example.model.Block;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class FirstChain {

    /**
     * Your blockchain:
     * > Is made up of blocks that store data.
     * > Has a digital signature that chains your blocks together.
     * > Requires proof of work mining to validate new blocks.
     * > Can be check to see if data in it is valid and unchanged.
     */

    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static int difficulty = 5;
    public static Jsonb jsonb = JsonbBuilder.create();

    public static void main(String[] args) {
        Block genesisBlock = new Block("First Block", "0");
        blockChain.add(genesisBlock);
        System.out.println("Hash for block 1: " + genesisBlock.hash);
        System.out.println("Trying to Mine block 1... ");
        blockChain.get(0).mineBlock(difficulty);

        Block secondBlock = new Block("Second Block", genesisBlock.hash);
        blockChain.add(secondBlock);
        System.out.println("Hash for block 2: " +secondBlock.hash);
        System.out.println("Trying to Mine block 2... ");
        blockChain.get(1).mineBlock(difficulty);

        Block thirdBlock = new Block("Third Block", secondBlock.hash);
        blockChain.add(thirdBlock);
        System.out.println("Hash for block 3: " + thirdBlock.hash);
        System.out.println("Trying to Mine block 3... ");
        blockChain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());



        String blockchainJson = jsonb.toJson(blockChain);
        System.out.println("\nThe block chain: ");
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
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes are not equal.");
                return false;
            }
        }
        return true;
    }
}
