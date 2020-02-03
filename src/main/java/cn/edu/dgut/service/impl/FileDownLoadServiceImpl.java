package cn.edu.dgut.service.impl;

import cn.edu.dgut.service.FileDownLoadService;
import cn.edu.dgut.utils.MyStringUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileDownLoadServiceImpl implements FileDownLoadService {
    @Override
    public void fileDownLoad(HttpServletRequest request, HttpServletResponse response,String id)throws ServletException, IOException{
        File file = new File(id);
        String filename = "";
        if(file.isDirectory()){
            //打包
            filename = file.getName() + ".zip";
            file = compressDirToFile(file);
        } else {
            filename = file.getName();
        }
        filename = new String(filename.getBytes(),"ISO-8859-1");

        response.setHeader("content-disposition", "attachment;filename=" + filename);
        FileUtils.copyFile(file, response.getOutputStream());
    }
    /**
     * 压缩目录
     * @param dirPath 目录位置
     * @param fileName 文件名（不含扩展名）
     * @return
     * @throws ArchiveException
     * @throws FileNotFoundException
     */
    HttpServletRequest request;
    private File compressDirToFile(File dirFile) {
        try {
            String parentDir = request.getRealPath("/WEB-INF/download");
            String dirPath = dirFile.getCanonicalPath();
            ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext");
            String fileName = MyStringUtils.getMD5Value(dirPath);
            File zipFile = new File(parentDir, fileName + ".zip");
            if(zipFile.exists()){
                return zipFile;
            }
            //父目录不存在
            if(!zipFile.getParentFile().exists()){
                zipFile.getParentFile().mkdirs();
            }
            ArchiveOutputStream zipOut = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP,new FileOutputStream(zipFile));
            write(dirPath,dirPath, zipOut);
            zipOut.close();
            return zipFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加一个压缩文件
     * @param dirPath
     * @param zipOut
     * @throws IOException
     */
    private void write(String rootPath , String dirPath, ArchiveOutputStream zipOut) throws IOException {
        //1 压缩文件封装对象
        ArchiveEntry entry = null;
        //2 需要压缩目录
        File dir = new File(dirPath);

        //3 遍历所有的内容
        for(File file : dir.listFiles()){
            // 处理文件名。例如 /a/b/c/  或 /a/b/c/1.txt
            String relativeName = file.getCanonicalPath().substring(rootPath.length());
            if(file.isDirectory()){
                relativeName = relativeName + File.separator;
            }
            entry = new ZipArchiveEntry(relativeName);

            //添加到zip文件中
            zipOut.putArchiveEntry(entry);

            //如果是目录，只有名称
            if(file.isDirectory()){
                zipOut.closeArchiveEntry();
                //继续写入文件夹中内容
                write(rootPath, file.getCanonicalPath() ,zipOut);
                continue;
            }

            //如果是文件，需要写入内容
            IOUtils.copy(new FileInputStream(file), zipOut);
            zipOut.closeArchiveEntry();

        }

    }


    private static void write(File root , File parent, ArchiveOutputStream zipOut)
            throws IOException {
        for(File file : parent.listFiles()){
            String fileName = file.getCanonicalPath().substring(root.getAbsolutePath().length());
            System.out.println(fileName);
            ArchiveEntry entry = null ;
            if(file.isDirectory()){
                entry = new ZipArchiveEntry(parent,fileName + File.separator) ;
            } else {
                entry = new ZipArchiveEntry(fileName);
            }

            zipOut.putArchiveEntry(entry);
            if(file.isDirectory()){
                zipOut.closeArchiveEntry();
                write(root ,file,zipOut);
            } else {
                IOUtils.copy(new FileInputStream(file), zipOut);
                zipOut.closeArchiveEntry();
            }
        }
    }
}
