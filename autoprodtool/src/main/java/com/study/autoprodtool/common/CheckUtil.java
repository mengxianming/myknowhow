package com.study.autoprodtool.common;

import java.util.Collection;
import java.util.Map;
/**
 * チェックのツールクラス
 * 
 * @version 2013-12-11
 * @author SUNTEC
 * @since JDK1.6
 * 
 */
public class CheckUtil {
	/**
	 * オブジェクトはヌルであるかをチェックする<br>
	 * <li>オブジェクトは文字列型である場合、空文字もヌルと看做す。
	 * <li>オブジェクトは配列である場合、サイズが０である配列もヌルと看做す。
	 * <li>オブジェクトは集合クラス型(<code>Collection</code>)である場合、０サイズもヌルと看做す。
	 * <li>オブジェクトはMapクラス型(<code>Map</code>)である場合、０サイズもヌルと看做す。
	 * @param obj ヌルチェックオブジェクト
	 * @return true:ヌル、false:ヌルでない
	 */
	public static boolean isNull(Object obj ){
		if(obj == null){
			return true;
		}		
		if(obj instanceof String){
			return "".equals( obj );
		}	
		if(obj instanceof Object[]){
			return ((Object[])obj).length == 0;
		}	
		if(obj instanceof Collection){
			return ((Collection<?>)obj).size() == 0;
		}
		if(obj instanceof Map){
			return ((Map<?,?>)obj).size() == 0;
		}

		return false;
	}

}
