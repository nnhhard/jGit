package com.example.jgit.repository;

public interface GitRepository {
    String getFileContent(String branch, String pathFile);
}
