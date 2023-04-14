package com.example.jgit;

import com.example.jgit.repository.GitHubRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JGitApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(JGitApplication.class, args);

        final String branch = "main";
        final String branch2 = "home-work-02";

        GitHubRepository bean = context.getBean(GitHubRepository.class);
        System.out.println(bean.getFileContent(branch, "/home-work-02/pom.xml"));
    }


}
