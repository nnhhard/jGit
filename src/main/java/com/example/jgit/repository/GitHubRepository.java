package com.example.jgit.repository;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class GitHubRepository implements GitRepository {

    private Repository rep;
    private String localRep;
    public GitHubRepository() {
        final String stringPath = "./git/repository";

        try {
            File file = Paths.get(stringPath).toFile();
            deleteDir(file);

            this.rep = Git.cloneRepository()
                    .setURI("https://github.com/nnhhard/otus-2022-11-iashinme.git")
                    .setDirectory(file)
                    //.setBranchesToClone(List.of("refs/heads/" + branch2))
                    //.setBranch("refs/heads/" + branch)
                    //.setCredentialsProvider(new UsernamePasswordCredentialsProvider(login, password))
                    .call()
                    .getRepository();

            this.localRep = stringPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFileContent(String branch, String pathFile) {
        String content = null;
        try(Git git = new Git(rep)) {
            git.checkout().setName("origin/" + branch).call();
            content = new String(Files.readAllBytes(Paths.get(localRep + pathFile)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
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
