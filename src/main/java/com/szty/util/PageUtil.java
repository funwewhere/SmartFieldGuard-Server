package com.szty.util;

import java.util.List;

/**
 * 分页管理器
 * 
 * 根据 pageCount,pageIndex,dataCount构造PageUtil,
 * 然后去数据库中取dataStart至dataEnd的数据,
 * 取出的数据set到PageUtil的list中
 * 
 * @author fwwer 2015-12-4
 *
 */
public class PageUtil<T> {
	//当前页数
    private int pageIndex;
    
    //总页数
    private int pageSize;
    
    //数据总条数
    private int dataCount;
    
    //每页的数据条数
    private int pageCount;
    
    //起始数据位置
    private int dataStart;
    
    //结束数据位置
    private int dataEnd;
    
    private int currentCount;
    
    //页面数据
    private List<T> list = null;
      
    public void init(){
        //根据count 和pageCount计算页数pageSize
        int pageSize_temp = (int)dataCount/pageCount;
        if(dataCount >= pageCount){
            this.pageSize = dataCount%pageCount==0?pageSize_temp:pageSize_temp+1;
        }else{
            this.pageSize = 1;
        }

        //判断页数和当前页数
//        if(pageIndex>pageSize){
//            pageIndex=pageSize;
//        }
        if(pageIndex < 1){
            pageIndex = 1;
        }

        //根据当前页计算起始和结束数据位置
        this.dataStart = (pageIndex-1) * pageCount;
        this.dataEnd = pageIndex*pageCount;
    }

    /**
     * 构造器
     * @param pageIndex 所请求页码
     * @param count 数据条目总数
     * @param pageCount 每页显示数据数目的上限
     */
    public PageUtil(int pageIndex, int count, int pageCount) {
        this.pageIndex = pageIndex;
        this.dataCount = count;
        this.pageCount = pageCount;
        this.init();
    }
    
    @Override
    public String toString() {
        return "PageBean [count=" + dataCount + ", list=" + list
                + ", pageCount=" + pageCount + ", pageIndex=" + pageIndex
                + ", pageSize=" + pageSize + ", start=" + dataStart + ", end=" + dataEnd +"]";
    }

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getDataStart() {
		return dataStart;
	}

	public void setDataStart(int dataStart) {
		this.dataStart = dataStart;
	}

	public int getDataEnd() {
		return dataEnd;
	}

	public void setDataEnd(int dataEnd) {
		this.dataEnd = dataEnd;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
		this.currentCount = list.size();
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
 
}
