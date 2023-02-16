package io.jenkins.plugins.fileProcessor;

import hudson.Launcher;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

public class HelloWorldBuilder extends Builder implements SimpleBuildStep {
    //variables for Parameters: name, userFrench
    private final String name;
    private boolean useFrench;

    //constructor for the input parameter "name"
    @DataBoundConstructor
    public HelloWorldBuilder(String name) {
        this.name = name;
    }
    //getter for paramter "name"
    public String getName() {
        return name;
    }
    //getter for useFrench boolean
    public boolean isUseFrench() {
        return useFrench;
    }
    //setter that checks for useFrench parameter
    @DataBoundSetter
    public void setUseFrench(boolean useFrench) {
        this.useFrench = useFrench;
    }

    //I think this is what shows up on the console
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, EnvVars env, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        //calling the HelloWorldAction and passing in the name. from "Extend the Plugin" docs:
        run.addAction(new HelloWorldAction(name));

//        if (useFrench) {
//            listener.getLogger().println("Bonjour, " + name + "!");
//        } else {
//            listener.getLogger().println("Hello, " + name + "!");
//        }
//        return false;
    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value, @QueryParameter boolean useFrench)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages.HelloWorldBuilder_DescriptorImpl_errors_missingName());
            if (value.length() < 4)
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_tooShort());
            if (!useFrench && value.matches(".*[éáàç].*")) {
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_reallyFrench());
            }
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }
        //display name for the Build step. will show up when adding plugin as a build step in the dashboard
        @Override
        public String getDisplayName() {
            return Messages.HelloWorldBuilder_DescriptorImpl_DisplayName();
        }

    }

}
