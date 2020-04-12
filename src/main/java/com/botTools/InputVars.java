package com.botTools;

public class InputVars {
	private String collegename = "";
	private String term = "";
	private String majorname = "";
	private String classes = "";
	private Boolean reload;
	private Boolean hornOnOrOff;
	private int reloadsecs = 10;
	
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term + " Term";
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String subjectname) {
		this.majorname = subjectname;
	}
	public String[] getClassesArray()
	{
		String[] classesstringtoarray = classes.split(", ", 15);
		return classesstringtoarray;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public Boolean getReload() {
		return reload;
	}
	public void setReload(Boolean reload) {
		this.reload = reload;
	}
	public Boolean getHornOnOrOff() {
		return hornOnOrOff;
	}
	public void setHornOnOrOff(Boolean hornOnOrOff) {
		this.hornOnOrOff = hornOnOrOff;
	}
	public int getReloadsecs() {
		return reloadsecs;
	}
	public void setReloadsecs(int reloadsecs) {
		this.reloadsecs = reloadsecs;
	}
}
