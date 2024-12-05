package s.m.mota.comicvineandroidnativeapp.ui.screens.issues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.issues.IssuesRepository
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(private val repository: IssuesRepository) : ViewModel() {
    val allIssues = repository.allIssues().cachedIn(viewModelScope)
}