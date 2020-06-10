package com.csl.guess

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)


    @Test
    fun guessWrong() {
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret

        for (n in 1..10) {

            if (n == secret)
                continue

            onView(withId(R.id.ed_number)).perform(clearText())
            onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
            onView(withId(R.id.ok_button)).perform(click())

            val message =
                if (n < secret) resources.getString(R.string.bigger)
                else resources.getString(R.string.smaller)

            onView(withText(message)).check(matches(isDisplayed()))
            onView(withText(resources.getString(R.string.ok))).perform(click())
        }
    }

    @Test
    fun replay() {


        //輸入一個錯誤數字 讓count不為0
        val n = (activityTestRule.activity.secretNumber.secret+1)%10
        onView(withId(R.id.ed_number)).perform(clearText())
        onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
        onView(withId(R.id.ok_button)).perform(click()).perform()
        Espresso.pressBack()
        Espresso.pressBack()

        onView(withId(R.id.fab)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.counter)).check(matches(withText("0")))

    }
}