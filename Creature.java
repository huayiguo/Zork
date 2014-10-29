package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creature {
	private 
		String name;
		List<String> vulnerability = new ArrayList<String>();
		String description;
		String Status;
		Attack attack = new Attack();
		Trigger trigger = new Trigger();
		Map <Integer,Trigger> triggers = new HashMap<Integer,Trigger>();
		int tricount = 0;
	public String Getname(){
		return this.name;
	}
	public Trigger Gettrigger(){
		return this.trigger;
	}
	public List<String> Getvul(){
		return this.vulnerability;
	}
	public String Getdes(){
		return this.description;
	}
	public Attack attack(){
		return this.attack;
	}
	public String Getstatus(){
		return this.Status;
	}
	public void Setdes(String Des){
		this.description = Des;
	}
	public void Setname(String name){
		this.name = name;
	}
	public void Setattack(Attack newattack){
		this.attack = newattack;
	}
	public void Setvun(String Des){
		this.vulnerability.add(Des);
	}
	public void Setstatus(String status){
		this.Status = status;
	//	System.out.println("The status has been updated to "+status);
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
			//System.out.println(tricount);
			this.triggers.put(tricount, this.trigger);
		}
		this.tricount++;
	}
	public Map <Integer,Trigger> Gettriggers(){
		return this.triggers;
	}
}
