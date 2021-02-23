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
import static org.junit.Assert.assertNotEquals;

public class MovieListViewModelTest {
    @Rule
    @JvmField
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private MovieListViewModel viewModel;
    private FakeMoviesRepo repo;

    @Before
    public void setup() {
        repo = new FakeMoviesRepo();
        viewModel = new MovieListViewModel(repo);
    }

    @Test
    public void add_successOffers_errorDetails_returnsSuccess() throws InterruptedException {
        repo.insertDummyMovieOffers_success();
        repo.insertDummyMovieDetails_success();
        viewModel.observeMovieDetailsSource(repo.getMoviesDetails(1));
        assertEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.getMovieItemsDetails()).status, Resource.Status.SUCCESS);
    }

    @Test
    public void add_successOffers_errorDetails_returnsNonSuccess() throws InterruptedException {
        repo.insertDummyMovieOffers_success();
        repo.insertDummyMovieDetails_error();
        viewModel.observeMovieDetailsSource(repo.getMoviesDetails(1));
        assertNotEquals(LiveDataTestUtil.getOrAwaitValue(viewModel.getMovieItemsDetails()).status, Resource.Status.SUCCESS);
    }
}