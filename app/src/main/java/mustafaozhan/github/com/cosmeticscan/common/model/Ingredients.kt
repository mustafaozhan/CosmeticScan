package mustafaozhan.github.com.cosmeticscan.common.model

import ninja.sakib.pultusorm.annotations.PrimaryKey

/**
Created by Mustafa Özhan on 7/14/17 at 2:44 PM on Linux.

 */
data class Ingredients(
        @PrimaryKey val id: Int = -1,
        val name: String?=null,
        val information: String ?= null,
        val categori: String ?= null,
        val rating: String ?= null
)