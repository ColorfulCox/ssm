package cn.yq.springmvc.dao.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType)
			throws SQLException {
		String result=StringUtils.join(parameter,',');
		ps.setString(i,result);
	}

	@Override
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String result=rs.getString(columnName);
		if(StringUtils.isNoneBlank(result)){
			return result.split(",");
		}
		return null;
	}

	@Override
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		
		String result=rs.getString(columnIndex);
		if(StringUtils.isNoneBlank(result)){
			return result.split(",");
		}
		return null;
	}

	@Override
	public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String result=cs.getString(columnIndex);
		if(StringUtils.isNoneBlank(result)){
			return result.split(",");
		}
		return null;
	}

}
