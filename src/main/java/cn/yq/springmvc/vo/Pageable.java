package cn.yq.springmvc.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class Pageable<T> {

	private Long pageNumber=1L;//页码
	private Long pageSize=2L;//页大小
	private Long total;//总条数
	private List<T> element;//元素集合
	
	private String baseUrl;//获取分页信息的baseUrl 不带参数
	
	
	// 页面请求的信息，引入这个对象目的是为了获取查询条件
	private PageRequest pageRequest;


	public void setPageRequest(PageRequest pageRequest) {
			this.pageRequest = pageRequest;
	}

	
	public String getBaseUrl() {
		return baseUrl;
	}


	//获取总页数
	public Long getTotalPage(){
		//如果total/pageSize取余数等于0，则总页数就是该值
		return total%pageSize==0?total/pageSize:total/pageSize+1;
	
	}
	
	/**
	 *  是否是第一页
	 * @return
	 */
	public boolean isFirst(){
		return pageNumber==1;
	}
	
	/**
	 * 是否是最后一页
	 * @return
	 */
	public boolean isLast(){
		return pageNumber==getTotalPage();
	
	}
	
	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean isHasPrevious(){
		return pageNumber>1;
	}
	
	/**
	 * 是否有下一页
	 * @return
	 */
	
	public boolean isHasNext(){
		return pageNumber<getTotalPage();
	}
	/**
	 * 第一页的地址
	 * @return
	 */
	public String getFirstHref(){
		if(isFirst()){
			return "";
		}
		return baseUrl;
	}
	
	/**
	 * 下一页的地址
	 * @return
	 */
	public String getNextHref(){
		if(!isHasNext()){
			return "";
		}
		StringBuffer sb=new StringBuffer(baseUrl);
		sb.append("?")
		.append(PageRequest.PAGE_NUMBER_PARAM)
		.append("=")
		.append(pageNumber+1);
		return sb.toString();
	}
	
	
	/**
	 * 上一页的地址
	 * @return
	 */
	public String getPreviousHref(){
		if(!isHasPrevious()){
			return "";
		}
		StringBuffer sb=new StringBuffer(baseUrl);
		sb.append("?")
		.append(PageRequest.PAGE_NUMBER_PARAM)
		.append("=")
		.append(pageNumber-1);
		return sb.toString();
	}
	
	/**
	 * 最一页的地址
	 * @return
	 */
	public String getLastHref(){
		if(isLast()){
			return "";
		}
		StringBuffer sb=new StringBuffer(baseUrl);
		sb.append("?")
		.append(PageRequest.PAGE_NUMBER_PARAM)
		.append("=")
		.append(getTotalPage());
		return sb.toString();
	}
	/**
	 * URL方法
	 * @param baseUrl
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	private String buildSearchParam(String url){
		StringBuilder sb = new StringBuilder(url);
		// 如果url上不包含？，则追加一个
				if (!url.contains("?")) {
					sb.append("?");
				}
		Map<String,String> params = pageRequest.getSearchFilter();
		try {
		for (Map.Entry<String, String> entry : params.entrySet()) {
			//获取key和value
			String paramName = entry.getKey();
			String paramValue = entry.getValue();
			// 如果参数不是 pn而且也不是 pz, 则添加到url上，因为前面已经拼接过了
			if (!PageRequest.PAGE_NUMBER_PARAM.equals(paramName)
					&& !PageRequest.PAGE_SIZE_PARAM.equals(paramName)) {
				if (!url.endsWith("?")) {
					sb.append("&");
				}
				// 对值进行url编码
				String paramValueUrlEncode;
				
					paramValueUrlEncode = URLEncoder.encode(paramValue, "UTF-8");
				
				sb.append(paramName).append("=").append(paramValueUrlEncode);
			}
		}
		return sb.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 取出pageRequest中的所有条件，这些条件中可能包含分页的页码和页大小信息，拼接的时候，应该跳过这些参数
		// /students/hoem/index?pn=234		
		return "";
					
	
		 
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
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getElement() {
		return element;
	}
	public void setElement(List<T> element) {
		this.element = element;
	}
}
