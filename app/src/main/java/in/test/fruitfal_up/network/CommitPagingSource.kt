package `in`.test.fruitfal_up.network

import `in`.test.fruitfal_up.response.CommitResponse
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState


class CommitPagingSource(val apiService: ApiService) :
    PagingSource<Int, CommitResponse>() {
    override fun getRefreshKey(state: PagingState<Int, CommitResponse>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommitResponse> {
        val page = params.key ?: 1
        Log.i("TAG", "page" + page)

        return try {


            val commitsList = apiService.getCommitListAPI(
                "token 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",
                "10",
                page.toString()
            )

            LoadResult.Page(
                data = commitsList,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (commitsList.isNullOrEmpty()) null else page.plus(1)
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }


}