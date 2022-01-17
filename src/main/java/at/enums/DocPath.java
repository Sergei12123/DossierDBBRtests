package at.enums;

/**
 * Перечень документов и их путь до файла
 */
public enum DocPath {
    FIRSTDOCUMENT("/src/test/resources/attachments/firstDocument.png");


    private final String path;

    <T extends String> DocPath(T url) {
        this.path = url;
    }

    public String getPath() {
        return path;
    }
}
