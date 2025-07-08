package com.kuki.feature1.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.kuki.feature1.di.component.DaggerFeatureComponent
import com.kuki.feature1.domain.repository.FeatureRepositoryProvider
import com.kuki.feature1.domain.usecase.FetchFeatureStringUseCase
import com.kuki.presentation.theme.TestAppTheme
import javax.inject.Inject

class FeatureFragment : Fragment() {

    @Inject
    lateinit var fetchFeatureStringUseCase: FetchFeatureStringUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val featureComponent = DaggerFeatureComponent
            .builder()
            .repository(FeatureRepositoryProvider.featureRepository)
            .build()
        featureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            TestAppTheme {
                Box(
                    modifier =
                        Modifier.fillMaxSize()
                            .background(color = Color.Red)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = "Feature Fragment",
                            style = TextStyle(fontSize = 20.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                        Text(
                            text = fetchFeatureStringUseCase.invoke(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = FeatureFragment()
    }
}