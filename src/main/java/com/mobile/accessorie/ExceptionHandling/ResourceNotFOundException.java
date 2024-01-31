package com.mobile.accessorie.ExceptionHandling;

public class ResourceNotFOundException extends  RuntimeException  {

    String resoureceName;
	String filedName;
	long fieldvalue;

	public ResourceNotFOundException(String resoureceName, String filedName, long fieldvalue) {
		super(String.format("%s not found with% s :  %l", resoureceName, filedName, fieldvalue));
		this.resoureceName = resoureceName;
		this.filedName = filedName;
		this.fieldvalue = fieldvalue;

	}

    
}
