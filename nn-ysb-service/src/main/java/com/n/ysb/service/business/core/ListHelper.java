package com.n.ysb.service.business.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class ListHelper {

	public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass) {  
	    if (source.size()==0) return Collections.emptyList();  
	    List<E> res = new ArrayList<E>(source.size());  
	    for (Object o : source) {  
			try {
				E e = destinationClass.newInstance();
				BeanUtils.copyProperties(o, e);  
		        res.add(e); 
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}  
	    }  
	    return res;  
	}
}
