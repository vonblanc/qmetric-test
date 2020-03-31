package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items;
    
    /**
     * introduced discount class add on demand
     */
    private Discounts discount;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }
      
    /**
     * point basket to discount
     * @param discount
     */
    public void addDiscount()
    {
    	//add 2 4 1 discount
    	if(items != null ||  !items.isEmpty())
    	{
        	this.discount= new BasicDiscounts((Item[])items.toArray(new Item[0]));

    	}
    }
    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            
        	return discount.getDiscountedPrice();
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
