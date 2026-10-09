package ua.knu.classwork.cw04.task11;

public class Directory extends Book {
    private final String subjectArea;

    public Directory(String title, String author, int pages, String subjectArea) {
        super(title, author, pages);
        this.subjectArea = subjectArea;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    @Override
    public void printDetails() {
        System.out.printf("[Directory] '%s' by %s (%d pages) - Field: %s%n",
                getTitle(), getAuthor(), getPages(), subjectArea);
    }
}