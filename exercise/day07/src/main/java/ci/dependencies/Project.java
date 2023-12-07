package ci.dependencies;

public class Project {
    private final boolean buildsSuccessfully;
    private final TestStatus testStatus;

    private Project(boolean buildsSuccessfully, TestStatus testStatus) {
        this.buildsSuccessfully = buildsSuccessfully;
        this.testStatus = testStatus;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public boolean doesntHaveTests() {
        return testStatus == TestStatus.NO_TESTS;
    }

    public JobStatus runTests() {
        return testStatus == TestStatus.PASSING_TESTS ? JobStatus.SUCCESS : JobStatus.FAILURE;
    }

    public JobStatus deploy() {
        return buildsSuccessfully ? JobStatus.SUCCESS : JobStatus.FAILURE;
    }

    public enum JobStatus{
        SUCCESS,
        FAILURE;

        public boolean successfully() {
            return SUCCESS.equals(this);
        }
    }

    public static class ProjectBuilder {
        private boolean buildsSuccessfully;
        private TestStatus testStatus;

        public ProjectBuilder setTestStatus(TestStatus testStatus) {
            this.testStatus = testStatus;
            return this;
        }

        public ProjectBuilder setDeploysSuccessfully(boolean buildsSuccessfully) {
            this.buildsSuccessfully = buildsSuccessfully;
            return this;
        }

        public Project build() {
            return new Project(buildsSuccessfully, testStatus);
        }
    }
}
