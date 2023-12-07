package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        if(hasTestFailed(project)){
            sendEmail("Tests failed");
            return;
        }
        if(hasDeploymentFailed(project)){
            sendEmail("Deployment failed");
            return;
        }
        sendEmail("Deployment completed successfully");
    }


    private boolean hasTestFailed(Project project) {
        if (project.doesntHaveTests()) {
            log.info("No tests");
            return false;
        }

        if (project.runTests().successfully()) {
            log.info("Tests passed");
            return false;
        }

        log.error("Tests failed");
        return true;
    }

    private boolean hasDeploymentFailed(Project project) {
        if (project.deploy().successfully()) {
            log.info("Deployment successful");
            return false;
        }

        log.error("Deployment failed");
        return true;
    }

    private void sendEmail(String text) {
        if (!config.sendEmailSummary()) {
            log.info("Email disabled");
            return;
        }
        log.info("Sending email");
        emailer.send(text);
    }

}
