package telefonica.aaee.informes.form;

import java.util.List;

import telefonica.aaee.informes.model.FileInfoDTO;

public class MUploadResultado {

	// Ficheros subidos
	private List<FileInfoDTO> uploadedFiles;
	
	// URLs
	private List<FileInfoDTO> urls;
	
	// Resultados
	private List<String> resultados;
	
	
	/**
	 * 
	 * Getters & Setters
	 * 
	 * @return
	 */
	

	public List<FileInfoDTO> getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(List<FileInfoDTO> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public List<FileInfoDTO> getUrls() {
		return urls;
	}

	public void setUrls(List<FileInfoDTO> urls) {
		this.urls = urls;
	}

	public List<String> getResultados() {
		return resultados;
	}

	public void setResultados(List<String> resultados) {
		this.resultados = resultados;
	}

}
