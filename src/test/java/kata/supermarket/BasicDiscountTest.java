package kata.supermarket;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import kata.supermarket.GroupedItem.GroupedType;

public class BasicDiscountTest {

	BasicDiscounts discount; 
	
	
	
	@Test
	public void testBasicTestDiscounts()
	{
		discount= new BasicDiscounts(fetchGroupedItems1());
	    discount.applyTwoFor1Discount();
		assertEquals(new BigDecimal("0.00"),discount.getDiscountedPrice());
		
		discount= new BasicDiscounts(fetchGroupedItems1(), new Product(new BigDecimal("50.5")).oneOf());
	    discount.applyTwoFor1Discount();
		assertEquals(new BigDecimal("50.50"),discount.getDiscountedPrice());
		
		discount= new BasicDiscounts(fetchGroupedItems1(), new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5")));
	    discount.applyTwoFor1Discount();
		assertEquals(new BigDecimal("102.50"),discount.getDiscountedPrice());

		discount= new BasicDiscounts(fetchGroupedItems1(), 
			new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5")),
			new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5"))
				);
	    discount.applyTwoFor1Discount();
		assertEquals(new BigDecimal("102.50"),discount.getDiscountedPrice());


	}
	
	
	
	private GroupedItem fetchGroupedItems1()
	{
		
		 List<Item> items=Arrays.asList(new Product(new BigDecimal("50.5")).oneOf(),
					new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5") ),null);
		
		 GroupedItem item = new GroupedItem(GroupedType.SAME,items);

		 
		 return item;
	}
	
}
