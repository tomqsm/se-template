package com.tomasz.design.framuga.blockingqueue;

/**
 *
 * @author toks
 */
public class Product {
    private int weight;

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product weight: " + weight;
    }
    
}
