package ua.knu.classwork.cw04.task11;

public class Encyclopedia extends Book {
    private final int volumeNumber;

    public Encyclopedia(String title, String author, int pages, int volumeNumber) {
        super(title, author, pages);
        this.volumeNumber = volumeNumber;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }

    @Override
    public void printDetails() {
        System.out.printf("[Encyclopedia] '%s' by %s (%d pages) - Volume: %d%n",
                getTitle(), getAuthor(), getPages(), volumeNumber);
    }
}