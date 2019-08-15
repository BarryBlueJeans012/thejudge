package file;

public class PathHolder {
    protected String fileName;
    protected String homeDirectory = System.getenv("HOME");
    protected String projectDirectory = homeDirectory + "/IdeaProjects/thejudge";
    protected String workingDirectory = homeDirectory + "/judge_boy";

    public String getFileName() {
        return fileName;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public String getProjectDirectory() {
        return projectDirectory;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }
}
