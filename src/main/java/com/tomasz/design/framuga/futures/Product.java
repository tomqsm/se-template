package com.tomasz.design.framuga.futures;

/**
 *
 * @author toks
 */
public class Product {
    private int weight;
    private String producer;

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
        return "Product{" + "weight=" + weight + ", producer=" + producer + '}';
    }

    /**
     * @return the producer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * @param producer the producer to set
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }
    
}
