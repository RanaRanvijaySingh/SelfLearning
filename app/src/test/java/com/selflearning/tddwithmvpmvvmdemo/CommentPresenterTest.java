package com.selflearning.tddwithmvpmvvmdemo;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class CommentPresenterTest {

    private static List<Comment> comments = new ArrayList<>();

    {
        comments.add(new Comment("Rana", "Dummy body", "rana@gmail.com"));
        comments.add(new Comment("Rana1", "Dummy body1", "rana1@gmail.com"));
    }

    @Captor
    private ArgumentCaptor<retrofit2.Callback> mCallbackArgumentCaptor;

    @Mock
    private CommentView mockedCommentView;
    @Mock
    private Context mockedContext;
    @Mock
    private CommentRepoImpl commentRepo;
    private CommentPresenter mCommentPresenter;
/*

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mCommentPresenter = new CommentPresenter(mockedCommentView, commentRepo);
    }
*/

    /*@Test
    public void presentCommentsTest() {
        Assert.fail("Wait till other functions are running.");
        mCommentPresenter.presentComments();
        Mockito.verify(mockedCommentView).setList(comments);
    }*/
}
