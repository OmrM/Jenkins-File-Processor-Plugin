package io.jenkins.plugins.fileProcessor;

import hudson.Launcher;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

public class FileProcessingBuilder extends Builder implements SimpleBuildStep {

    //Getters and setters for Parameters: name, userFrench
    private final String name;
    private boolean useFrench;

    @DataBoundConstructor
    public FileProcessingBuilder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isUseFrench() {
        return useFrench;
    }

    @DataBoundSetter
    public void setUseFrench(boolean useFrench) {
        this.useFrench = useFrench;
    }

    //I think this is what shows up on the console
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, EnvVars env, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        //calling the HelloWorldAction and passing in the name. from "Extend the Plugin" docs:
        //run.addAction(new HelloWorldAction(name));

        run.addAction(new FileProcessingAction());
        //calling the main function in the driver.java file

        //FileTypeDetection.FileTypeDetectionMain(null);
        try {
            FileTypeDetection.main(new String[]{});
        } catch (Exception e) {
            listener.getLogger().println("Error running FileTypeDetection: " + e.getMessage());
        }

    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {



        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }
        //display name for the Build step. will show up when adding plugin as a build step in the dashboard
        @Override
        public String getDisplayName() {
            return "File Processing Plugin";
            //return Messages.HelloWorldBuilder_DescriptorImpl_DisplayName();
        }

    }

}
