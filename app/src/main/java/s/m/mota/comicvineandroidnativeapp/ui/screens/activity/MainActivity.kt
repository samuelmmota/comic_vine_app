package s.m.mota.comicvineandroidnativeapp.ui.screens.activity

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen.MainScreen
import s.m.mota.comicvineandroidnativeapp.ui.theme.ComicVineAndroidNativeAppTheme
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val splashViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        installSplashScreen().apply {
            setKeepOnScreenCondition { splashViewModel.isLoading.value }
        }
        setContent {
            ComicVineAndroidNativeAppTheme {
                MainScreen()
            }
        }
    }
}