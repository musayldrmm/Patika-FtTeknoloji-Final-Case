package com.ftTeknoloji.finalcase.excetions;

public class GetByIdErrorMessage extends NoSuchFieldException {
    public GetByIdErrorMessage() {
    }

    public GetByIdErrorMessage(String s) {
        super(s);
    }
}
