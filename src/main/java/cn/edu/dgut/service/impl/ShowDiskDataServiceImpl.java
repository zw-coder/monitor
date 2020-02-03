package cn.edu.dgut.service.impl;

import cn.edu.dgut.service.ShowDiskDataService;
import net.sf.json.JSONArray;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowDiskDataServiceImpl implements ShowDiskDataService {
    private Sigar sigar = new Sigar();
    public void ShowDiskData(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {

            //[{name:'c盘',data=[{"label":"未使用","data":20},{}]},{}]
            List<Map<String, Object>> diskList = new ArrayList<Map<String,Object>>();

            //2 获得所有的硬盘
            FileSystem[] fileSystemArray = sigar.getFileSystemList();
            // 遍历
            for (FileSystem fileSystem : fileSystemArray) {
                //3.1 盘符的名称
                String devName = fileSystem.getDevName();

                //#当前硬盘 名称和数据
                Map<String, Object> currentDisk = new HashMap<String, Object>();
                currentDisk.put("name", devName);

                List<Map<String,Object>> currentData = new ArrayList<Map<String,Object>>();
                currentDisk.put("data", currentData);

                diskList.add(currentDisk);


                if(fileSystem.getType() == FileSystem.TYPE_LOCAL_DISK){  //2 表示 本地硬盘
                    // 获得硬盘是否对象
                    FileSystemUsage fileSystemUsage = sigar.getFileSystemUsage(devName);
                    //3.2 总大小
                    long total = fileSystemUsage.getTotal();
                    //3.3 已经使用的
                    long user = fileSystemUsage.getUsed();
                    Map<String,Object> userMap = new HashMap<String, Object>();
                    userMap.put("label", "已使用");
                    userMap.put("data", user);
                    currentData.add(userMap);
                    //3.4 没有使用的
                    long avail = fileSystemUsage.getAvail();
                    Map<String,Object> availMap = new HashMap<String, Object>();
                    availMap.put("label", "未使用");
                    availMap.put("data", avail);
                    currentData.add(availMap);


                }
            }

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(JSONArray.fromObject(diskList).toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
