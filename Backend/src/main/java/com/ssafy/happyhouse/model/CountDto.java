package com.ssafy.happyhouse.model;

public class CountDto {
	@Override
	public String toString() {
		return "CountDto [aptCode=" + aptCode + ", count=" + count + ", preCount=" + preCount + ", preRank=" + preRank
				+ ", rankChange=" + rankChange + "]";
	}
	private int aptCode;
	private int count;
	private int preCount;
	private int preRank;
	private int rankChange;
	
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPreCount() {
		return preCount;
	}
	public void setPreCount(int preCount) {
		this.preCount = preCount;
	}
	public int getPreRank() {
		return preRank;
	}
	public void setPreRank(int preRank) {
		this.preRank = preRank;
	}
	public int getRankChange() {
		return rankChange;
	}
	public void setRankChange(int rankChange) {
		this.rankChange = rankChange;
	}
	
	
}
