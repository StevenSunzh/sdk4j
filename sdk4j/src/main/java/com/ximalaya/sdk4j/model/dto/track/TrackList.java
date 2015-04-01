package com.ximalaya.sdk4j.model.dto.track;

import java.util.List;

import com.ximalaya.sdk4j.model.dto.AbstractPageResult;

/**
 * 声音列表DTO
 * @author will
 *
 */
public class TrackList extends AbstractPageResult {
	
	private Long categoryID;       // 声音所属分类ID，可选
	private String categoryName;   // 声音所属分类名，可选
	private String tagName;        // 选填的声音所属标签名，可选
	private List<Track> tracks;    // 声音列表
	
	public Long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public List<Track> getTracks() {
		return tracks;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryID == null) ? 0 : categoryID.hashCode());
		result = prime * result + ((tagName == null) ? 0: tagName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		TrackList other = (TrackList) obj;
		if((categoryID == null && other.categoryID != null) 
			|| !categoryID.equals(other.categoryID)) {
			return false;
		}
		if(tagName == null && other.tagName != null
			|| !tagName.equals(other.tagName)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("TrackList {categoryID: ");
		strBuilder.append(categoryID);
		strBuilder.append(", categoryName: \"");
		strBuilder.append(categoryName);
		strBuilder.append("\", tagName: \"");
		strBuilder.append(tagName);
		strBuilder.append("\", tracks: [");
		if(tracks != null && !tracks.isEmpty()) {
			for(Track track: tracks) {
				strBuilder.append(track.toString());
				strBuilder.append(", ");
			}
			strBuilder.deleteCharAt(strBuilder.lastIndexOf(","));
		}
		strBuilder.append("]}");
		return strBuilder.toString();
	}
}
