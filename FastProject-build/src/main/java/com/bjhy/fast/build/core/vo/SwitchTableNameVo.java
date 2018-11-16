package com.bjhy.fast.build.core.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示可交换表和已经交换表的实体
 * Create by: Jackson
 */
public class SwitchTableNameVo {


    private List<TableName> TableNames = new ArrayList<>();//可以交换的表
    private List<String> changedTableNames = new ArrayList<>();//已经交换的表

    public List<TableName> getTableNames() {
        return TableNames;
    }

    public void setTableNames(List<TableName> tableNames) {
        TableNames = tableNames;
    }

    public List<String> getChangedTableNames() {
        return changedTableNames;
    }

    public void setChangedTableNames(List<String> changedTableNames) {
        this.changedTableNames = changedTableNames;
    }



    public static class TableName{

        private String tableName;
        private boolean choosed;//是否被选上

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public boolean isChoosed() {
            return choosed;
        }

        public void setChoosed(boolean choosed) {
            this.choosed = choosed;
        }
    }
}
