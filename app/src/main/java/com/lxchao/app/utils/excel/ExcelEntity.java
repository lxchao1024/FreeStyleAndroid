package com.lxchao.app.utils.excel;

import java.util.List;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/12
 */
public class ExcelEntity {
    int nums;
    List<ExcelItemEntity> lists;

    public ExcelEntity(int nums, List<ExcelItemEntity> lists) {
        this.nums = nums;
        this.lists = lists;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public List<ExcelItemEntity> getLists() {
        return lists;
    }

    public void setLists(List<ExcelItemEntity> lists) {
        this.lists = lists;
    }

}
