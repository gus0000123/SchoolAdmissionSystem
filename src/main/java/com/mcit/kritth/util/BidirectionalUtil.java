package com.mcit.kritth.util;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.mcit.kritth.bo.template.BaseBO;

public class BidirectionalUtil
{
	public static <U, V> void addIfNew(U mainObj, BaseBO<U> service, V objToAdd, Set<V> setFromMainObj)
	{
		if (!setFromMainObj.contains(objToAdd))
		{
			setFromMainObj.add(objToAdd);
			service.update(mainObj);
		}
	}
	
	public static <U> void add(Serializable id, BaseBO<U> service, Set<U> setFromMainObj)
	{
		U obj = service.getById(id);
		setFromMainObj.add(obj);
	}
	
	public static <U, V> void removeIfOld(U mainObj, BaseBO<U> service, V objToRemove, Set<V> setFromMainObj
			, String vID, List<String> setFromForm)
	{
		if (!setFromForm.contains(vID))
		{
			setFromMainObj.remove(objToRemove);
			service.update(mainObj);
		}
	}
	
	public static <U, V> void remove(U mainObj, BaseBO<U> service, V objToRemove, Set<V> setFromMainObj)
	{
		setFromMainObj.remove(objToRemove);
		service.update(mainObj);
	}
}
