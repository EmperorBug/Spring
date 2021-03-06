package madvirus.spring.chap07.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{
	public DownloadView() {
		setContentType("application/download; charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)throws Exception{
		File file = (File)model.get("downloadFile");
		
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		
		String userAgent = request.getHeader("User-Agent");
		boolean ie=userAgent.indexOf("MEIS")>-1;
		String fileName = null;
		
		if(ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		}else {
			fileName=new String(file.getName().getBytes("euc-kr"),"iso-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		}finally {
			if(fis!=null)
				try {
					fis.close();
				}catch (IOException e) {
					// TODO: handle exception
				}
		}
		out.flush();
	}

}
