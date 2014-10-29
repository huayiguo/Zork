package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Container {
	private 
		String name;
		String accept ="";
		String item;
		String status="";
		List<String> items = new ArrayList<String>();
		Map <Integer,Trigger> triggers = new HashMap<Integer,Trigger>();
		Trigger trigger = new Trigger();
		int tricount = 0;
	public void Settrigger(Trigger newtrigger){
		this.trigger=newtrigger;
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
	public void Setstatus(String newstatus){
		this.status= newstatus;
	}
	public void Setname(String newname){
		this.name = newname;
	}
	public void Setaccept(String newaccept){
		this.accept = newaccept;
	}
	public void Setitem(String itemname){
		this.items.add(itemname);
	}
	public String Getname(){
		return this.name;
	}
	public String Getaccept(){
		return this.accept;
	}
	public List<String> Getitems(){
		return this.items;
	}
	public void RemoveItem(String item){
		Iterator<String> a  = this.items.iterator();
		int count = 0;
		int index = 0;
		int flag = 0;
		while(a.hasNext()){
			String b = a.next();
			if(b.equals(item)){
				index = count;
				flag = 1;
			}
			count++;
		}
		if(flag ==1){
			this.items.remove(index);
		}
	}
	public String Getstatus(){
		return this.status;
	}
	public Trigger Gettrigger(){
		return this.trigger;
	}
	public Map <Integer,Trigger> Gettriggers(){
		return this.triggers;
	}
	
}
