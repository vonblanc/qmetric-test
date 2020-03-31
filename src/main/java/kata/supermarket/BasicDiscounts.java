package kata.supermarket;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Discount implementation
 * @author Richard
 *
 */
public class BasicDiscounts implements Discounts {

	private int numberOfItems=0;
	
	private BigDecimal totalPrice=BigDecimal.ZERO; 
	
	private BigDecimal bigIntegerDiscountedPrice=BigDecimal.ZERO; 
	
	private List<Item> items;
	
	public BasicDiscounts (Item ...items)
	{
		this.items= new LinkedList<Item>();
		
		for(Item item: items)
		{	
			
			if(item instanceof GroupedItem)
			{
				numberOfItems+= ((GroupedItem) item).getItems().size();
				this.items.addAll(((GroupedItem) item).getItems());
			
			}
			else {
					numberOfItems +=1;
					this.items.add(item);

				}
			totalPrice=totalPrice.add(item.price());

		}	
	}
	/**
	 * for the sake of basics  
	 * a product is same if two prices are exactly thesame
	 * ideally give product an id by giving it a parent
	 */
	public void applyTwoFor1Discount()
	{
		if(numberOfItems < 2) return; 
		
		
		Set <BigDecimal> valueSet=new HashSet<BigDecimal>(); 
		
		for(Item item: items)
		{
			if(valueSet.contains(item.price()))
				{
				bigIntegerDiscountedPrice=bigIntegerDiscountedPrice.add(item.price());
				  valueSet.remove(item.price());
				}
			else {
				valueSet.add(item.price());
				
			}
				
		}
		
		
	
		
	}
	@Override
	public BigDecimal getDiscountedPrice() {
		applyTwoFor1Discount();
		return bigIntegerDiscountedPrice.setScale(2, RoundingMode.HALF_UP);
	}

}
