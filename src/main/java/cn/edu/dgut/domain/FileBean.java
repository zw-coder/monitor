package cn.edu.dgut.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//标识dao层为spring的bean
@Repository
public class FileBean {
	
	private String id;
	private String text;
	private String state = "closed";
	private boolean file;
	private String icoName;
	
	public FileBean(String id, String text, boolean file) {
		super();
		this.id = id;
		this.text = text;
		this.file = file;
	}
	
	public FileBean(String id, String text, String state, boolean file) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.file = file;
	}
	public FileBean() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isFile() {
		return file;
	}
	public void setFile(boolean file) {
		this.file = file;
	}

	public String getIcoName() {
		return icoName;
	}

	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}

}
