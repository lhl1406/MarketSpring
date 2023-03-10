
package Springweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="Vegetable")

public class Vegetable implements Comparable<Vegetable> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int VegetableID;
    private int CategoryID;
    
    private String Vegetable_Name;
    
    private String Unit;
    
    private int Amount;
    
    private String Image;
    
    private float Price;

    //@ManyToOne
    //@JoinColumn(name="CategoryID")
    //private Category category;
    
    public int getVegetableID() {
        return VegetableID;
    }

    public void setVegetableID(int VegetableID) {
        this.VegetableID = VegetableID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getVegetable_Name() {
        return Vegetable_Name;
    }

    public void setVegetable_Name(String Vegetable_Name) {
        this.Vegetable_Name = Vegetable_Name;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
    
    public String getALL() {
        return (Price+" "+ Vegetable_Name+" ");
    }
    @Override
    public int compareTo(Vegetable v) {
	
		float comparePrice = ((Vegetable) v).getPrice(); 
		//ascending order
		return (int) (this.Price - comparePrice);
		
		
	}
}
