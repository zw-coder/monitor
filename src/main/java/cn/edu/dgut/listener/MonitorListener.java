package cn.edu.dgut.listener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;


public class MonitorListener extends ContextLoader implements ServletContextListener {

	//性能核心程序
	private Sigar sigar = new Sigar();
	private Integer showXaxisCount = 10;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//设置CPU
		setCPU(sce);
	}

	// [{'label':'','data':[]} , {}]
	private void setCPU(final ServletContextEvent sce) {
		try {
			//1 获得存放的容器
			List<Map<String, Object>> cpuData = (List<Map<String, Object>>) sce.getServletContext().getAttribute("cpuData");
			if (cpuData == null) {
				cpuData = new ArrayList<Map<String, Object>>();
				sce.getServletContext().setAttribute("cpuData", cpuData);
				// 初始化容器中存储结构
				CpuPerc[] cpuPercArray = sigar.getCpuPercList();
				for (int i = 0; i < cpuPercArray.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("label", "第" + (i + 1) + "块CPU");
					map.put("data", new ArrayList<Object[]>(showXaxisCount));
					cpuData.add(map);
				}

			}
			//2 定时存放数据
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						List<Map<String, Object>> cpuData = (List<Map<String, Object>>) sce.getServletContext().getAttribute("cpuData");
						CpuPerc[] cpuPercArray = sigar.getCpuPercList();
						for (int i = 0; i < cpuPercArray.length; i++) {
							//存放数据容器
							List<Object[]> data = (List<Object[]>) cpuData.get(i).get("data");
							//当前数据
							double combined = cpuPercArray[i].getCombined() * 100d;
							String combinedStr = String.valueOf(combined);
							int ix = combinedStr.indexOf(".") + 1;
							String currentData =combinedStr.substring(0, ix) + combinedStr.substring(ix, ix + 1);
							// 处理数据
							handleData(data,currentData);
						}
//						println(cpuData.get(0));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}, 500, 1000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void handleData(List<Object[]> data , String currentData){
		if(data.size() < showXaxisCount){
			//添加
			data.add(new Object[]{data.size(), currentData});
		} else {
			//换掉第一个
			Object[] arr = data.remove(0);
			arr[1] = currentData;
			data.add(arr);
			//重写设置key
			for (int i = 0; i < data.size(); i++) {
				data.get(i)[0] = i + 1;
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/*
	public static void main(String[] args) throws Exception {

		Random random = new Random();
		Sigar sigar = new Sigar();
		List<Map<String,Object>> cpuData = new ArrayList<Map<String,Object>>();
		CpuPerc[] cpuPercArray = sigar.getCpuPercList();
		for (int i = 0; i < cpuPercArray.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("label", "第"+(i+1)+"块CPU");
			map.put("data", new ArrayList<Integer[]>(10));
			cpuData.add(map);
		}
		while(true){
			Map<String,Object> map = cpuData.get(0);
			println(map);
			Thread.sleep(1000);

			List<Integer[]> data = (List<Integer[]>) map.get("data");
			if(data.size() < 5){
				//添加
				data.add(new Integer[]{data.size(), random.nextInt(10)});
			} else {
				//换掉第一个
				Integer[] arr = data.remove(0);
				arr[1] = random.nextInt(10);
				data.add(arr);
				//重写设置key
				for (int i = 0; i < data.size(); i++) {
					data.get(i)[0] = i + 1;
				}
			}

		}

		//System.out.println(String.valueOf(0.0123456789 * 100.0));


	}

	private static void println(Map<String, Object> map) {
		Iterator<Map.Entry<String, Object>> i = map.entrySet().iterator();
		StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Map.Entry<String, Object> e = i.next();
            String key = e.getKey();
            Object value = e.getValue();
            sb.append(key);
            sb.append('=');
            if (value instanceof List) {
            	List<Integer[]> objArr =  (List<Integer[]>) value;
            	sb.append("[");
            	for(int m = 0 ; m < objArr.size() ; m ++ ){
            		sb.append(Arrays.toString(objArr.get(m)));
            	}
				sb.append("]");
			} else {
				sb.append(value);
			}

            if (! i.hasNext()){
            	sb.append('}');
            	break;
            }
            sb.append(',').append(' ');
        }
       System.out.println(sb.toString());
	}
	*/
}
