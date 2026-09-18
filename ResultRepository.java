package com.aec.aqs.repository;

import com.aec.aqs.model.Result;

import java.util.List;

public interface ResultRepository {

    void saveResult(Result result);

    List<Result> getResultsByUser(int userId);

    List<Result> getAllResults();
}