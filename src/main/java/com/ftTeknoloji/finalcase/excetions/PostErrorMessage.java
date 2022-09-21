package com.ftTeknoloji.finalcase.excetions;

public class PostErrorMessage extends RuntimeException {
    public PostErrorMessage() {
    }

    public PostErrorMessage(String message) {
        super(message);
    }
}
