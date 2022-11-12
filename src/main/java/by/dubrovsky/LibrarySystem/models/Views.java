package by.dubrovsky.LibrarySystem.models;

public final class Views {
    public interface IdFullNameAge {}
    public interface FullUser extends IdFullNameAge {}

    public interface IdTitleAuthor {}
    public interface FullBook extends IdTitleAuthor {}

    public interface UserBook extends IdFullNameAge, IdTitleAuthor {}
    public interface FullUserBook extends FullBook, FullUser {}
}
