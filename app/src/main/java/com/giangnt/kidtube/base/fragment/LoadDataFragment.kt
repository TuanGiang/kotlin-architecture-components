package com.giangnt.kidtube.base.fragment

import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import com.giangnt.kidtube.R
import com.giangnt.kidtube.action.ActionType
import com.giangnt.kidtube.action.ClickActionCallBack
import com.giangnt.kidtube.model.MyVideo
import com.giangnt.kidtube.repo.Repo
import kotlinx.coroutines.experimental.launch

public abstract class LoadDataFragment : BaseFragment(), ClickActionCallBack {

    override fun onClickAction(id: String, type: ActionType) {
        MaterialDialog.Builder(context!!)
                .items(R.array.actions)
                .itemsCallback({ dialog, view, which, text ->
                    when (which) {
                        0 -> performAction(id, type)
                    }
                })
                .show()
    }

    protected fun performAction(id: String, type: ActionType) {
        launch {
            val repo = Repo()
            when (type) {
                ActionType.MOVIE -> {
                    repo.saveMyVideos(getContext()!!, arrayListOf(MyVideo(id))).await()
                    val datas = repo.getMyVideos(getContext()!!).await()
                    datas.forEach { Log.i("ID", it.videoId) }
                }
                ActionType.PLAYLIST -> {
                    val movieIds = repo.getMovieIdByPlayList(getContext()!!, id).await()
                    repo.saveMyVideos(getContext()!!, movieIds.map { MyVideo(it) }).await()
                }
            }

        }


    }


}