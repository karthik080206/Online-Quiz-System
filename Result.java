package com.aec.aqs.model;

public class Result {
	

	    private int resultId;
	    private int userId;
	    private int score;
	    private int totalQuestions;

	    public Result() {
	    }

	    public Result(int resultId, int userId,
	                  int score, int totalQuestions) {

	        this.resultId = resultId;
	        this.userId = userId;
	        this.score = score;
	        this.totalQuestions = totalQuestions;
	    }

	    public Result(int userId, int score, int totalQuestions) {
	        this.userId = userId;
	        this.score = score;
	        this.totalQuestions = totalQuestions;
	    }

	    public int getResultId() {
	        return resultId;
	    }

	    public void setResultId(int resultId) {
	        this.resultId = resultId;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public int getScore() {
	        return score;
	    }

	    public void setScore(int score) {
	        this.score = score;
	    }

	    public int getTotalQuestions() {
	        return totalQuestions;
	    }

	    public void setTotalQuestions(int totalQuestions) {
	        this.totalQuestions = totalQuestions;
	    }

	    @Override
	    public String toString() {

	        return "Result ID: " + resultId +
	                ", User ID: " + userId +
	                ", Score: " + score +
	                "/" + totalQuestions;
	    }
	}

