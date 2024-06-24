package github.fredsonchaves07.domain.forum.entities.author;

import github.fredsonchaves07.core.entities.Entity;

public class Author extends Entity<AuthorID> {

    private String name;

    private AuthorType type;

    private Author(AuthorID authorID, String name, AuthorType type) {
        super(authorID);
        this.name = name;
        this.type = type;
    }

    private Author(String name, AuthorType type) {
        super(new AuthorID());
        this.name= name;
        this.type = type;
    }

    public static Author createAuthor(String name, AuthorType type) {
        return new Author(name, type);
    }

    public static Author createAuthor(AuthorID authorID, String name, AuthorType type) {
        return new Author(authorID, name, type);
    }
}
