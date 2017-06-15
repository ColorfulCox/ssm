package cn.yq.springmvc.vo;

import java.util.HashMap;
import java.util.Map;

public class PageRequest {
	private Long pageNumber=1L;//页码
	private Long pageSize=2L;//页
	
	//页码的请求参数名称，强制规定，所有 的分页提交参数都必须是 pn
	public static final String PAGE_NUMBER_PARAM="pn";
		
	//页码的请求参数名称，强制规定，所有 的分页提交参数都必须是 pz
	public static final String PAGE_SIZE_PARAM="pz";
		
	//PageRequest保存在requestScope中的key名称
	public static final String REQUEST_SCOPE_KEY="request_scope_key";
	
	
	//查询条件
		private Map<String,String> searchFilter=new HashMap<>();
		//添加查询条件
		public void addSerachFilter(String key,String value){
			searchFilter.put(key, value);
		}
		//获取查询条件内容
		public String getSerchFilter(String key){
			return searchFilter.get(key);
		}
		//取出查询条件集合
		public Map<String,String> getSearchFilter(){
			return searchFilter;
		}
		
	
	public PageRequest(Long pageNumber, Long pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public PageRequest() {
		
	}

	public Long getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	
}
