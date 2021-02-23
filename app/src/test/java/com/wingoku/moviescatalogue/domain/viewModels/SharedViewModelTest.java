package com.wingoku.moviescatalogue.domain.viewModels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.wingoku.moviescatalogue.data.network.repositories.FakeMoviesRepo;
import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import kotlin.jvm.JvmField;

import static org.junit.Assert.assertEquals;

public class SharedViewModelTest {
    @Rule
    @JvmField
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private SharedViewModel viewModel;
    private FakeMoviesRepo repo;

    @Before
    public void setup() {
        repo = new FakeMoviesRepo();
        viewModel = new SharedViewModel(repo);
    }

    @Test
    public void insert_movieDetails_success() throws InterruptedException {
        repo.insertDummyMovieDetails_success();
        assertEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.fetchMoviesListData()).status, Resource.Status.SUCCESS);
    }

}