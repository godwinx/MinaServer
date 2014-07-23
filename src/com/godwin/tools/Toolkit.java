package com.godwin.tools;

public class Toolkit {
	
	public static byte[] hexStringToBytes(String hexString) {
		if ((hexString == null) || (hexString.equals(""))) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; ++i) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
		}
		return d;
	}
	
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	public static int akeCRC( String buf  ){
		byte[] by = hexStringToBytes(buf);
		int temp =0;
		for(int i=0 ;i<by.length; i++){
			int tmp=getUnsignedByte( by[i]);
			temp+= tmp;			
		}
		temp = getUnsignedByte((byte)temp) ;
		temp= (byte)(getNot((byte)temp));
		temp += 1;
		
		return temp;
	}

	public static int getUnsignedByte(byte data) { // 将data字节型数据转换为0~255 (0xFF 即BYTE)�?
		if( data<0){
			return data & 0x0FF;
		}
		return data;
		
	}
	public static String  getHexStringByInt( int value){
		String tmp=Integer.toHexString(getUnsignedByte((byte)value));
		if(tmp.length()<=1){
			tmp='0'+tmp;
		}
		return tmp.toUpperCase();
	}
	
	public static byte getNot(byte bt) {
		int b = 0;
		for (int j = 0; j < 8; j++) {
			int bit = (bt >> j & 1) == 0 ? 1 : 0;
			b += (1 << j) * bit;
		}
		return (byte)b;
	}
	
	// 字节数组转换16进制
	public static String byte2HexStr(byte[] b, int length) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < length; ++n) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	 
	public static String strToBinaryStr(String binaryString){
	    	String str="";
	    	String zeroStr="";
	    	for(int i=0;i<(8-binaryString.length());i++){
	    		zeroStr+="0";
	    	}
	    	str=zeroStr+binaryString;
	    	return str;
	}
	
	public static String getDateByHexString(String dateStr){
	    	String dateResult="";
	    	String year=Integer.valueOf(dateStr.substring(0,2),16)+1900+"";
	    	String month=intToString(Integer.valueOf(dateStr.substring(2,4),16));
	    	String day=intToString(Integer.valueOf(dateStr.substring(4,6),16));
	    	String hour=intToString(Integer.valueOf(dateStr.substring(6,8),16));
	    	String minute=intToString(Integer.valueOf(dateStr.substring(8,10), 16));
	    	String second=intToString(Integer.valueOf(dateStr.substring(10,12),16));
	    	dateResult=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	    	return dateResult;
	}
	
	private static  String intToString(int dateparam){
	    	String str="";
	    	if(dateparam<10)
	    	    str+=("0"+dateparam);
	    	else
	    		str+=dateparam;
	    	return str;
	}
	
	/**
	 * 判断是否数字
	 * @param str
	 * @return
	 *
	 * @author xieyj
	 * @date:Created on 2014-4-18 下午3:25:16
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 16进制转二进制
	 * @param hexString
	 * @return
	 *
	 * @author xieyj
	 * @date:Created on 2014-4-21 下午2:48:00
	 */
	public static String hexString2binaryString(String hexString)  
    {  
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000"
					+ Integer.toBinaryString(Integer.parseInt(
							hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
    }
	
	/**
	 * ip转为16进制数
	 * @param ip
	 * @return
	 *
	 * @author xieyj
	 * @date:Created on 2014-4-23 上午9:04:50
	 */
	public static String ip2Hex(String ip){
		String []ips=ip.split("\\.");
		StringBuffer ipH=new StringBuffer();
		for (int i = 0; i < ips.length; i++) {
			ipH.append(Integer.toHexString(Integer.parseInt(ips[i])));
		}
		return ipH.toString().toUpperCase();
	}
	/**
	 * 字符串转字节组
	 * @param str
	 * @return
	 *
	 * @author xieyj
	 * @date:Created on 2014-4-18 下午2:41:43
	 */
	public static byte[] str2Byte(String str){
		byte[] returnb=new byte[str.length()/2];
		
		for(int i=0;i<str.length();i+=2){
			returnb[i/2]=(byte)Integer.parseInt(str.substring(i, i+2), 16);
		}
		return returnb;
	}
}
