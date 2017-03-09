package com.mcit.kritth.util;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.mcit.kritth.bo.template.BaseBO;

/***
 * This class was meant to be used to assist with bidirectional update
 * This may be legacy class
 * @author Kritth
 *
 */
public class BidirectionalUtil
{
	/***
	 * 
	 * @param mainObj
	 * @param service
	 * @param objToAdd
	 * @param setFromMainObj
	 */
	public static <U, V> void addIfNew(U mainObj, BaseBO<U> service, V objToAdd, Set<V> setFromMainObj)
	{
		if (!setFromMainObj.contains(objToAdd))
		{
			setFromMainObj.add(objToAdd);
			service.update(mainObj);
		}
	}
	
	/***
	 * 
	 * @param id
	 * @param service
	 * @param setFromMainObj
	 */
	public static <U> void add(Serializable id, BaseBO<U> service, Set<U> setFromMainObj)
	{
		U obj = service.getById(id);
		setFromMainObj.add(obj);
	}
	
	/***
	 * 
	 * @param mainObj
	 * @param service
	 * @param objToRemove
	 * @param setFromMainObj
	 * @param vID
	 * @param setFromForm
	 */
	public static <U, V> void removeIfOld(U mainObj, BaseBO<U> service, V objToRemove, Set<V> setFromMainObj
			, String vID, List<String> setFromForm)
	{
		if (!setFromForm.contains(vID))
		{
			setFromMainObj.remove(objToRemove);
			service.update(mainObj);
		}
	}
	
	/***
	 * 
	 * @param mainObj
	 * @param service
	 * @param objToRemove
	 * @param setFromMainObj
	 */
	public static <U, V> void remove(U mainObj, BaseBO<U> service, V objToRemove, Set<V> setFromMainObj)
	{
		setFromMainObj.remove(objToRemove);
		service.update(mainObj);
	}
}
