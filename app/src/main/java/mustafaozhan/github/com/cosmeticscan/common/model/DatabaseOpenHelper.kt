package mustafaozhan.github.com.cosmeticscan.common.model

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


/**
Created by Mustafa Özhan on 7/15/17 at 12:01 PM on Linux.

 */
class DatabaseOpenHelper(private val mContext: Context) : SQLiteAssetHelper(mContext, DatabaseOpenHelper.DATABASE_NAME, null, DatabaseOpenHelper.DATABASE_VERSION) {

    companion object {


        private val DATABASE_NAME = "CosmeticScan.db"
        private val DATABASE_VERSION = 1
    }


}