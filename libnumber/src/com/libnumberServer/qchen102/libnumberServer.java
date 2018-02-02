package com.libnumberServer.qchen102;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.i18n.phonenumbers.AsYouTypeFormatter;
import com.google.i18n.phonenumbers.CountryCodeToRegionCodeMap;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import apple.laf.JRSUIConstants.SegmentTrailingSeparator;


@Path("phonenumbers")
public class libnumberServer {
	private static ArrayList<String> phoneNumbers = new ArrayList<String>();
	private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	
	@GET
	@Path("/parse/text/{i}")
	@Produces(MediaType.APPLICATION_JSON)
	public String libNumber(@PathParam("i") String i) {
		return getPhoneNumberFromString(i);
	}
	
	 

	@POST
	@Path("/parse/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String receiveFile(InputStream incomingData) {
		String context = "";
		ArrayList<String> tempContext = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			
			String line = null;
			Integer lineNumber = 0;
			while ((line = in.readLine()) != null) {
				tempContext.add(line);
			
				lineNumber++;
			}
			
			for(int i = 4 ; i < lineNumber-1 ; i++) {
				context += tempContext.get(i);
				context += "\n";
			}
			
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		//System.out.println("Data Received: " + crunchifyBuilder.toString());
		System.out.println("Context: "+ context);
		//return Response.status(200).entity(crunchifyBuilder.toString()).build();
		return getPhoneNumberFromString(context);
	}

	
		@POST
		@Path("/parse/file2")
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<String> libNumberPost(FileReader file)throws Exception {
			BufferedReader reader = new BufferedReader(file);

			String text = "";
			String line = reader.readLine();
			while(line != null) {
				text += line;
				line = reader.readLine();
			}
			
			String finalNumber = "";
			Map<Integer, List<String>> countryCodeToRegionCodeMap = 
					CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap();
			for(Integer countryCode : countryCodeToRegionCodeMap.keySet()) {
				finalNumber = parseContact(text, Integer.toString(countryCode));
				if(finalNumber!=null) {
					break;
				}
			}
				
			if(finalNumber==null) {
				finalNumber = "";
			}else {
				finalNumber = "("+ finalNumber;
			}
			
			boolean NumberFound = false;
			for(String phoneNumber : phoneNumbers) {
				if(finalNumber.equals(phoneNumber)) {
					NumberFound = true;
					break;
				}
			}
			if(!NumberFound) {
				phoneNumbers.add(finalNumber);
			}
			
		    return phoneNumbers;
		}
	
	public static String getPhoneNumberFromString(String i) {
String finalNumber = "";
		
		Map<Integer, List<String>> countryCodeToRegionCodeMap = 
				CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap();
		for(Integer countryCode : countryCodeToRegionCodeMap.keySet()) {
			finalNumber = parseContact(i, Integer.toString(countryCode));
			if(finalNumber!=null) {
				break;
			}
		}
			
		if(finalNumber==null) {
			finalNumber = "[]";
		}else {
			finalNumber = "[\"("+ finalNumber + "\"]";
		}
	    return finalNumber;
	}
		
	public static String parseContact(String contact, String countrycode) {
	    PhoneNumber phoneNumber = null;
	    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
	    String finalNumber = null;
	    String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countrycode));
	    boolean isValid = false;
	    PhoneNumberType isMobile = null;
	    try {
	        phoneNumber = phoneNumberUtil.parse(contact, isoCode);
	        isValid = phoneNumberUtil.isValidNumber(phoneNumber);
	        isMobile = phoneNumberUtil.getNumberType(phoneNumber);

	    } catch (NumberParseException e) {
	        e.printStackTrace();
	    } catch (NullPointerException e) {
	        e.printStackTrace();
	    }


	    if (isValid
	            && (PhoneNumberType.MOBILE == isMobile || PhoneNumberType.FIXED_LINE_OR_MOBILE == isMobile)) {
	        finalNumber = phoneNumberUtil.format(phoneNumber,
	                PhoneNumberFormat.NATIONAL).substring(1);
	    }

	    return finalNumber;
	}
	

	
	

	
	
	
}
