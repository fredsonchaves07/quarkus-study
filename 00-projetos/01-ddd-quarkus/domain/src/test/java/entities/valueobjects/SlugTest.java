package entities.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlugTest {

    @Test
    public void shouldBeAbleCreateAnewSlugFromText() {
        Slug slug = Slug.createFromText("Example question title");
        assertEquals("example-question-title", slug.value());
    }
}
