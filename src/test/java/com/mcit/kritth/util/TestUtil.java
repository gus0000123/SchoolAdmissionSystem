package com.mcit.kritth.util;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.DepartmentCode;

public class TestUtil
{
	private static SecureRandom random;
	
	private static SecureRandom getRandomGenerator()
	{
		if (random == null) random = new SecureRandom();
		return random;
	}
	
	public static String generateRandomString()
	{
		return new BigInteger(32, getRandomGenerator()).toString(32);
	}
	
	public static int generateRandomNumber() { return generateRandomNumber(100); }
	public static int generateRandomNumber(int bound)
	{
		return getRandomGenerator().nextInt(bound) + 1; // Guarantee not 0
	}

	public static Department createDummyDepartmentWithCode()
	{
		Department d = new Department();
		DepartmentCode dc = new DepartmentCode();
		dc.setDept_code(generateRandomString());
		dc.setDept_name(generateRandomString());
		d.setDept_code(dc);
		
		return d;
	}
}
