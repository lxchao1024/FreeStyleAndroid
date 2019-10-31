package com.lxchao.app.bean

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/8/27
 */
class MovieBean {
    var code: Int = 0
    var message: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        var name: String? = null
        var url: String? = null
        var imageUrl: String? = null
        var groupTitle: String? = null
    }
}
