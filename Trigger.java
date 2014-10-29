package org;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trigger {
	public
		Map <String,String> condition;
		List<String> print = new ArrayList<String>();
		String command ="";
		List<String> action = new ArrayList<String>();
		String type ="";
		int index_action = 0;
		int index_print=0;
		int typein = 0;
		
	public void Setcondition(Map<String, String> ncondition){
		this.condition = ncondition;
	}	
	public void Settype(String ntype){
		this.type = ntype;
		if(this.type.equals("single")){
			this.typein = 0;
		}else{
			this.typein = 2;
		}
	}
	public void Setcommand(String ncommand){
		this.command = ncommand;
	}
	public void Setprint(String nprint){
		this.print.add(index_print,nprint);
		this.index_print++;
	}
	public void Setaction(String naction){
		this.action.add(this.index_action,naction);
		this.index_action++;
	}	
	public Map<String, String> Getcondition(){
		return this.condition;
	}
	public String Gettype(){
		return this.type;
	}
	public String Getcommand(){
		return this.command;
	}
	public List<String> Getprint(){
		return this.print;
	}
	public List<String> Getaction(){
		return this.action;
	}
	
}
