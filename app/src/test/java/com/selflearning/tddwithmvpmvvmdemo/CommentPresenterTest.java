package com.selflearning.tddwithmvpmvvmdemo;

import android.content.Context;

import org.junit.Before;
import org.mockito.Mockito;

public class CommentPresenterTest {

    private CommentView mockedCommentView;
    private Context mockedContext;
    private CommentRepoImpl commentRepo;

    @Before
    public void setUp() throws Exception {
        mockedCommentView = Mockito.mock(CommentView.class);
        mockedContext = Mockito.mock(Context.class);
        commentRepo = new CommentRepoImpl(mockedContext);
    }
}
