/*
 * Copyright (C),2016-2016. ��ס�Ƶ�������޹�˾
 * FileName: DynamicField.java
 * Author:   admin
 * Date:     2016-03-13 14:28:45
 * Description: //ģ��Ŀ�ġ���������
 * History: //�޸ļ�¼ �޸������� �޸�ʱ�� �汾�� ����
 * <admin>  <2016-03-13 14:28:45> <version>   <desc>
 *
 */

package com.seiko.base.model;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;


public class DynamicField extends BaseObject{

	@Transient
	private Map<String,Object> fields;

	public DynamicField(){
		fields = new HashMap<String, Object>();
	}

	public void addField(String name,Object value){
		fields.put(name, value);
	}
	public Map<String,Object> getFields(){
		return fields;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


}
