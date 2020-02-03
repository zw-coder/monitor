package cn.edu.dgut.utils;

import org.springframework.stereotype.Repository;

@Repository
public class ImageUtils {

	/**
	 * 获得指定文件对应的图标
	 * @param extName
	 * @return
	 */
	public static String getExtension(String extName, boolean isFile) {
		
		if(!isFile){
			return "FolderType.png";
		}
		
		if(extName == null){
			return "image_default.png";
		}
		
		
		if(extName.endsWith(".apk")){
			return "ApkType.png";
		}
		if(extName.endsWith(".cad")){
			return "CADType.png";
		}
		if(extName.endsWith(".doc") || extName.endsWith(".docx")){
			return "DocType.png";
		}
		if(extName.endsWith(".ppt") || extName.endsWith(".pptx")){
			return "PptType.png";
		}if(extName.endsWith(".xls") || extName.endsWith(".xlsx")){
			return "XlsType.png";
		}
		
		
		if(extName.endsWith(".pdf")){
			return "PdfType.png";
		}
		if(extName.endsWith(".txt")){
			return "TxtType.png";
		}
		if(extName.endsWith(".mp3")){
			return "MusicType.png";
		}
		if(extName.endsWith(".avi")){
			return "VideoType.png";
		}
		if(extName.endsWith(".ara") || extName.endsWith(".zip")){
			return "RarType.png";
		}
		return "image_default.png";
	}

	/**
	 * 文件是否是图片
	 * @param name
	 * @return
	 */
	public static boolean isImage(String name) {
		if(name == null){
			return false;
		}
		if(name.endsWith(".png")){
			return true;
		}
		if(name.endsWith(".jpg") || name.endsWith(".jpeg")|| name.endsWith(".jpe")|| name.endsWith(".jfif")){
			return true;
		}
		if(name.endsWith(".gif")){
			return true;
		}
		if(name.endsWith(".ioc")){
			return true;
		}
		if(name.endsWith(".tif")){
			return true;
		}
		if(name.endsWith(".bmp") || name.endsWith(".dib")){
			return true;
		}

		return false;
	}

}
