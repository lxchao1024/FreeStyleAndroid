package com.lxchao.app.utils.excel;

import java.util.List;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/12
 */
public interface ExcelInfoListener {
    void backExcelInfo(boolean isTrue, List<ExcelEntity> listsContent);
}
