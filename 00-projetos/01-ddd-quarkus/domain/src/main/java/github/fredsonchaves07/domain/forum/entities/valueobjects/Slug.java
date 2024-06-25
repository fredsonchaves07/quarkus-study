package github.fredsonchaves07.domain.forum.entities.valueobjects;

import java.util.Objects;
import java.text.Normalizer;

public record Slug(String value) {

    public static Slug createFromText(String value) {
        String slugText = Normalizer
                .normalize(Objects.requireNonNull(value), Normalizer.Form.NFD)
                .toLowerCase()
                .trim()
                .replaceAll(" ", "-");
        return new Slug(slugText);
    }

    public static Slug createFromSlug(String slug) {
        return new Slug(slug);
    }

    @Override
    public String toString() {
        return value;
    }
}
