package telefonica.aaee.marte.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import telefonica.aaee.marte.helpers.FileHelper;

public class GenericUploadFormController {

	protected static final int MAX_BUFFER = 10000000;
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource
	protected Environment environment;

	public GenericUploadFormController() {
		super();
	}

	protected String createUploadDirIfNotExists(String tempDir) {
		return FileHelper.createUploadDirIfNotExists(tempDir);
	}

	protected String createUploadDirFor977rFiles(String tempDir) {
		return FileHelper.createUploadDirFor977rFiles(tempDir);
	}

	protected int getLinesFromFile(String fileName) {
		
		BufferedReader buffReader;
		int fila = 0;
	    try {
			buffReader = new BufferedReader(new FileReader(fileName));
	
			String line;
			line = buffReader.readLine();
	
			while (true) {
	
			    if (line == null) {
				break;
			    }
	
			    line = buffReader.readLine();
			    fila++;
			}
	
			buffReader.close();
	
	    }catch (Exception e) {
	    	
	    }
	
		return fila;
	}

	/**
	 * 
	 * @param tempDir, el directorio donde guardar el fichero subido
	 * @param multipartFile, el fichero subido
	 * 
	 * @returns fileName, el nombre del fichero guardado
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected String saveFileInDir(String tempDir, MultipartFile multipartFile)
			throws IOException, FileNotFoundException {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS-");
				String prefijo = sdf.format(new Date());
				
				String fileName = tempDir + prefijo + multipartFile.getOriginalFilename();
				logger.info(String.format("fileName.[%s]", fileName));
				
				OutputStream outputStream = null;
				InputStream inputStream = null;
				inputStream = multipartFile.getInputStream();
				outputStream = new FileOutputStream(fileName);
				int readBytes = 0;
				byte[] buffer = new byte[MAX_BUFFER];
				while ((readBytes = inputStream.read(buffer, 0, MAX_BUFFER)) != -1) {
				     outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
				
				return fileName;
			}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView resolveException(
			HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			Object arg2, 
			Exception exception) {
	
		Map<Object, Object> model = new HashMap<Object, Object>();
		if (exception instanceof MaxUploadSizeExceededException) {
			model.put(
					"errors",
					"File size should be less than "
							+ ((MaxUploadSizeExceededException) exception)
									.getMaxUploadSize() + " byte.");
		} else {
			model.put("errors", "Unexpected error: " + exception.getMessage());
			exception.printStackTrace();
		}
//		model.put("FORM", new MUploadForm());
		return new ModelAndView("/404", (Map) model);
	}

}