package com.asto.move.util.qcloud.cos;
import org.json.JSONObject;

import com.asto.move.constant.MoveConstant;
import com.asto.move.util.qcloud.sign.Sign;

public class CosUtil {
	//通过控制台获取AppId,SecretId,SecretKey


	public static void main(String[] args) {
		try{			
//			String result = "";
//            long start = System.currentTimeMillis();
            //result = cos.getFolderList(MoveConstant.COS_BUCKETNAME, "/", 20, "", 0, CosCloud.FolderPattern.Both);
//            result = createFolder("/test3/test4_1/");
//            result = cos.uploadFile(MoveConstant.COS_BUCKETNAME, "/test/test.xlsx", MoveConstant.TEST_FILE_PATH);
//			result = cos.uploadFile(MoveConstant.COS_BUCKETNAME, "test.docx", "C://Users/Administrator/Pictures/test.docx");
			//result = cos.updateFile(MoveConstant.COS_BUCKETNAME, "/sdk/xx.txt", "test file");
            //result = cos.getFileStat(MoveConstant.COS_BUCKETNAME, "信贷工厂数据字典.xlsx");
            //result = cos.updateFolder(MoveConstant.COS_BUCKETNAME, "/sdk/", "test folder");
            //result = cos.getFolderStat(MoveConstant.COS_BUCKETNAME, "/sdk/");
            //result = cos.deleteFile(MoveConstant.COS_BUCKETNAME, "/sdk/xx.txt");
            
//            result = MoveConstant.cos.deleteFolder(MoveConstant.COS_BUCKETNAME, "/app/");

			//分片上传
            //result = cos.sliceUploadFileFirstStep(bucketName, "信贷工厂数据字典.xlsx", "/Users/feixiangming/Downloads/信贷工厂数据字典.xlsx", 512 * 1024);
//            long end = System.currentTimeMillis();
//            
//            System.out.println(result);
//			System.out.println(CosUtil.jsonUrlAnalysis(result));
//            System.out.println("总用时：" + (end - start) + "毫秒");
//			System.out.println("The End!");
//			http://docs-10002631.file.myqcloud.com/da/2014/11/2/a0fe18ee-6cda-4da8-92b9-4f81018334f0.doc
//
//				http://docs-10002631.file.myqcloud.com/da/2014/10/24/691111e1-b5a7-4bc6-a842-07f35c1d440f.xls
//
//				http://docs-10002631.file.myqcloud.com/da/2015/0/22/858bad1e-9cef-47ff-b021-b1a29b500c65.docx
//
//				http://docs-10002631.file.myqcloud.com/da/2014/11/9/d6cd0736-0f28-4391-b6c3-9b8c9f6a4724.pdf
//
//				http://docs-10002631.file.myqcloud.com/da/2015/0/22/c61eeeba-5814-4c0c-af5a-de7de16e751f.rar
//
//				http://docs-10002631.file.myqcloud.com/zlbank/18857718488/8478_20150612094954_video.avi
//				http://docs-10002631.file.myqcloud.com/zlbank/18957129917/8878_20150624195224_video.mp4
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/10/24/691111e1-b5a7-4bc6-a842-07f35c1d440f.xls"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/11/2/a0fe18ee-6cda-4da8-92b9-4f81018334f0.doc"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2015/0/22/858bad1e-9cef-47ff-b021-b1a29b500c65.docx"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/11/9/d6cd0736-0f28-4391-b6c3-9b8c9f6a4724.pdf"));
//			System.out.println(getDownUrl(MoveConstant.cos,"zlbank/18857718488/8478_20150612094954_video.avi"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2015/0/22/c61eeeba-5814-4c0c-af5a-de7de16e751f.rar"));
//			System.out.println(getDownUrl(MoveConstant.cos,"zlbank/18957129917/8878_20150624195224_video.mp4"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/10/24/28d55558-0886-4422-b07f-70006c457974.xls"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2015/9/2/26759879-d2b9-4bba-9270-2f1e071e461e.docx"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2015/9/2/630fe897-12fd-4461-8c2e-6ff8dfa16b34.rar"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2015/10/9/0fed6ad9-4274-4a01-a285-e88f0deb5c4f.jpg"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/6/31/3168505977627.jpg"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/7/14/4804340301400.jpg"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/8/18/43f9d693-3ad8-40bb-92bf-7122ca7c3bae.jpg"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/8/23/05200ddb-c5b5-4b56-b2a3-39ee9533276a.jpg"));
//			System.out.println(getDownUrl(MoveConstant.cos,"da/2014/10/24/691111e1-b5a7-4bc6-a842-07f35c1d440f.xls"));
//			System.out.println(getDownUrl(MoveConstant.cos,"zlbank/15258872295/12429_20150724153304_other.pdf"));
			System.out.println(getDownUrl(MoveConstant.cos,"/zlbank/15371259903/huachengcheng.wmv"));
			System.out.println(getDownUrl(MoveConstant.cos,"/zlbank/13712307926/yangpeng.wmv"));
			
//			/zlbank/15371259903/huachengcheng.wmv
//			/zlbank/15371259903/huachengcheng.wmv
//			/zlbank/13712307926/yangpeng.wmv
//			/zlbank/13913588617/liuchunhui.wmv
//			/zlbank/13712307926/yangpeng.wmv
//			/zlbank/13171589729/weihongman.wmv
			
			//问题数据
//			文件上传失败：/zlbank/15371259903/huachengcheng.wmv，文件大小：[7.0] MB，该条数据uniqueCode：[36af60e3-a0f5-4cbd-bfae-9a4e197a7365]
//			文件上传失败：/zlbank/15371259903/huachengcheng.wmv，文件大小：[7.0] MB，该条数据uniqueCode：[8588a9ed-05fa-41d8-bdad-3c88d914dcb5]
//			文件上传失败：/zlbank/13712307926/yangpeng.wmv，文件大小：[10.0] MB，该条数据uniqueCode：[bc38acac-48ff-4ed9-8928-4d8c24508b25]
//			文件上传失败：/zlbank/13913588617/liuchunhui.wmv，文件大小：[6.0] MB，该条数据uniqueCode：[356c1f23-e15a-47cd-aaf2-11b50885909b]
//			文件上传失败：/zlbank/13712307926/yangpeng.wmv，文件大小：[10.0] MB，该条数据uniqueCode：[3ad2392e-cfa1-4487-9826-83650e030a56]
//			文件上传失败：/zlbank/13171589729/weihongman.wmv，文件大小：[7.0] MB，该条数据uniqueCode：[2a2a733c-5d22-416d-96b1-9e3779a65849]
			
//			文件不存在：/zlbank/13213727777/15741_20150825094418_other.rar ,该条数据uniqueCode：[eaaed857-4333-4b0c-912b-1e124a49a90c]
			
			
//			文件上传失败：/da/2014/4/23/4355472655474.jpg，文件大小：[0.0] MB，该条数据uniqueCode：[f41d1cd7-da78-467a-aa2e-03e9fe613760]
//			该文件不存在：/da/2014/4/24/3363119868721.jpg ,该条数据uniqueCode：[da3333e6-1676-4e0c-b6c2-a6f470ac8f25]
//			该文件不存在：/da/2014/4/24/5121948725699.jpg ,该条数据uniqueCode：[822d49e8-affe-4814-a6a5-d29af712329c]
//			该文件不存在：/da/2014/4/24/8088138389882.jpg ,该条数据uniqueCode：[18cd1255-d808-4625-97d1-a0b256654382]
//			该文件不存在：/da/2014/4/24/7059545744997.jpg ,该条数据uniqueCode：[a00023a9-b727-47b9-a864-e5c0ca909168]
//			该文件不存在：/da/2014/4/24/9733972448806.jpg ,该条数据uniqueCode：[2e7ffd56-708d-4a1b-9187-9281d69d51de]
//			该文件不存在：/da/2014/4/24/7774910602779.jpg ,该条数据uniqueCode：[1349012d-9436-4233-aaa2-3b75aaef5469]
//			该文件不存在：/da/2014/4/24/4046571101013.jpeg ,该条数据uniqueCode：[821c7d69-3a8b-4099-9f90-5c1c59e7fa49]
//			该文件不存在：/da/2014/4/24/5886577739676.jpeg ,该条数据uniqueCode：[d4a415a2-a439-4296-bdba-4db54b3f4c17]
//			该文件不存在：/da/2014/4/24/4079566141752.jpg ,该条数据uniqueCode：[77d9509f-5304-497b-84e6-a44b9936a993]
//			该文件不存在：/da/2014/6/29/4691070451445.jpg ,该条数据uniqueCode：[16d8d2bd-b60e-42b4-9809-171c5ef8d166]
//			该文件不存在：/da/2014/6/29/1838154010668.jpg ,该条数据uniqueCode：[33814ece-3703-483e-a008-2e6e1b14af4b]
//			该文件不存在：/da/2014/6/29/4945659613836.jpg ,该条数据uniqueCode：[71d02221-838a-490f-b6a5-8210dd7519b9]
//			该文件不存在：/zlbank/13632475435/15714_20150825085335_other.png ,该条数据uniqueCode：[f4801806-e46a-4f55-a765-c9ed79821b9c]
//			该文件不存在：/zlbank/13632475435/15714_20150825085358_other.png ,该条数据uniqueCode：[06081340-da62-4179-9d06-beb3e6fd41c0]
//			该文件不存在：/zlbank/13632475435/15714_20150825085359_other.png ,该条数据uniqueCode：[f9228c1f-f21e-4496-8a71-c9c71ef0bda9]
//			该文件不存在：/da/2015/8/25/b6aef2db-afe1-4ce0-9dc0-1deeb7091134.png ,该条数据uniqueCode：[b6aef2db-afe1-4ce0-9dc0-1deeb7091134]
//			该文件不存在：/da/2015/8/25/56930be4-6388-4099-97ad-0867672c45c3.png ,该条数据uniqueCode：[56930be4-6388-4099-97ad-0867672c45c3]
//			该文件不存在：/da/2015/8/25/1b743509-976f-4651-8af6-884b190a075c.jpg ,该条数据uniqueCode：[1b743509-976f-4651-8af6-884b190a075c]
//			该文件不存在：/da/2015/8/25/f53fd78f-9039-402c-95be-d4e1e879835b.png ,该条数据uniqueCode：[f53fd78f-9039-402c-95be-d4e1e879835b]
//			该文件不存在：/da/2015/8/25/66cd1d85-48c6-4eba-ac5e-f0d21fe51919.png ,该条数据uniqueCode：[66cd1d85-48c6-4eba-ac5e-f0d21fe51919]
//			该文件不存在：/da/2015/6/26/1ee8404e-867b-4443-a4f0-e1bd74431b68.jpg ,该条数据uniqueCode：[1ee8404e-867b-4443-a4f0-e1bd74431b68]
//			该文件不存在：/da/2015/7/1/3387ddcf-3e9d-4b1f-b3bf-28fff7768d84.jpg ,该条数据uniqueCode：[3387ddcf-3e9d-4b1f-b3bf-28fff7768d84]

//			文件上传失败：/da/2014/10/11/f2a4793b-8d77-4841-98d1-e2e0e52fb87d.jpg，文件大小：[0.0] MB，该条数据uniqueCode：[f2a4793b-8d77-4841-98d1-e2e0e52fb87d]
//			文件上传失败：/da/2014/10/11/2ef402fd-f09c-4b74-a1ae-986fd7be095f.jpg，文件大小：[0.0] MB，该条数据uniqueCode：[2ef402fd-f09c-4b74-a1ae-986fd7be095f]
//			文件上传失败：/da/2015/2/16/c285b9d0-bc94-4257-92b6-b99edad70ee0.gif，文件大小：[0.0] MB，该条数据uniqueCode：[c285b9d0-bc94-4257-92b6-b99edad70ee0]
//			文件上传失败：/da/2015/8/19/94a8342a-2d5e-4a2f-ad05-bf989d936a0a.jpg，文件大小：[0.0] MB，该条数据uniqueCode：[94a8342a-2d5e-4a2f-ad05-bf989d936a0a]
//			文件上传失败：/da/2015/7/23/94caf77b-363c-45ad-9c4a-c645c4dea970.jpg，文件大小：[0.0] MB，该条数据uniqueCode：[94caf77b-363c-45ad-9c4a-c645c4dea970]


			

		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 单个文件上传
	 * @param remotePath 远程文件路径
	 * @param localPath 本地文件路径
	 * @return
	 * @throws Exception 
	 */
	public static String uploadFile(String remotePath, String localPath){
		try {
			return MoveConstant.cos.uploadFile(remotePath, localPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 单个文件上传
	 * 如果文件大于200MB，则使用分片上传，否则为普通上传
	 * @param remotePath 远程文件路径
	 * @param localPath 本地文件路径
	 * @param fileSize 文件大小
	 * @return
	 * @throws Exception 
	 */
	public static String uploadFile(String remotePath, String localPath,double fileSize){
		String result = null;
		try {
			//如果大于200MB
			if(fileSize > 200){
				System.out.println("开始使用分片上传，文件大小为：["+fileSize+" MB]");
				result = MoveConstant.cos.sliceUploadFile(remotePath, localPath);
			}else{
				result = MoveConstant.cos.uploadFile(remotePath, localPath);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 创建文件夹
	 * @param remotePath 远程文件路径
	 * @return
	 * @throws Exception 
	 */
	public static String createFolder(String remotePath){
		String code = null;
		try {
			if(remotePath != null && !"".equals(remotePath)){
				String[] pathArray = remotePath.split("/");
				String tempPath = "";
				String str = "";
				if(pathArray.length > 0){
					for (int i = 0; i < pathArray.length; i++) {
						str = pathArray[i];
						if(str != null &&!"".equals(str)){
							tempPath=tempPath+"/"+str;
							code = MoveConstant.cos.createFolder(tempPath);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	
	
	/**
	 * 解析json字符串
	 * @param result
	 * @return
	 */
	public static String jsonUrlAnalysis(String result){
		try {
			if(result != null &&!"".equals(result)){
				JSONObject jo1 = new JSONObject(result);
				JSONObject jo2 = null;
				if(jo1.has("data")){
					jo2 = new JSONObject(jo1.get("data").toString());
					return jo2.getString("access_url");
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/***
	 * 生成下载链接
	 */
	public static String getDownUrl(){
		try {
			long expired = System.currentTimeMillis() / 1000 + 2592000;
			String sign = Sign.appSignature(MoveConstant.COS_APP_ID, MoveConstant.COS_SECRET_ID, MoveConstant.COS_SECRET_KEY, expired, MoveConstant.COS_BUCKETNAME);
			String result = MoveConstant.cos.getFileStat(MoveConstant.COS_BUCKETNAME, "Linux.docx");
			System.out.println(result);
			String url = jsonUrlAnalysis(result);
			if(url != null){
				return url+"?sign="+sign;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 生成下载链接 2015-08-17
	 */
	public static String getDownUrl(CosCloud cos,String filePath){
		try {
			long expired = System.currentTimeMillis() / 1000 + 2592000;
			String sign = Sign.appSignature(MoveConstant.COS_APP_ID, MoveConstant.COS_SECRET_ID, MoveConstant.COS_SECRET_KEY, expired, MoveConstant.COS_BUCKETNAME);
			String result = cos.getFileStat(MoveConstant.COS_BUCKETNAME, filePath);
			System.out.println(result);
			String url = jsonUrlAnalysis(result);
			if(url != null){
				return url+"?sign="+sign;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
