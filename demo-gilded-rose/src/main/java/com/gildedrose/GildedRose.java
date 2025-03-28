package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
    
    String[] specialProducts = {"Aged Brie", 
    		"Backstage passes to a TAFKAL80ETC concert",
    		"Sulfuras, Hand of Ragnaros"};
    

    public void updateQuality() {
    	
    	 for (int i = 0; i < items.length; i++) {
    		 
    	 }
    	
        for (int i = 0; i < items.length; i++) {
        	if (items[i].quality > 0 && !Arrays.asList(specialProducts).contains(items[i].name)) {
        		items[i].quality = items[i].quality - 1;
	             if(items[i].name.equals("Conjured Mana Cake") && items[i].quality > 1) {
	            	items[i].quality = items[i].quality - 1;
	             }
	             
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            } if(items[i].name.equals("Conjured Mana Cake") && items[i].quality > 1) {
                            	items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}