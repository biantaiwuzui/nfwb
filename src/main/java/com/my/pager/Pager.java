package pager;

import java.io.Serializable;
import java.util.List;

public class Pager implements Serializable {
	private static final long serialVersionUID = 6147935339713952592L;
	private int pageSize;//每页显示多少条数据
	private int currentPage;//当前第几页数据
	private int totalRecord;//一共多少条记录
	private int totalPage;//一共多少页记录
	private List dateList;//要显示的数据

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Pager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pager(int pageNum,int pageSize,List list) {
		if(list==null){
			return;
		}
		//总记录条数
		this.totalRecord=list.size();
		//每页显示多少条记录
		this.pageSize=pageSize;
		//获取总页数
		this.totalPage=this.totalRecord/this.pageSize;
		if(this.totalRecord%this.pageSize!=0){
			this.totalPage+=1;
		}
		//当前第几页数据
		this.currentPage=this.totalPage<pageNum?this.totalPage:pageNum;
		/*if(this.totalPage<pageNum){
			this.currentPage=this.totalPage;
		}else{
			this.currentPage=pageNum;
		}*/

		//起始索引
		int fromIndex = this.pageSize * (this.currentPage -1);
		//结束索引
		int toIndex=this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
		/*if(this.pageSize*this.currentPage>this.totalRecord){
			toIndex=this.totalRecord;
		}else{
			toIndex=this.pageSize*this.currentPage;
		}*/
		this.dateList=list.subList(fromIndex, toIndex);
	}
	public Pager(int pageSize,int currentPage, int totalRecord, int totalPage, List dateList) {
		super();
		this.pageSize=pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dateList = dateList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getDateList() {
		return dateList;
	}
	public void setDateList(List dateList) {
		this.dateList = dateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
