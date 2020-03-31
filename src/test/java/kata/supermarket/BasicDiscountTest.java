package kata.supermarket;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import kata.supermarket.GroupedItem.GroupedType;

public class BasicDiscountTest {

	/**
	 * 2 4 1 discount object
	 */
	BasicDiscounts discount; 
	
	
	/**
	 * 2 4 1 discount object
	 */
	@Test
	public void testBasicTestDiscounts()
	{
		discount= new BasicDiscounts(fetchGroupedItems1());
		assertEquals(new BigDecimal("0.00"),discount.getDiscountedPrice());
		
		discount= new BasicDiscounts(fetchGroupedItems1(), new Product(new BigDecimal("50.5")).oneOf());
		assertEquals(new BigDecimal("50.50"),discount.getDiscountedPrice());
		
		discount= new BasicDiscounts(fetchGroupedItems1(), new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5")));
		assertEquals(new BigDecimal("102.50"),discount.getDiscountedPrice());

		discount= new BasicDiscounts(fetchGroupedItems1(), 
			new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5")),
			new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5"))
				);
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
