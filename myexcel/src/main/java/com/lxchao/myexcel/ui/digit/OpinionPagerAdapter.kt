package com.lxchao.myexcel.ui.digit

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.viewpager.widget.PagerAdapter

import com.lxchao.myexcel.R
import com.lxchao.myexcel.SpUtils
import com.lxchao.myexcel.digit.PanDuan

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/18
 */
class OpinionPagerAdapter
/**
 * 构造函数
 * 初始化上下文和数据
 *
 * @param context
 * @param list
 */
(//上下文
        private val mContext: Context, //数据
        private val mData: List<PanDuan>) : PagerAdapter() {

    /**
     * 返回要滑动的VIew的个数
     *
     * @return
     */
    override fun getCount(): Int {
        return mData.size
    }

    /**
     * 1.将当前视图添加到container中
     * 2.返回当前View
     *
     * @param container
     * @param position
     * @return
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(mContext, R.layout.fragment_item_blank, null)
        val title = view.findViewById<TextView>(R.id.tv_item_title)
        val tv = view.findViewById<TextView>(R.id.tv_item_content)
        val answer = view.findViewById<TextView>(R.id.tv_item_answer)
        val data = mData[position]
        title.text = "题目名称： " + data.题目名称
        title.text = "题目名称： " + data.题目名称
        var mContent = "问题：\n" + data.题目内容
        tv.text = mContent

        answer.text = "答案：" + data.答案
        val pos = view.findViewById<TextView>(R.id.tv_item_pos)
        pos.text = "当前进度：${position + 1} / ${mData.size}"

        SpUtils.put(SpUtils.KEY_DIGIT_OPINION, position)

        container.addView(view)
        return view
    }

    /**
     * 从当前container中删除指定位置（position）的View
     *
     * @param container
     * @param position
     * @param object
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView(`object` as View)
    }

    /**
     * 确定页视图是否与特定键对象关联
     *
     * @param view
     * @param object
     * @return
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}