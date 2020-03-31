package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Enhancement to group Items
 * @author Richard
 *
 */
public class GroupedItem implements Item{

	public enum GroupedType{
		
		MIXED,SAME;
	}
	
	private GroupedType type;
	
	private List<Item> items;
	
	BigDecimal totalPrice=BigDecimal.ZERO; 
	
	public GroupedItem(GroupedType type, List<Item> items)
	{
		if(items!= null)
		{
			this.items=new ArrayList<Item>(items.size());
			this.type=type;
			
			for(Item item: items)
			{
				if(item == null) continue;
				
				this.totalPrice=this.totalPrice.add(item.price());
				
				this.items.add(item);
				
			}
		}
	}
	
	public GroupedType getType()
	{		
		return type;
	}
	public List<Item> getItems()
	{
		
		if(items != null) return Collections.unmodifiableList(items);
		else return null;
	}

	@Override
	public BigDecimal price() {
		// TODO Auto-generated method stub
		return totalPrice.setScale(2, RoundingMode.HALF_UP);
	}
	
	
	
}
