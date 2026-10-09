package ua.knu.classwork.cw04.task11;

public abstract class Book implements Publication {
    private final String title;
    private final String author;
    private final int pages;

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPages() {
        return pages;
    }

    public String getAuthor() {
        return author;
    }
}