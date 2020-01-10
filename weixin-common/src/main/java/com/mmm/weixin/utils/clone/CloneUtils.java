package com.mmm.weixin.utils.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 克隆工具类，进行深克隆,包括对象、集合
 * 
 * @Author:chenssy
 * @date:2014年8月9日
 */
public class CloneUtils {

	/**
	 * 采用对象的序列化完成对象的深克隆
	 * @autor:chenssy
	 * @date:2014年8月9日
	 *
	 * @param obj
	 * 			待克隆的对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T cloneObject(T obj) {
		T cloneObj = null;
		try {
			// 写入字节流
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream obs = new ObjectOutputStream(out);
			obs.writeObject(obj);
			obs.close();

			// 分配内存，写入原始对象，生成新对象
			ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(ios);
			// 返回生成的新对象
			cloneObj = (T) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cloneObj;
	}
	
	/**
	 * 利用序列化完成集合的深克隆
	 * @autor:chenssy
	 * @date:2014年8月9日
	 *
	 * @param collection
	 * 					待克隆的集合
	 * @return
	 * @throws ClassNotFoundException
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> cloneCollection(Collection<T> collection) throws ClassNotFoundException, IOException{
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
	    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
	    out.writeObject(collection);
	    out.close();
	  
	    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
	    ObjectInputStream in = new ObjectInputStream(byteIn);  
	    Collection<T> dest = (Collection<T>) in.readObject();  
	    in.close();
	    
	    return dest;  
	}
	
	/**
     * @param orig  源对象
     * @param dest  目标对象
	 * @throws Exception 
     */
    public static void copyProperties(final Object orig,final Object dest) throws Exception{
        try{
            BeanUtils.copyProperties(dest, orig);
        }catch (Exception e){
            throw e;
        }
    }
 
    /**
     * @Description：拷贝list元素对象，将origs中的元素信息，拷贝覆盖至dests中
     * @param origs 源list对象
     * @param dests 目标list对象
     * @param origsElementTpe 源list元素类型对象
     * @param destElementTpe 目标list元素类型对象
     * @param <T1> 源list元素类型
     * @param <T2> 目标list元素类型
     * @throws Exception 
     */
    public static <T1,T2> void copyProperties(final List<T1> origs, final List<T2> dests, Class<T1> origsElementTpe, Class<T2> destElementTpe) throws Exception{
        if(origs==null||dests==null){
            return ;
        }
        if(dests.size()!=0){
            //防止目标对象被覆盖，要求必须长度为零
            throw new RuntimeException("目标对象存在值");
        }
        try{
            for (T1 orig: origs) {
                T2 t = destElementTpe.newInstance();
                dests.add(t);
                copyProperties(orig,t);
            }
        }catch (Exception e){
            throw e;
        }
    }
}
