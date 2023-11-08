package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.utils.ValidationHelpers;

public class CommentImpl implements Comment {

    public static final int CONTENT_LEN_MIN = 3;
    public static final int CONTENT_LEN_MAX = 200;
//    private static final String CONTENT_LEN_ERR = format(
//            "Content must be between %d and %d characters long!",
//            CONTENT_LEN_MIN,
//            CONTENT_LEN_MAX);

    private String content;
    private final String author;


    public CommentImpl(String content, String author) {
        setContent(content);
        this.author = author;

    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    private void setContent(String content) {
        ValidationHelpers.validateStringLength(content, CONTENT_LEN_MIN, CONTENT_LEN_MAX, "Content");
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("----------%n%s%nUser: %s%n"+
                "----------%n", content, author);
    }
}