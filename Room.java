package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.Item;
import org.Trigger;

public class Room {
   private 
   		String name = "";
   		String description = "";
   		Trigger trigger = null;
   		String type = "";
   		String status;
   		List<Item> Items = new ArrayList<Item>();
   		List<Border> borders = new ArrayList<Border>();
   		List<Container> containers = new ArrayList<Container>();
   		List<String> creatures = new ArrayList<String>();
   		Map <Integer,Trigger> triggers = new HashMap<Integer,Trigger>();
   		int tricount = 0;
  
   public void Setcreature(String newcreature){
	   this.creatures.add(newcreature);
   }
   public void Setstatus(String newstatus){
	   this.status =newstatus;
   }
   public void Setname(String name){
	   this.name = name;
   }
   public void Settype(String newtype){
	   this.type = newtype;
   }
   public void Setborder(Border newborder){
	   this.borders.add(newborder);
   }
   public void Setcontainer(Container container){
	   this.containers.add(container);
   }
   public void Setdescription(String Des){
	   this.description= Des;
   }
   public void SetItem(Item newItem){
	   this.Items.add(newItem);
   }
   public void Settrigger(Trigger newtrigger){
	   this.trigger= newtrigger;
	   if(newtrigger.Getcommand()!=null){
			if(this.tricount!=0){
				Trigger temptrigger = this.triggers.get(0);
				//if(temptrigger==null){System.out.println("shit "+tricount);}
				this.triggers.remove(0);
				this.triggers.put(0,this.trigger);
				this.triggers.put(this.tricount,temptrigger);
			}else{
				this.triggers.put(tricount, this.trigger);
			}
		}else{
			//System.out.println(tricount);
			this.triggers.put(tricount, this.trigger);
		}
		this.tricount++;
   }
   public String Getname(){
	   return this.name;
   }
   public String Getdescription(){
	   return this.description;
   }
   public Trigger Gettrigger(){
	   return this.trigger;
   }
   public Map <Integer,Trigger> Gettriggers(){
		return this.triggers;
   }
   public List<Item> GetItems(){
	   return this.Items;
   }
   public List<Border> Getborders(){
	   return this.borders;
   }
   public List<Container> GetContainer(){
	   return this.containers;
   }
   public String Gettype(){
	   return this.type;
   }
   public String Getstatus(){
	   return this.status;
   }
   public List<String> GetCreature(){
	   return this.creatures;
   }
   public void RemoveCreature(String creature){
		Iterator<String> a  = this.creatures.iterator();
		int count = 0;
		int index = 0;
		int flag = 0;
		while(a.hasNext()){
			String b = a.next();
			if(b.equals(creature)){
				index = count;
				flag = 1;
			}
			count++;
		}
		if(flag ==1){
			this.creatures.remove(index);
		}
	}
}
