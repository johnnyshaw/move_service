package com.asto.move.constant;

import com.asto.move.util.qcloud.cos.CosCloud;
import com.asto.move.util.qcloud.pic.PicCloud;


/**
 * 图片迁移至腾讯云常量类
 * @author xiaob
 *
 */
public class MoveConstant {

	/**
	 * 图片下载地址
	 */
	public static final String DOWNLOAD_IMAGE_URL = "";
	
	/**
	 * 文件存放路径
	 */
//	public static final String DOWNLOAD_IMAGE_SAVE_URL = "./image";
	public static final String DOWNLOAD_IMAGE_SAVE_URL = "/data/storage/protected";
//	public static final String DOWNLOAD_IMAGE_SAVE_URL = "./file";
	
	/**
	 * 测试图片地址
	 */
	public static final String TEST_IMAGE_PATH = "C:\\Users\\Administrator\\Downloads\\linux.jpg";
	
	/**
	 * 测试文件地址
	 */
	public static final String TEST_FILE_PATH = "D:\\test\\test.xlsx";
	
	/**
	 * 每页行数
	 */
	public static final int PAGE_SIZE = 1000;
	
	//万象优图正式账号
	public static final int PIC_ID_V2 = 10002631;
	public static final String PIC_SECRET_ID_V2 = "AKIDRTuGSeHqqB9kIuySAYStjw0B7eGvBJiU";
	public static final String PIC_SECRET_KEY_V2 = "STNPkOZEdWB6okxMmddyDTSezQvSpqj5";
	public static final String PIC_BUCKET = "ybpimg";        //空间名
	//测试KEY
//	public static final int PIC_ID_V2 =  10002876;
//	public static final String PIC_SECRET_ID_V2 = "AKIDXDUTUcsqH2Zcr2sji9v1Sn3QtWzOnxzP";
//	public static final String PIC_SECRET_KEY_V2 = "cpX3tG43SETjisky6iu6hUsfZXgvUPLC";
//	public static final String PIC_BUCKET = "test1";        //空间名
	public static final PicCloud pc = new PicCloud(PIC_ID_V2, PIC_SECRET_ID_V2, PIC_SECRET_KEY_V2, PIC_BUCKET);
	
	//COS正式账号
	public static final int COS_APP_ID = 10002631;
	public static final String COS_SECRET_ID = "AKIDRTuGSeHqqB9kIuySAYStjw0B7eGvBJiU";
	public static final String COS_SECRET_KEY = "STNPkOZEdWB6okxMmddyDTSezQvSpqj5";
	public static final String COS_BUCKETNAME = "docs";
//	public static final int COS_APP_ID = 10002876;
//	public static final String COS_SECRET_ID = "AKIDXDUTUcsqH2Zcr2sji9v1Sn3QtWzOnxzP";
//	public static final String COS_SECRET_KEY = "cpX3tG43SETjisky6iu6hUsfZXgvUPLC";
//	public static final String COS_BUCKETNAME = "apptest6";
	public static final CosCloud cos = new CosCloud(COS_APP_ID, COS_SECRET_ID, COS_SECRET_KEY,COS_BUCKETNAME);
	
}
