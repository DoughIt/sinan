package com.doughit.sinan.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.doughit.sinan.SiNanApplication

/**
 * @Description 获取DataStore实例
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-26 17:04
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SiNanApplication.mContext.packageName + "_preferences",
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, SiNanApplication.mContext.packageName + "_preferences"))
    })