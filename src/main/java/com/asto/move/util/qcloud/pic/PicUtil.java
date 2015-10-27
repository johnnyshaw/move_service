package com.asto.move.util.qcloud.pic;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.asto.move.constant.MoveConstant;

/**
 * Created by feixiangming on 15/8/4.
 */
public class PicUtil {

    public static void main(String[] args) {
        UploadResult result = new UploadResult();
        //上传
//        uploadPic(path, result);

        //下载
//        downloadPic(result.download_url);

        //生成私密的下载链接，绑定该图片的专属签名,签名有效时间10分钟
//        result.fileid="ef8fdc45-f0b8-45ea-ba6c-9b2bad4b0a2c";
//        result.fileid="f3d13249-6b84-4915-a743-faaae1dd2afa";
        result.fileid="b73eda72-0d88-44f1-9b85-037a2f2e09cf";
        System.out.println(MoveConstant.pc.GetDownloadUrlAuth(result.fileid));

        //生成私密的下载链接，使用未绑定资源的签名,签名有效时间10分钟
        System.out.println(MoveConstant.pc.GetDownloadUrl(result.fileid)+"?sign="+MoveConstant.pc.GetSign(600));

    }


    /***
     * 上传一张图片
     */
    public static void uploadPic(String path,UploadResult result){
        int ret = MoveConstant.pc.Upload(path, result);
        if (ret == 0) {
//            System.out.println("upload pic success");
//            result.Print();
        } else {
            System.out.println("upload pic error, error=" + MoveConstant.pc.GetError());
        }
    }


    /***
     * 下载一张图片
     */
    public static void downloadPic(String url,String fileName){
        String downUrl = url+"?sign=" + MoveConstant.pc.GetSign(600);
        int ret = MoveConstant.pc.Download(downUrl, fileName);
        if (ret == 0) {
//            System.out.println("download pic success");
        } else {
            System.out.println("download pic error, error=" + MoveConstant.pc.GetError());
        }
    }

    /***
     * 查询状态
     */
    public static  void stat(String fileid){
        int ret = MoveConstant.pc.Stat(fileid, new PicInfo());
        if (ret == 0) {
            System.out.println("Stat pic success");
        } else {
            System.out.println("Stat pic error, error=" + MoveConstant.pc.GetError());
        }
    }

    /***
     * 删除一张图片
     */
    public static void del(String fileid){
        int ret = MoveConstant.pc.Delete(fileid);
        if (ret == 0) {
            System.out.println("delete pic success");
        } else {
            System.out.println("delete pic error, error=" + MoveConstant.pc.GetError());
        }

    }
    
    public static void testFile(String url,String fileName){
		InputStream in;
		try {
			in = new FileInputStream(new File(url));
			File file = new File(fileName);
			String path = "";
			if (!file.exists()) {
				path = fileName.substring(0, fileName.lastIndexOf("/"));
				File catalogFile = new File(path);
				catalogFile.mkdirs();
			}
			DataOutputStream ops = new DataOutputStream(new FileOutputStream(
					file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) > 0) {
				ops.write(bufferOut, 0, bytes);
			}
			ops.close();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
