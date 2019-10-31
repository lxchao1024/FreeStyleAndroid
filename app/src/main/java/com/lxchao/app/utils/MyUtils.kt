package com.lxchao.app.utils

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText
import android.widget.Toast

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/9/26
 */
object MyUtils {
    /**
     * 输入框长度过滤器
     * @param context       上下文
     * @param editText      输入框对象
     * @param maxLength     最大长度
     * @param errMsg        上限信息
     */
    fun lengthFilter(context: Context, editText: EditText, maxLength: Int, errMsg: String) {
        val filters = arrayOfNulls<InputFilter>(1)
        filters[0] = object : InputFilter.LengthFilter(maxLength) {
            override fun filter(source: CharSequence, start: Int, end: Int,
                                dest: Spanned, dstart: Int, dend: Int): CharSequence {
                //获取字符个数(一个中文算2个字符)
                val destLen = getCharacterNum(dest.toString())
                val sourceLen = getCharacterNum(source.toString())
                if (destLen + sourceLen > maxLength) {
                    Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
                    return ""
                }
                return source
            }
        }
        editText.filters = filters
    }

    /**
     * @description 获取一段字符串的字符个数（包含中英文，一个中文算2个字符）
     * @param content
     * @return
     */
    fun getCharacterNum(content: String?): Int {
        return if (null == content || "" == content) {
            0
        } else {
            content.length + getChineseNum(content)
        }
    }

    /**
     * @description 返回字符串里中文字或者全角字符的个数
     * @param s
     * @return
     */
    fun getChineseNum(s: String): Int {
        var num = 0
        val myChar = s.toCharArray()
        for (i in myChar.indices) {
            if (myChar[i].toByte().toChar() != myChar[i]) {
                num++
            }
        }
        return num
    }
}
