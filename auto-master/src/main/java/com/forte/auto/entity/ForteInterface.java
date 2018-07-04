package com.forte.auto.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ForteInterface {
	private int idf_interface;
	
	@NotEmpty  
    @Length(min=6,max=16)
	private String f_name;
	@NotEmpty
	private String f_desc;
	@NotEmpty
	private String f_url;
	@NotEmpty
	private String f_method;
	@NotEmpty
	private String f_parameters;
	private String f_status;
	private String f_source;
	public String getF_source() {
		return f_source;
	}

	public void setF_source(String f_source) {
		this.f_source = f_source;
	}

	private Date f_createtime;
	private Date f_updatetime;

	public int getIdf_interface() {
		return idf_interface;
	}

	public void setIdf_interface(int idf_interface) {
		this.idf_interface = idf_interface;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_desc() {
		return f_desc;
	}

	public void setF_desc(String f_desc) {
		this.f_desc = f_desc;
	}

	public String getF_url() {
		return f_url;
	}

	public void setF_url(String f_url) {
		this.f_url = f_url;
	}

	public String getF_method() {
		return f_method;
	}

	public void setF_method(String f_method) {
		this.f_method = f_method;
	}

	public String getF_parameters() {
		return f_parameters;
	}

	public void setF_parameters(String f_parameters) {
		this.f_parameters = f_parameters;
	}

	public String getF_status() {
		return f_status;
	}

	public void setF_status(String f_status) {
		this.f_status = f_status;
	}

	public Date getF_createtime() {
		return f_createtime;
	}

	public void setF_createtime(Date f_createtime) {
		this.f_createtime = f_createtime;
	}

	public Date getF_updatetime() {
		return f_updatetime;
	}

	public void setF_updatetime(Date f_updatetime) {
		this.f_updatetime = f_updatetime;
	}

}
