package az.rest.domain;

import java.util.Arrays;

public class Datatable {
    private int draw;
    private  int start;
    private int length;
    private String search;
    private int sortColumn;
    private String sortDirection;
    private int recordsTotal;
    private int recordsFiltered;
    private Object[][] data;


    public Datatable() {
        this.draw=0;
        this.start=0;
        this.length=0;
        this.search="";
        this.sortColumn=0;
        this.recordsTotal=0;
        this.recordsFiltered=0;
        this.sortDirection="asc";
        this.data=null;

    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(int sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Datatable{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", search='" + search + '\'' +
                ", sortColumn=" + sortColumn +
                ", sortDirection='" + sortDirection + '\'' +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
