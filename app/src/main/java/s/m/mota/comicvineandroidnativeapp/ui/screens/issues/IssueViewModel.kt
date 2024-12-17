package s.m.mota.comicvineandroidnativeapp.ui.screens.issues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import s.m.mota.comicvineandroidnativeapp.data.model.toComicIssueUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.issues.IssuesRepository
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(private val repository: IssuesRepository) : ViewModel() {
    val allIssues = repository.allIssues().cachedIn(viewModelScope)
    val allIssuesUi =
        repository.allIssues().map { it.map { it.toComicIssueUi() } }.cachedIn(viewModelScope)
}