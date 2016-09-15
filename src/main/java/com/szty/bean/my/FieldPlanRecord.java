package com.szty.bean.my;

import java.util.ArrayList;
import java.util.List;

public class FieldPlanRecord {
	
	private String cropNo;

    private String cropTpyeNo;

    private String cropName;

    private String imageUrl;
    
    private List<VoFieldRecord> fieldRecords;
    
    {
    	fieldRecords = new ArrayList<VoFieldRecord>();
    }
    
    public void addRecord(VoFieldRecord record){
    	fieldRecords.add(record);
    }
    
    public int recordSize() {
    	return fieldRecords.size();
    }

	public String getCropNo() {
		return cropNo;
	}

	public void setCropNo(String cropNo) {
		this.cropNo = cropNo;
	}

	public String getCropTpyeNo() {
		return cropTpyeNo;
	}

	public void setCropTpyeNo(String cropTpyeNo) {
		this.cropTpyeNo = cropTpyeNo;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<VoFieldRecord> getFieldRecords() {
		return fieldRecords;
	}

	public void setFieldRecords(List<VoFieldRecord> fieldRecords) {
		this.fieldRecords = fieldRecords;
	}
    
}
