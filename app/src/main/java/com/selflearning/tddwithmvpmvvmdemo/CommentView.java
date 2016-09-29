package com.selflearning.tddwithmvpmvvmdemo;

import java.io.BufferedReader;
import java.util.List;

public interface CommentView {
    void setList(List<Comment> commentList);

    BufferedReader getBufferReader();
}
