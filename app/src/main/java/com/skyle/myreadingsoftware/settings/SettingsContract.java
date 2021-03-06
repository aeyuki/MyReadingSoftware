/*
 * Copyright 2017 lizhaotailang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skyle.myreadingsoftware.settings;

import android.support.v7.preference.Preference;

import com.skyle.myreadingsoftware.BasePresenter;
import com.skyle.myreadingsoftware.BaseView;


/**
 * Created by Lizhaotailang on 2016/9/5.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void showCleanGlideCacheDone();

    }

    interface Presenter extends BasePresenter {

        void setNoPictureMode(Preference preference);

        void setInAppBrowser(Preference preference);

        void cleanGlideCache();

        void setTimeOfSavingArticles(Preference preference, Object newValue);

        String getTimeSummary();

    }

}
