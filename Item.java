package org;

import java.util.HashMap;
import java.util.Map;

public class Item {
	
	//Item - may contain name, status, description, writing, status, turn on, and trigger[ ]. 
	//If item has turn on element and "turn on" command is issued by user, action elements in
	//'turn on' are to be executed if any given conditions are met.
	private
		String name;
  	    String description;
  	    String writing;
  	    String status ="";
  	    Map <String,String> turnon;
  	    int index_turnon = 0;
  	    Trigger trigger = new Trigger();
  	    Map <Integer,Trigger> triggers = new HashMap<Integer,Trigger>();
		int tricount = 0;
  
  	public void Setturnon(Map<String,String> newturnon){
  		this.turnon=newturnon;
  	}
  	public void Setname(String newname){
  		this.name = newname;
  	}
  	public void Setdescription(String newdesription){
  		this.description = newdesription;
  	}
  	public void Setwriting(String newwriting){
  		this.writing = newwriting;
  	}
  	public void Setstatus(String newstatus){
  		this.status = newstatus;
  	}
  	public String Getname(){
  	   return this.name;
  	}
  	public String Getdescription(){
   	   return this.description;
   	}
   	public String Getwriting(){
   	   return this.writing;
   	}
   	public String Getstatus(){
   	   return this.status;
    }
   	public Map <String,String> Getturnon(){
   		return this.turnon;
   	}
	public void Settrigger(Trigger newtrigger){
		this.trigger = newtrigger;
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
			
			this.triggers.put(tricount, this.trigger);
		}
		this.tricount++;
	}
	public Map <Integer,Trigger> Gettriggers(){
		return this.triggers;
	}

	
}//each Item must have name, description and writing on it.

