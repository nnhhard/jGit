package com.example.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;


@SpringBootApplication
public class JGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(JGitApplication.class, args);

        final String stringPath = "./git/repository";
        final String branch = "home-work-02";

        try {
            File file = Paths.get(stringPath).toFile();
            deleteDir(file);

            Git.cloneRepository()
                    .setURI("https://github.com/nnhhard/otus-2022-11-iashinme.git")
                    .setDirectory(file)
                    .setBranchesToClone(List.of("refs/heads/" + branch))
                    .setBranch("refs/heads/" + branch)
                    //.setCredentialsProvider(new UsernamePasswordCredentialsProvider(login, password))
                    .call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }
}
