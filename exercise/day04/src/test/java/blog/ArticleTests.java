package blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ArticleTests {

    private Article article;
    public static final String COMMENT_TEXT = "Amazing article !!!";
    public static final String COMMENT_AUTHOR = "Pablo Escobar";

    @BeforeEach
    void setUp() {
        article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
    }

    @Test
    void new_comment_can_be_added_to_article() throws CommentAlreadyExistException {
        article.addComment(COMMENT_TEXT, COMMENT_AUTHOR);

        var today = LocalDate.now();
        assertThat(article.getComments()).containsExactly(new Comment(COMMENT_TEXT,COMMENT_AUTHOR,today));
    }

    @Test
    void comment_cant_be_add_to_article_twice() throws CommentAlreadyExistException {
        article.addComment(COMMENT_TEXT, COMMENT_AUTHOR);

        assertThatThrownBy(() -> article.addComment(COMMENT_TEXT, COMMENT_AUTHOR))
                .isInstanceOf(CommentAlreadyExistException.class);
    }
}
