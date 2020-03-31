package kata.supermarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import kata.supermarket.GroupedItem.GroupedType;

public class GroupedItemTest {
	
	
	
	
	GroupedItem items;
	
	@Test
	public void testGroupedItem()
	{

		items= new GroupedItem(GroupedType.SAME,fetchItems1());
		assertEquals(1, items.getItems().size());
		assertEquals(GroupedType.SAME, items.getType());
		assertEquals(new BigDecimal("50.5"), items.totalPrice);

		items= new GroupedItem(GroupedType.MIXED,fetchItems2());
		assertEquals(2, items.getItems().size());
		assertEquals(GroupedType.MIXED, items.getType());
		assertEquals(new BigDecimal("153.00"), items.totalPrice);
		
		items= new GroupedItem(GroupedType.MIXED,null);
		assertNull(items.getItems());

		
	}
	
	private List<Item> fetchItems1()
	{
		return Arrays.asList(new Product(new BigDecimal("50.5")).oneOf(), null);
	}

	private List<Item> fetchItems2()
	{
		return Arrays.asList(new Product(new BigDecimal("50.5")).oneOf(),
		new WeighedProduct(new BigDecimal("20.5")).weighing(new BigDecimal("5") ),null);
	}
}
