package com.zb.byb.common;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 公共函数类库
 * @author xieli
 */
public class CommonFunc
{
	/**
	 * 浮点型进位（"0"）
	 */
	public static final int FLOAT_CARRY_NUMBER_ZERO = 0;
	/**
	 * 浮点型进位（"0.00"）
	 */
	public static final int FLOAT_CARRY_NUMBER_TWO = 2;
	/**
	 * 浮点型进位（"0.0000"）
	 */
	public static final int FLOAT_CARRY_NUMBER_FOUR = 4;
	/**
	 * 浮点型零值（判断"0.0"值）
	 */
	public static final double FLOAT_ZERO_VALUE = 0.0000001;

	/**
	 * 创建唯一GUID
	 * @return
	 */
	public static String newGuid()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 检测字符串是否为null或空字符串
	 * @param str 需要检测的字符串
	 * @return 如果字符串为null或空字串或trim()后为空字串：true；否则返回false
	 */
	public static boolean checkNullOrEmpty(String str)
	{
		if (str == null)
		{
			return true;
		}

		return str.trim().length() <= 0;
	}
	
	/**
	 * 检查对象是否是duoble类型
	 * @param obj
	 * @return
	 */
	public static boolean checkDouble(Object obj) 
	{
		if (!checkNullOrEmpty(obj))
		{
			String number = obj.toString();
			
			if (number.equals("0")) 
			{
				return true;
			}
			
			return CommonFunc.parseDbl(number) != 0;
		}
		else
		{
			return false;
		}
		
	}

	/**
	 * 检测字符串是否为null或空字符串
	 * @param str 需要检测的字符串
	 * @return 如果字符串为null或空字串或trim()后为空字串：true；否则返回false
	 */
	public static boolean checkNullOrEmpty(Object str)
	{
		if (str == null)
		{
			return true;
		}

		return parseStr(str).length() <= 0;
	}

	/**
	 * 检测字符串是否为null或空字符串或“null”字符串
	 * @param str 需要检测的字符串
	 * @return 如果字符串为null或空字串或trim()后为空字串：true；否则返回false
	 */
	public static boolean checkNull(String str)
	{
		if (str == null || "null".equals(str) || "undefined".equals(str))
		{
			return true;
		}

		return str.trim().length() <= 0;
	}

	/**
	 * 解析字符型数据
	 */
	public static String parseStr(Object objIn)
	{
		if (objIn == null)
		{
			return "";
		}

		return objIn.toString().trim();
	}
	
	public static String parseStr(BigDecimal decimal)
	{
		if (decimal == null || decimal.doubleValue() == 0.0)
		{
			return "";
		}
		return decimal.toString();
	}

	/**
	 * 解析整型数据
	 */
	public static int parseInt(Object objIn)
	{
		return (int) CommonFunc.parseDbl(objIn);
	}

	/**
	 * 解析长整型数据
	 */
	public static long parseLong(Object objIn)
	{
		return (long) CommonFunc.parseDbl(objIn);
	}

	/**
	 * 解析单精度浮点型数据
	 */
	public static float parseFloat(Object objIn)
	{
		float flt = 0.0f;
		if (objIn == null)
		{
			return flt;
		}

		// 尝试解析浮点型数据
		try
		{
			flt = Float.parseFloat(CommonFunc.parseStr(objIn));
		}
		catch (Exception e)
		{
		}
		return flt;
	}

	/**
	 * 解析双精度浮点型数据
	 */
	public static double parseDbl(Object objIn)
	{
		double dbl = 0.0;
		if (objIn == null)
		{
			return dbl;
		}

		// 尝试解析浮点型数据
		try
		{
			dbl = Double.parseDouble(CommonFunc.parseStr(objIn));
		}
		catch (Exception e)
		{
		}
		return dbl;
	}

	private static DecimalFormat df = new DecimalFormat("#.##########");

	/**
	 * 设置小数位数
	 * @param data 需要转换的数据
	 * @param round 有效小数位数（默认为两位）
	 * @return
	 */
	public static String setScale(Object data, int... round)
	{
		double re = 0d;
		try
		{
			re = parseDbl(data);
		}
		catch (Exception e)
		{
			return parseStr(data);
		}
		if (re == 0d)
		{
			return "0";
		}

		if (round.length <= 0)
		{
			round = new int[] { 2 }; // 默认取两位小数
		}
		try
		{
			return df.format(BigDecimal.valueOf(re).setScale(round[0], BigDecimal.ROUND_HALF_UP));
		}
		catch (Exception e)
		{
		}
		return "";
	}

	
	

	/**
	 * 格式化时间
	 * @param objIn
	 * @return
	 */
	public static String parseDateToString(Object objIn)
	{
		if (objIn == null)
		{
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(objIn);
	}

	/**
	 * 解析布尔数据
	 */
	public static boolean parseBoolean(Object objIn)
	{
		return parseBoolean(objIn, false);
	}

	/**
	 * 解析布尔数据
	 */
	public static boolean parseBoolean(Object objIn, boolean defaultValue)
	{
		boolean bRe = defaultValue;
		if (objIn == null)
		{
			return bRe;
		}

		// 尝试解析布尔型数据
		try
		{
			bRe = Boolean.parseBoolean(CommonFunc.parseStr(objIn));
		}
		catch (Exception e)
		{
		}
		return bRe;
	}

	public static String getListSplitStr(List<String> list, String split)
	{
		if (list == null || checkNullOrEmpty(split))
		{
			return "";
		}
		StringBuffer strBuff = new StringBuffer();
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();)
		{
			String str = iterator.next();
			if (strBuff.length() > 0)
			{
				strBuff.append((new StringBuilder(String.valueOf(split))).append(str).toString());
			}
			else
			{
				strBuff.append(str);
			}
		}

		return strBuff.toString();
	}

	public static String getListSplitSqlStr(List<String> list, String split)
	{
		if (list == null || checkNullOrEmpty(split))
		{
			return "";
		}
		StringBuffer strBuff = new StringBuffer();
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();)
		{
			String str = iterator.next();
			if (strBuff.length() > 0)
			{
				strBuff.append((new StringBuilder(String.valueOf(split))).append("'").append(str).append("'").toString());
			}
			else
			{
				strBuff.append((new StringBuilder("'")).append(str).append("'").toString());
			}
		}

		return strBuff.toString();
	}



	/**
	 * 将传入的Params的filter字符串转换成json字符串
	 * @param filter 例： usrid=1,name=myname,code=123
	 * @param splitStr 字段间分隔符(此处为",")
	 * @return
	 */
	public static Map<String, String> transferJsonToMap(String filter, String splitStr)
	{
		Map<String, String> map = new HashMap<String, String>();
		// 根据“&”切割,获取各属性名称及值
		String[] items = filter.split(splitStr);
		if (items.length <= 0)
		{
			return map;
		}

		String[] tmpItem = null;
		for (String tmpStr : items)
		{
			// 根据“=”切割,划分名称与值
			tmpItem = tmpStr.split("=");
			if (tmpItem.length <= 1)
			{
				continue;
			}

			// 加入map
			map.put(tmpItem[0], tmpItem[1]);
		}

		return map;
	}

	/**
	 * 将传入的Params的filter字符串转换成json字符串
	 * @param filter 例： usrid:1,name:myname,code:123
	 * @param fieldSplit 字段间分隔符(此处为",")
	 * @param valueSplit 字段值分割符(此处为":")
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> transferStringToMap(String filter, String fieldSplit, String valueSplit)
	{
		Map map = new HashMap<String, String>();
		// 根据“&”切割,获取各属性名称及值
		String[] items = filter.split(fieldSplit);
		if (items.length <= 0)
		{
			return map;
		}

		String[] tmpItem = null;
		for (String tmpStr : items)
		{
			// 根据“=”切割,划分名称与值
			tmpItem = tmpStr.split(valueSplit);
			if (tmpItem.length <= 1)
			{
				continue;
			}

			// 加入map
			map.put(tmpItem[0], tmpItem[1]);
		}

		return map;
	}


	/**
	 * 获取文件对应无后缀的文件名称
	 * @param file
	 * @return
	 */
	public static String getFileName(File file)
	{
		if (file == null)
		{
			return "";
		}

		String fileName = "";
		if (file.getName().lastIndexOf(".") >= 0)
		{
			fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
		}
		else
		{
			fileName = file.getName();
		}

		return fileName;
	}

	/**
	 * 获取文件对应无后缀的文件名称
	 * @param file
	 * @return
	 */
	public static String getFileExtName(String fileName)
	{
		if (CommonFunc.checkNullOrEmpty(fileName))
		{
			return "";
		}

		String fileExtName = "";
		if (fileName.lastIndexOf(".") >= 0)
		{
			fileExtName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}

		return fileExtName;
	}

	/**
	 * 检查浮点型数据是否为"0.0"值
	 */
	public static boolean checkDblZero(double dblIn)
	{
		return (dblIn < CommonFunc.FLOAT_ZERO_VALUE) && (dblIn > -CommonFunc.FLOAT_ZERO_VALUE);
	}

	/**
	 * 解析浮点型字符串(对应值为零则返回空)
	 */
	public static String parseDblStr(Object objIn, int carryNum, boolean bDisplayZero)
	{
		double dbl = CommonFunc.round(objIn, carryNum);

		if (!bDisplayZero && CommonFunc.checkDblZero(dbl))
		{
			return "";
		}

		// 进行精度处理
		DecimalFormat decFormat = new DecimalFormat();
		decFormat.setMinimumFractionDigits(carryNum);
		decFormat.setMaximumFractionDigits(carryNum);
		if (carryNum == 2)
		{
			decFormat.applyPattern("0.00");
		}
		else
		{
			decFormat.applyPattern("#.#");
		}
		return decFormat.format(dbl);
	}

	/**
	 * 获取常量类常量列表
	 * @return
	 */
	public static List<String> getConstFieldList(Class<?> clasz) throws Exception
	{
		if (clasz == null)
		{
			return null;
		}
		Field[] field = clasz.getDeclaredFields();
		List<String> constStrList = new ArrayList<String>();
		// 获取常量字段
		for (int i = 0; i < field.length; i++)
		{
			constStrList.add(field[i].get(null).toString());
		}

		return constStrList;
	}

	/**
	 * 加法运算（准确计算精度）
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static double add(Object o1, Object o2)
	{
		try
		{
		    // 为空，默认赋予0值
		    if (o1 == null)
		    {
		        o1 = 0;
		    }
		    if (o2 == null)
		    {
		        o2 = 0;
		    }
			BigDecimal b1 = new BigDecimal(parseStr(o1));
			BigDecimal b2 = new BigDecimal(parseStr(o2));
			BigDecimal result = b1.add(b2);
			if (result != null)
			{
				return result.doubleValue();
			}
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	/**
	 * 减法运算（准确计算精度）
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static double subtract(Object o1, Object o2)
	{
		try
		{
			BigDecimal b1 = new BigDecimal(parseStr(o1));
			BigDecimal b2 = new BigDecimal(parseStr(o2));
			BigDecimal result = b1.subtract(b2);
			if (result != null)
			{
				return result.doubleValue();
			}
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	/**
	 * 乘法运算（准确计算精度）
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static double multiply(Object o1, Object o2)
	{
		try
		{
			BigDecimal b1 = new BigDecimal(parseStr(o1));
			BigDecimal b2 = new BigDecimal(parseStr(o2));
			BigDecimal result = b1.multiply(b2);
			if (result != null)
			{
				return result.doubleValue();
			}
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	/**
	 * 除法运算（准确计算精度）
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static double divide(Object o1, Object o2, int... len)
	{

		try
		{
			int i = 10; // 默认取两位
			if (len.length > 0)
			{
				i = len[0];
			}

			BigDecimal b1 = new BigDecimal(parseStr(o1));
			BigDecimal b2 = new BigDecimal(parseStr(o2));
			BigDecimal result = b1.divide(b2, i, BigDecimal.ROUND_HALF_UP);
			if (result != null)
			{
				return result.doubleValue();
			}
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	/**
	 * 四舍五入运算
	 * @param o1
	 * @return
	 */
	public static double round(Object o1, int... round)
	{

		try
		{
			int len = 2; // 默认取两位
			double v = 1;
			if (round.length > 0)
			{
				len = round[0];
			}
			for (int i = 0; i < len; i++)
			{
				v *= 10;
			}
			BigDecimal b = new BigDecimal(parseStr(o1));
			BigDecimal val = new BigDecimal(v);
			BigDecimal result = b.multiply(val).divide(val, len, BigDecimal.ROUND_HALF_UP);
			if (result != null)
			{
				return result.doubleValue();
			}
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	/**
	 * 设置数值小数位数
	 * @param dbl
	 * @param carryNum
	 * @return
	 */
	public static double setDoubleScale(double dbl, int carryNum)
	{
		if (carryNum < 0)
		{
			return dbl;
		}

		return BigDecimal.valueOf(dbl).setScale(carryNum, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 通过枚举值获取对应的枚举
	 * @param <E> 枚举泛型
	 * @param enumType 枚举类型
	 * @param convertValue 需要转换的值
	 * @param defaultValue 转换失败的默认值
	 * @return
	 */
	public static <E extends Enum<E>> E getEnumByName(Class<E> enumType, Object convertValue, E defaultValue)
	{
		if (convertValue == null)
		{
			return defaultValue;
		}

		String convertStr = CommonFunc.parseStr(convertValue);
		// 遍历枚举类型名称集合
		for (E item : EnumSet.allOf(enumType))
		{
			if (item.name().equals(convertStr))
			{
				return item;
			}
		}

		return defaultValue;
	}

	

	/**
	 * 尝试转换Double值数据
	 * @param parseObject
	 * @return
	 */
	public static double tryParseDouble(String parseObject)
	{
		double parseValue = Double.MIN_VALUE;
		if (parseObject == null)
		{
			return parseValue;
		}

		try
		{
			// 尝试转换值
			parseValue = Double.valueOf(parseObject);
			return parseValue;
		}
		catch (Exception e)
		{
			return parseValue;
		}
	}

	/**
	 * 解析浮点型字符串(对应值为零则返回空)
	 */
	public static String parseDblStr(Object objIn, int carryNum)
	{
		// 当为0的时候显示为空
		if (CommonFunc.parseDbl(objIn) == 0.0D)
		{
			return "";
		}
		return CommonFunc.setScale(objIn, carryNum);

		// Double dbl = Func.parseDbl(objIn);
		// if (CommFunc.CheckDblZero(dbl)) return "";
		//
		// // 进行精度处理
		// DecimalFormat decFormat = new DecimalFormat();
		// decFormat.setMinimumFractionDigits(carryNum);
		// decFormat.setMaximumFractionDigits(carryNum);
		// return decFormat.format(dbl);
	}

	public static boolean deleteFile(String sPath)
	{
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists())
		{
			flag = file.delete();
		}
		return flag;
	}

	/**
	 * 删除目录（所有）
	 * @param sPath
	 * @return
	 */
	public static boolean deleteDirectory(String sPath)
	{
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator))
		{
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory())
		{
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			// 删除子文件
			if (files[i].isFile())
			{
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
				{
					break;
				}
			} // 删除子目录
			else
			{
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
				{
					break;
				}
			}
		}
		if (!flag)
		{
			return false;
		}
		// 删除当前目录
		return dirFile.delete();
	}

	public static boolean deleteDirFile(String sPath)
	{
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator))
		{
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory())
		{
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			// 删除子文件
			if (files[i].isFile())
			{
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
				{
					break;
				}
			} // 删除子目录
			else
			{
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
				{
					break;
				}
			}
		}
		if (!flag)
		{
			return false;
		}

		return true;
	}



	/**
	 * 检测文件是否为Excel格式
	 * @param fileFullName
	 * @return
	 */
	public static boolean checkExcelFile(String fileFullName)
	{
		if (fileFullName == null || fileFullName.equals("")) 
		{
			return false;
		}
		if (fileFullName.endsWith(".xls")) 
		{
			return true;
		}
		if (fileFullName.endsWith(".xlsx")) 
		{
			return true;
		}

		return false;
	}

	/**
	 * 编码成安全XML字符串
	 * @param obj
	 * @return
	 */
	public static String encodeSafeXML(Object obj)
	{
		String content = CommonFunc.parseStr(obj);
		content = content.replace("&", "&amp;");
		content = content.replace("\r", "");
		content = content.replace("\n", "");
		content = content.replace(">", "&gt;");
		content = content.replace("<", "&lt;");
		content = content.replace("\'", "&apos;");
		content = content.replace("\"", "&quot;");
		content = content.replace(String.format("%s", (char) 32), "&nbsp;");
		content = content.replace(String.format("%s", (char) 9), "&nbsp;");
		content = content.replace(String.format("%s", (char) 34), "&quot;");
		content = content.replace(String.format("%s", (char) 39), "&apos;");
		content = content.replace(String.format("%s", (char) 13), "");
		// 过滤十六进制数据
		// content = Regex.replace(content,
		// "[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "*");

		return content;
	}
	
	/**
	 * sql 字符串处理
	 * @param sIn
	 * @return
	 */
	public static String sqlStr(String sIn)
	{
		if (sIn == null)
		{
			sIn = "";
		}

		String str = sIn.replace("'", "\"");
		
		return "'" + str + "' ";
	}
	
	/**
	 * sql 字符串处理
	 * @param objIn
	 * @return
	 */
	public static String sqlStr(Object objIn)
	{
		
		return sqlStr(CommonFunc.parseStr(objIn));
	}

}
