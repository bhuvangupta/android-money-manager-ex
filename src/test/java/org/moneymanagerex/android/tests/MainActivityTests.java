/*
 * Copyright (C) 2012-2015 The Android Money Manager Ex Project Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.moneymanagerex.android.tests;

import android.content.Intent;
import android.view.View;

import com.money.manager.ex.BuildConfig;
import com.money.manager.ex.Constants;
import com.money.manager.ex.R;
import com.money.manager.ex.account.AccountEditActivity;
import com.money.manager.ex.home.MainActivity;
import com.money.manager.ex.transactions.EditTransactionActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;


/**
 * Test the MainActivity.
 *
 * Created by Alen Siljak on 22/09/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTests {

    /**
     * Test the activity lifecycle in unit tests.
     * See http://robolectric.org/activity-lifecycle/
     */
    @Test
    public void activityLifecycle() {
        // ActivityController controller
        MainActivity activity  = Robolectric.buildActivity(MainActivity.class)
                .create().visible()
                .get();
        // .create().start().resume().visible() .pause().stop().destroy()
        // .restoreInstanceState(savedInstanceState)

        View addAccountButton = activity.findViewById(R.id.buttonAddAccount);
        assertNotNull("Add Account button not found", addAccountButton);
        addAccountButton.performClick();

//        ShadowActivity shadowActivity = Shadows.shadowOf(activity);

//        Intent intent = new Intent(getActivity(), AccountEditActivity.class);
//        intent.setAction(Constants.INTENT_ACTION_INSERT);

        Intent expectedIntent = new Intent(activity, AccountEditActivity.class);
        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }
}