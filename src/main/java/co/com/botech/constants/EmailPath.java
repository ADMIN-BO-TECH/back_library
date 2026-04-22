package co.com.botech.constants;

public enum EmailPath {
    EMAIL_PATH("emailTemplates/");
    private final String path;

    EmailPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}