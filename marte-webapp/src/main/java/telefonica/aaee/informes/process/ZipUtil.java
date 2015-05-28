package telefonica.aaee.informes.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	
	public File toZip(File inFile){
		
		byte[] buffer = new byte[1024];
		
		String path = inFile.getAbsolutePath();
		path = path.concat(".zip");
		
		File outFile = new File(path);
		
		try {
			FileOutputStream fos = new FileOutputStream(outFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry(inFile.getName());
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream(inFile);

			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			in.close();
			zos.closeEntry();

			//remember close it
			zos.close();
			
			return outFile;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
