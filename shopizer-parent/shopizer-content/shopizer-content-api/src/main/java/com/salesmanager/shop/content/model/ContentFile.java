package com.salesmanager.shop.content.model;


/**
 * Model object used in webservice
 * when creatin files
 * @author carlsamson
 *
 */
public class ContentFile extends ContentPath {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte[] file;
	

	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}


}
