package org.exl.test.demo.core.services;

public interface HttpService {

	/**
	 * This method makes the HTTP call on the given URL
	 * 
	 * @param url
	 * @return {@link String}
	 */
	public String makeHttpCall();
}