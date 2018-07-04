package com.forte.auto.entity;

import java.util.Date;

public class ForteRunner {
	private int idf_running;
	private int f_interface_id;
	private String f_parameters;
	private String f_response;
	private String f_status;
	private Date f_createtime;
	private String f_patch;


	public String getF_patch() {
		return f_patch;
	}

	public void setF_patch(String f_patch) {
		this.f_patch = f_patch;
	}

	public int getIdf_running() {
		return idf_running;
	}

	public void setIdf_running(int idf_running) {
		this.idf_running = idf_running;
	}

	public int getF_interface_id() {
		return f_interface_id;
	}

	public void setF_interface_id(int f_interface_id) {
		this.f_interface_id = f_interface_id;
	}

	public String getF_parameters() {
		return f_parameters;
	}

	public void setF_parameters(String f_parameters) {
		this.f_parameters = f_parameters;
	}

	public String getF_response() {
		return f_response;
	}

	public void setF_response(String f_response) {
		this.f_response = f_response;
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

}
