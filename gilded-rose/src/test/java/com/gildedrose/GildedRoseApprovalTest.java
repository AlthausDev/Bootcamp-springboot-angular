package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@UseReporter(DiffReporter.class)
public class GildedRoseApprovalTest {
	
	
    @Test
    @DisplayName("Item generico caducado")
    public void foo() {
    	
    	Item[] items = new Item[]{new Item("foo", 0, 6)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("foo", items[0].name);
		assertEquals(-1, items[0].sellIn);
		assertEquals(4, items[0].quality);
		
        //Approvals.verifyAll("Items", items);
    }
    
    @Test
    @DisplayName("Item generico rango")
    public void foo2Test() {
    	
    	Item[] items = new Item[]{new Item("foo", 3, 3)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("foo", items[0].name);
		assertEquals(2, items[0].sellIn);
		assertEquals(2, items[0].quality);
		
        //Approvals.verifyAll("Items", items);
    }
       
    
    @Test
    @DisplayName("Brie antes de la fecha de caducidad")
    public void BrieTest() {
    	
    	Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Aged Brie", items[0].name);
		assertEquals(1, items[0].sellIn);
		assertEquals(1, items[0].quality);		
    }
    
    @Test
    @DisplayName("Brie despues de la fecha de caducidad")
    public void BrieTest2() {
    	
    	Item[] items = new Item[]{new Item("Aged Brie",0, 0)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Aged Brie", items[0].name);
		assertEquals(-1, items[0].sellIn);
		assertEquals(2, items[0].quality);		
    }
    
    @Test
    @DisplayName("Brie con maxima calidad")
    public void BrieTest3() {
    	
    	Item[] items = new Item[]{new Item("Aged Brie",0, 50)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Aged Brie", items[0].name);
		assertEquals(-1, items[0].sellIn);
		assertEquals(50, items[0].quality);		
    }
    
    @Test
    @DisplayName("Sulfuras")
    public void SulfurasTest() {
    	
    	Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros",0, 80)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Sulfuras, Hand of Ragnaros", items[0].name);
		assertEquals(0, items[0].sellIn);
		assertEquals(80, items[0].quality);		
    }
    
    @Test
    @DisplayName("Backstage 11 días antes de la caducidad")
    public void BackstageTest() {
    	
    	Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",11,1)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
		assertEquals(10, items[0].sellIn);
		assertEquals(2, items[0].quality);		
    }
    
    @Test
    @DisplayName("Backstage 6 días antes de la caducidad")
    public void BackstageTest2() {
    	
    	Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",6,1)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
		assertEquals(5, items[0].sellIn);
		assertEquals(3, items[0].quality);		
    }
    
    @Test
    @DisplayName("Backstage 2 días antes de la caducidad")
    public void BackstageTest3() {
    	
    	Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",2,1)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
		assertEquals(1, items[0].sellIn);
		assertEquals(4, items[0].quality);		
    }
    
    @Test
    @DisplayName("Backstage día de caducar")
    public void BackstageTest4() {
    	
    	Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",0,10)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
		assertEquals(-1, items[0].sellIn);
		assertEquals(0, items[0].quality);		
    }
    
    @Test
    @DisplayName("Backstage con maxima calidad")
    public void BackstageTest5() {
    	
    	Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",3,50)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Backstage passes to a TAFKAL80ETC concert", items[0].name);
		assertEquals(2, items[0].sellIn);
		assertEquals(50, items[0].quality);		
    }
    
    @Test
    @DisplayName("Conjured Mana Cake degracion en rango")
    public void ConjuredTest() {
    	
    	Item[] items = new Item[]{new Item("Conjured Mana Cake",3,6)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Conjured Mana Cake", items[0].name);
		assertEquals(2, items[0].sellIn);
		assertEquals(4, items[0].quality);		
    }
    
    @Test
    @DisplayName("Conjured Mana Cake caducado")
    public void ConjuredTest2() {
    	
    	Item[] items = new Item[]{new Item("Conjured Mana Cake",0,6)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Conjured Mana Cake", items[0].name);
		assertEquals(-1, items[0].sellIn);
		assertEquals(2, items[0].quality);		
    }
    
    @Test
    @DisplayName("Conjured Mana Cake caducado con calidad 1")
    public void ConjuredTest3() {
    	
    	Item[] items = new Item[]{new Item("Conjured Mana Cake",0,1)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Conjured Mana Cake", items[0].name);
		assertEquals(-1, items[0].sellIn);
		assertEquals(0, items[0].quality);		
    }
    
    @Test
    @DisplayName("Conjured Mana Cake caducado con calidad 3")
    public void ConjuredTest4() {
    	
    	Item[] items = new Item[]{new Item("Conjured Mana Cake",0,3)};
		GildedRose app = new GildedRose(items); 
		app.updateQuality();

		assertEquals("Conjured Mana Cake", items[0].name);
		assertEquals(-1, items[0].sellIn);
		assertEquals(0, items[0].quality);		
    }

    @Test
    public void thirtyDays() {

        ByteArrayOutputStream fakeoutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(fakeoutput));
        System.setIn(new ByteArrayInputStream("a\n".getBytes()));

        Program.main();
        String output = fakeoutput.toString();

        //Approvals.verify(output);
    }
}
