package com.trendcore;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "classpath")
public class PluginGoal extends AbstractMojo {

    @Parameter(name = "warPath")
    private String warPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("Executing Goal...." + warPath);
    }
}
