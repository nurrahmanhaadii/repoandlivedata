package id.haadii.submission.dicoding.repoandlivedata.utils

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

object SelectTabIndex {
    fun selectTabAtPosition(tabIndex: Int) : ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "with tab at index $tabIndex"
            }

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex) ?:
                        throw PerformException.Builder()
                            .withCause(Throwable("No tab at index : $tabIndex"))
                            .build()

                tabAtIndex.select()
            }

            override fun getConstraints(): Matcher<View> {
                return AllOf.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.isAssignableFrom(TabLayout::class.java))
            }
        }
    }
}