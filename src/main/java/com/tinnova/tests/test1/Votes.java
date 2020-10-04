package com.tinnova.tests.test1;

public class Votes {
	
	private Double totalVoters;
	private Double validVotes;
	private Double blankVotes;
	private Double nullVotes;
	
	public Votes(Double totalVoters, Double validVotes, Double blankVotes, Double nullVotes) {
		super();
		this.totalVoters = totalVoters;
		this.validVotes = validVotes;
		this.blankVotes = blankVotes;
		this.nullVotes = nullVotes;
	}

	public Votes(Double totalVoters) {
		super();
		this.totalVoters = totalVoters;
	}


	public Integer getPercentageValidVotes() {
		return (int) ((validVotes / totalVoters) * 100);
	}
	
	public Integer getPercentageBlankVotes() {
		return (int) ((blankVotes / totalVoters) * 100);
	}
	
	public Integer getPercentageNullVotes() {
		return (int) ((nullVotes / totalVoters) * 100);
	}

	public Double getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Double totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Double getValidVotes() {
		return validVotes;
	}

	public void setValidVotes(Double validVotes) {
		this.validVotes = validVotes;
	}

	public Double getBlankVotes() {
		return blankVotes;
	}

	public void setBlankVotes(Double blankVotes) {
		this.blankVotes = blankVotes;
	}

	public Double getNullVotes() {
		return nullVotes;
	}

	public void setNullVotes(Double nullVotes) {
		this.nullVotes = nullVotes;
	}
		
}
