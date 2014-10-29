package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attack {
	public
		List <String> print = new ArrayList<String>();
		Map <String,String> Condition = null;
		List <String> action = new ArrayList<String>();
		int actioncount = 0;

	public void SetCondition(Map<String,String> newcondition){
		this.Condition = newcondition;
	}
	public void Setaction(String newaction){
		this.action.add(newaction);
	}
	public void Setprint(String newprint){
		this.print.add(newprint);
	}
	public Map<String,String> Getcondition(){
		return this.Condition;
	}
	public List<String> Getprint(){
		return this.print;
	}
	public List<String> Getaction(){
		return this.action;
	}
}
