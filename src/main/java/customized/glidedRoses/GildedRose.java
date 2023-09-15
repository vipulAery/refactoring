
/**
 * ======================================
 * Gilded Rose Requirements Specification
 * ======================================
 * <p>
 * Hi and welcome to team Gilded Rose. As you know, we are a small inn with a prime location in a
 * prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the finest goods.
 * Unfortunately, our goods are constantly degrading in quality as they approach their sell by date. We
 * have a system in place that updates our inventory for us. It was developed by a no-nonsense type named
 * Leeroy, who has moved on to new adventures. Your task is to add the new feature to our system so that
 * we can begin selling a new category of items. First an introduction to our system:
 * <p>
 * - All items have a SellIn value which denotes the number of days we have to sell the item
 * - All items have a Quality value which denotes how valuable the item is
 * - At the end of each day our system lowers both values for every item
 * <p>
 * Pretty simple, right? Well this is where it gets interesting:
 * <p>
 * - Once the sell by date has passed, Quality degrades twice as fast
 * - The Quality of an item is never negative
 * - "Aged Brie" actually increases in Quality the older it gets
 * - The Quality of an item is never more than 50
 * - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
 * - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
 * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
 * Quality drops to 0 after the concert
 * <p>
 * We have recently signed a supplier of conjured items. This requires an update to our system:
 * <p>
 * - "Conjured" items degrade in Quality twice as fast as normal items
 * <p>
 * Feel free to make any changes to the UpdateQuality method and add any new code as long as everything
 * still works correctly. However, do not alter the Item class or Items property as those belong to the
 * goblin in the corner who will insta-rage and one-shot you as he doesn't believe in shared code
 * ownership (you can make the UpdateQuality method and Items property static if you like, we'll cover
 * for you).
 * <p>
 * Just for clarification, an item can never have its Quality increase above 50, however "Sulfuras" is a
 * legendary item and as such its Quality is 80 and it never alters.
 **/


/*
 * <p>
 * - All items have a SellIn value which denotes the number of days we have to sell the item
 * - All items have a Quality value which denotes how valuable the item is
 * - At the end of each day our system lowers both values for every item
 * <p>
<p>
*
*
*
* Pretty simple, right? Well this is where it gets interesting:
* <p>
* - Once the sell by date has passed, Quality degrades twice as fast
* - The Quality of an item is never negative
* - "Aged Brie" actually increases in Quality the older it gets
* - The Quality of an item is never more than 50
* - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
* - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches
* Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
* Quality drops to 0 after the concert
* <p>
*
*
*
* We have recently signed a supplier of conjured items. This requires an update to our system:
* <p>
* - "Conjured" items degrade in Quality twice as fast as normal items
* <p>

*/

/*
SellIn, Quality dec. by 1, if SellIn < 0 then dec. by 1 more

AgedBrie ->
Quality inc. by 1 , if sellIn < 0 quality inc. by 1 more

Sulfurus ->
no change

Backtage passes ->
Quality inc. by 1 , if sellIn < 11 quality inc. by 1 more if sellIn < 6 inc. by 1 more if sellIn < 0, reset quality = 0

Conjured:
SellIn dec by 1, Quality dec. by 2, if SellIn < 0 then dec. by 2 more

Constraints:
Quality >= 0 && Quality <= 50
 */


//package com.gildedrose;
package customized.glidedRoses;

public class GildedRose {
    Item[] items;

    GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            String sulfuras = "Sulfuras, Hand of Ragnaros";
            String backstage = "Backstage passes to a TAFKAL80ETC concert";
            String agedBrie = "Aged Brie";

            if (sulfuras.equals(item.name)) {
                continue;
            }

            if (item.name.equals(agedBrie) || item.name.equals(backstage)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals(backstage)) {
                        if (item.sellIn < 11 && (item.quality < 50)) {
                            item.quality = item.quality + 1;
                        }

                        if (item.sellIn < 6 && (item.quality < 50)) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            } else if (item.quality > 0) {
                item.quality = item.quality - 1;
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn > 0) {
                continue;
            }

            if (item.name.equals(agedBrie)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            } else if (item.name.equals(backstage)) {
                item.quality = 0;
            } else if (item.quality > 0) {
                item.quality = item.quality - 1;
            }

        }
    }
}


class Item {

    public String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
