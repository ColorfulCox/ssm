package cn.yq.springmvc.web.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;


public class PaginationTag extends SimpleTagSupport{

	private Page<?> pager=null;
	
	private static final String REQUEST_PATH_KEY="javax.servlet.forward.servlet_path";
	private static final String PAGE_NUMBER_PARAM_NAME="pageNum";
	private static final String PAGE_SIZE_PARAM_NAME="pageSize";
	
	public void setPager(Page<?> pager) {
		this.pager = pager;
	}


	@Override
	public void doTag() throws JspException, IOException {
		
		//获取流
		JspWriter out=super.getJspContext().getOut();
		
		PageContext pageContext=(PageContext)super.getJspContext();
		//获取request对象
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		//所有请求参数
		Map<String,String[]> parameters=request.getParameterMap();
		if(pager==null){
			return;
		}
		//删除页码和页大小两个参数
		parameters.remove(PAGE_NUMBER_PARAM_NAME);
		parameters.remove(PAGE_SIZE_PARAM_NAME);
		
		
		PageInfo pageInfo=new PageInfo<>(pager);
		//只有一页，不显示分页信息
		if(pageInfo.getPages()<=1){ 
			return;
		}
		
		//请求路径 最原始的请求路径放在了request范围中
		String requestPath=(String)request.getAttribute(REQUEST_PATH_KEY);
		//请求参数串
		String searchParameterUri="";
		if(parameters!=null && parameters.size()>0){
			searchParameterUri=buildSearchParameter(parameters);
		}
		
		
		
		
		StringBuilder sb=new StringBuilder();
		sb.append("<ul class='pagination no-margin'>");
		
		////////////////上一页 begin////////////////
		if(pageInfo.isHasPreviousPage()){
			sb.append("<li>");
			//上一页地址
			String fullPreUrl=requestPath+"?"+buildPageUri(pageInfo.getPrePage())+
					(StringUtils.isBlank(searchParameterUri)?"":"&"+searchParameterUri);
					
			sb.append("<a href='"+fullPreUrl+"' data-original-title='' title=''>«</a>");

		}else{
			sb.append("<li class='disabled'>");
			sb.append("<a href='#' data-original-title='' title=''>«</a>");
		}

		sb.append("</li>");
		////////////////上一页  end////////////////
		
		////////////////页码 begin////////////////
		for(int i=pageInfo.getNavigateFirstPage();i<=pageInfo.getNavigateLastPage();i++){
			//如果是当前页
			if(i==pageInfo.getPageNum()){
				sb.append("<li class='active'>");
			}else{
				sb.append("<li>");
			}
			String fullUrl=requestPath+"?"+buildPageUri(i)+
						(StringUtils.isBlank(searchParameterUri)?"":"&"+searchParameterUri);
			sb.append("<a href='"+fullUrl+"' data-original-title='' title=''>"+i+"</a>");
			sb.append("</li>");
		}
		////////////////页码 end////////////////
		
		////////////////最后一页 begin////////////////
		if(pageInfo.isHasNextPage()){
			sb.append("<li>");
			//上一页地址
			String fullPreUrl=requestPath+"?"+buildPageUri(pageInfo.getNextPage())+
					(StringUtils.isBlank(searchParameterUri)?"":"&"+searchParameterUri);
			
			sb.append("<a href='"+fullPreUrl+"' data-original-title='' title=''>»</a>");

		}else{
			sb.append("<li class='disabled'>");
			sb.append("<a href='#' data-original-title='' title=''>«</a>");
		}

		sb.append("</li>");
		
		////////////////最后一页end////////////////
		
		sb.append("</ul>");
		
		
		//输出HTML片段
		out.print(sb.toString());
	}
	
	private String buildPageUri(int number){
		return PAGE_NUMBER_PARAM_NAME+"="+number+"&"+PAGE_SIZE_PARAM_NAME+"="+pager.getPageSize();
	}
	
	
	private String buildSearchParameter(Map<String,String[]> parameters){
		StringBuilder sb=new StringBuilder();
		for (Map.Entry<String,String[]> entry : parameters.entrySet()) {
			String paramName=entry.getKey();
			String[] paramValues=entry.getValue();
			if(paramValues==null || paramValues.length==0){
				continue;
			}
			if(paramValues.length==1){
				sb.append("&");
				sb.append(buildParameterUri(paramName,paramValues[0]));
			}else if(paramValues.length>1){
				for(int i=0;i<paramValues.length;i++){
					sb.append("&");
					sb.append(buildParameterUri(paramName,paramValues[i]));
				}
			}
		}
		//如果构建出来的字符串第1个字符是 & 则删除掉
		if(sb.charAt(0)=='&'){
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
	/**
	 * 如  aa=%256EEFF
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	private String buildParameterUri(String paramName,String paramValue){
		StringBuilder sb=new StringBuilder(paramName);
		try {
			sb.append("=").append(URLEncoder.encode(paramValue,"UTF-8"));
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
		
		}
		return "";
	}
	
	
	

}
