package com.emrecinar.xxxx.aa.xa.x.restaurant_finder

import java.util.ArrayList

class favRest {
    var menuList: ArrayList<String>? = null
    var place: String? = null
    var rating = 0.0

    constructor(menuList: ArrayList<String>?, place: String?, rating: Double) {
        this.menuList = menuList
        this.place = place
        this.rating = rating
    }

    constructor() {}
}