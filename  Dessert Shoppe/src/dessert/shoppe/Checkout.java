// DOoOooone

package dessert.shoppe;

import dessert.shoppe.Candy;
import dessert.shoppe.Cookie;
import dessert.shoppe.IceCream;
import dessert.shoppe.Sundae;

public class Checkout {
	private DessertItem[] myDessertItems;
	private int NumberOfItems;
	private final int RECEIPT_WD = 30; // receipt  width
	
	public Checkout() {
		myDessertItems = new DessertItem[50];
		NumberOfItems = 0;
	}
	
	public int numberOfItems() {
		return NumberOfItems;
	}
	
	public void enterItem(DessertItem item) {
		this.myDessertItems[NumberOfItems] = item;
		NumberOfItems++;
	}
	
	public void clear() {
		for(int i = 0; i < NumberOfItems; i++)
			this.myDessertItems[i] = null;
		NumberOfItems = 0;
	}
	
	public int totalCost() {
		int sum = 0;
		for(int i = 0; i < NumberOfItems; i++) 
			sum += myDessertItems[i].getCost(); 
		return sum;
	}
	
	public int totalTax() {
		return (int)Math.round(this.totalCost() * DessertShoppe.TAX_RATE / 100.00);
	}
	
	public String toString() {
		String x = "";		// This is for receipt to do in string
                
		x += "  " + DessertShoppe.STORE_NAME + "\n";
		x += "  " + "--------------------" + "\n";
		
		for(int i = 0; i < NumberOfItems; i++){
		
			String y = myDessertItems[i].getName();		// we get items of every line
                                                                        // y refer to the name of every item in the line
			
			
			String PRICE = DessertShoppe.cents2dollarsAndCents(myDessertItems[i].getCost());	//here we can get price of every item
			if (PRICE .length() > DessertShoppe.COST_WIDTH)		
				PRICE  = PRICE .substring(0, DessertShoppe.COST_WIDTH);
			
			if (myDessertItems[i] instanceof IceCream) {		//  if  it's an ice cream
				while(y.length() < RECEIPT_WD - PRICE.length()){
					y += " ";
				}
				x+= y + PRICE  + "\n";
			}
			else if (myDessertItems[i] instanceof Sundae) {		//  if  it's a Sundae
				
				x += ((Sundae)myDessertItems[i]).getTopping() + " Sundae with\n";
				
				while(y.length() < RECEIPT_WD - PRICE .length()){
					y += " ";
				}
				x += y + PRICE + "\n";
			}
			else if (myDessertItems[i] instanceof Candy){		//  if it's a  Candy
				x += ((Candy) myDessertItems[i]).getWeight() + " lbs @ " + 
						DessertShoppe.cents2dollarsAndCents(((Candy) myDessertItems[i]).getPricePerPound()) + " /lb.\n";
				
				while(y.length() < RECEIPT_WD - PRICE .length()){
					y += " ";
				}
				x += y + PRICE  + "\n";	
			}
			else {		// print if Cookie
				x+= ((Cookie)myDessertItems[i]).getNumber() + " @ " + 
						DessertShoppe.cents2dollarsAndCents(((Cookie)myDessertItems[i]).getPricePerDozen()) + " /dz\n";
				
				while(y.length() < RECEIPT_WD - PRICE.length()){
					y += " ";
				}
				x += y + PRICE  + "\n";			
			}	
		}
		
		String line = "\nTax";		
		String tax = DessertShoppe.cents2dollarsAndCents(this.totalTax());	// here is the total  tax
		while(line.length() <= RECEIPT_WD- tax.length())
			line += " ";
		x+= line + tax;
		
		String totalCost = DessertShoppe.cents2dollarsAndCents(this.totalCost() + this.totalTax());	// printing total cost
		line = "\nTotal Cost";
		while(line.length() <= RECEIPT_WD- totalCost.length())
			line += " ";
		x += line + totalCost;
	
		return x;
	}
}


