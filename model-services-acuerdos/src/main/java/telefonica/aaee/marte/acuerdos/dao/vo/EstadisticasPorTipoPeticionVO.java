package telefonica.aaee.marte.acuerdos.dao.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EstadisticasPorTipoPeticionVO {
	
	private List<String> campos = new ArrayList<String>();
	
	private HashMap<String, List<Long>> resultados = new HashMap<String, List<Long>>();
	
	public EstadisticasPorTipoPeticionVO(){}
	
	public EstadisticasPorTipoPeticionVO(List<String> campos, HashMap<String, List<Long>> resultados){
		this.campos = campos;
		this.resultados = resultados;
	}

	public List<String> getCampos() {
		return campos;
	}

	public void setCampos(List<String> campos) {
		this.campos = campos;
	}

	public HashMap<String, List<Long>> getResultados() {
		return resultados;
	}

	public void setResultados(HashMap<String, List<Long>> resultados) {
		this.resultados = resultados;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadisticasPorTipoPeticion [campos=");
		builder.append(campos);
		builder.append(", \nresultados=");
		
		for(String key : SortUtil.asSortedList(resultados.keySet())){
			builder.append("\n" + key + " = ");
			builder.append(resultados.get(key));
		}
		
		builder.append("]");
		return builder.toString();
	}
	
	

}
