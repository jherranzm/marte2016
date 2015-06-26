package telefonica.aaee.marte.helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CalculoFechas {
	private static final int ULTIMO_DIA_ANTES_APERTURA_FX_FACTURACION = 23;
	private static final int ULTIMO_DIA_ANTES_CIERRE_FX_POR_FACTURACION = 13;
	private static final boolean SABADO_ES_FIESTA = true;
	private static final boolean DOMINGO_ES_FIESTA = true;

	private static final Log logger = LogFactory.getLog(CalculoFechas.class);

	
	private static List<Date> diasDeFiesta = new ArrayList<Date>();

	static {
		populateDiasDeFiestaNacionales();
	}
	
	private static boolean esFiesta(Date date){
		boolean ret = false;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		if(SABADO_ES_FIESTA && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) ){
			return true;
		}
		
		if(DOMINGO_ES_FIESTA && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) ){
			return true;
		}
		
		for(Date fiesta : diasDeFiesta){
			if(DateUtils.isSameDay(date, fiesta)){
				return true;
			}
		}
		
		return ret;
	}
	
	private static Date manyana(Date date){
		date = DateUtils.addDays(date, 1);
		date = DateUtils.setHours(date, 8);
		date = DateUtils.setMinutes(date, 0);
		date = DateUtils.setSeconds(date, 0);
		
		return date;
	}

	private static void populateDiasDeFiestaNacionales() {
		java.util.Date fiesta = Calendar.getInstance().getTime();
			// Año Nuevo
			fiesta = DateUtils.setMonths(fiesta, Calendar.JANUARY); // 0 based
			fiesta = DateUtils.setDays(fiesta, 1);
			diasDeFiesta.add(fiesta);
			
			// Reyes
			fiesta = DateUtils.setMonths(fiesta, Calendar.JANUARY);
			fiesta = DateUtils.setDays(fiesta, 6);
			diasDeFiesta.add(fiesta);
			
			// Primero de Mayo
			fiesta = DateUtils.setMonths(fiesta, Calendar.MAY);
			fiesta = DateUtils.setDays(fiesta, 1);
			diasDeFiesta.add(fiesta);
			
			// Sant Joan
			fiesta = DateUtils.setMonths(fiesta, Calendar.JUNE);
			fiesta = DateUtils.setDays(fiesta, 24);
			diasDeFiesta.add(fiesta);
			
			// 15 Agosto
			fiesta = DateUtils.setMonths(fiesta, Calendar.AUGUST);
			fiesta = DateUtils.setDays(fiesta, 15);
			diasDeFiesta.add(fiesta);
			
			// Constitución
			fiesta = DateUtils.setMonths(fiesta, Calendar.DECEMBER);
			fiesta = DateUtils.setDays(fiesta, 6);
			diasDeFiesta.add(fiesta);
			
			// Inmaculada
			fiesta = DateUtils.setMonths(fiesta, Calendar.DECEMBER);
			fiesta = DateUtils.setDays(fiesta, 8);
			diasDeFiesta.add(fiesta);
			
			// Navidad
			fiesta = DateUtils.setMonths(fiesta, Calendar.DECEMBER);
			fiesta = DateUtils.setDays(fiesta, 25);
			diasDeFiesta.add(fiesta);
			
			// Sant Esteve
			fiesta = DateUtils.setMonths(fiesta, Calendar.DECEMBER);
			fiesta = DateUtils.setDays(fiesta, 26);
			diasDeFiesta.add(fiesta);
			
			CalculoFechas.getEasterSundayDate(Calendar.getInstance().get(Calendar.YEAR));
	}

	/**
	 * 
	 * @param fecha
	 * @param mesSiguiente, si pasa del día 13, es traspasable al mes siguiente?
	 * @return
	 */
	public static Date primerDiaHabil(java.util.Date fecha, boolean mesSiguiente) {
		logger.info(String.format("Fecha petición       : [%s]", fecha));
		
		java.util.Date hora1430 = Calendar.getInstance().getTime();
		hora1430  = DateUtils.setHours(hora1430, 14);
		hora1430  = DateUtils.setMinutes(hora1430, 30);
		
		java.util.Date dia13 = Calendar.getInstance().getTime();
		dia13 = DateUtils.setDays(fecha, ULTIMO_DIA_ANTES_CIERRE_FX_POR_FACTURACION);
		
		java.util.Date dia23 = Calendar.getInstance().getTime();
		dia23 = DateUtils.setDays(fecha, ULTIMO_DIA_ANTES_APERTURA_FX_FACTURACION);
		
		int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		logger.info(String.format("Último día del mes       : [%d]", lastDay));
		logger.info(String.format("Último día hábil del mes : [%d]", lastDay-2));
		
		// Si la petición se hace a partir de las 14h30 es para el día siguiente
		if(fecha.after(hora1430)){
			logger.info(String.format("A partir de las 1430, día siguiente: [%s]", fecha));
			fecha = manyana(fecha);
			while(esFiesta(fecha)){
				fecha = manyana(fecha);
			}
		}else{
			while(esFiesta(fecha)){
				fecha = manyana(fecha);
			}
		}
		
		// Las peticiones que hay que pasar al mes siguiente
		if(fecha.after(dia13) && mesSiguiente){
			logger.info(String.format("Para el mes siguiente: [%s]", fecha));
			fecha = DateUtils.addMonths(fecha, 1);
			fecha = DateUtils.setDays(fecha, 1);
			fecha  = DateUtils.setHours(fecha, 8);
			fecha  = DateUtils.setMinutes(fecha, 0);
			fecha  = DateUtils.setSeconds(fecha, 0);
			while(esFiesta(fecha)){
				fecha = manyana(fecha);
			}
		}

		// Para todas las peticiones... entre el 13 y el 23 NO se puede tramitar en FX.
		// Se pasan al 23
		if(fecha.after(dia13) && fecha.before(dia23)){
			logger.info(String.format("Periodo facturación 'ABIERTA': [%s]", fecha));
			fecha = DateUtils.setDays(fecha, 23);
			while(esFiesta(fecha)){
				fecha = manyana(fecha);
			}
		}
		
		logger.info(String.format("Primer día hábil     : [%s]", fecha));
		return fecha;
	}
	
	public static java.sql.Date getSQLDatePrimerDiaHabil(java.util.Date fecha, boolean mesSiguiente){
		fecha = primerDiaHabil(fecha, mesSiguiente);
		return new java.sql.Date(fecha.getTime());
	}
	
	public static String getEasterSundayDate(int year)
    {
        int a = year % 19,
            b = year / 100,
            c = year % 100,
            d = b / 4,
            e = b % 4,
            g = (8 * b + 13) / 25,
            h = (19 * a + b - d - g + 15) % 30,
            j = c / 4,
            k = c % 4,
            m = (a + 11 * h) / 319,
            r = (2 * e + 2 * j - k - h + m + 32) % 7,
            n = (h - m + r + 90) / 25,
            p = (h - m + r + n + 19) % 32;
        
        java.util.Date fecha = Calendar.getInstance().getTime();
        fecha = org.apache.commons.lang.time.DateUtils.setYears(fecha, year);
        fecha = org.apache.commons.lang.time.DateUtils.setMonths(fecha, n-1);
        fecha = org.apache.commons.lang.time.DateUtils.setDays(fecha, p);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        logger.info(String.format("Domingo de Resurrección: [%s]",sdf.format(fecha)));

        java.util.Date viernesSanto = org.apache.commons.lang.time.DateUtils.addDays(fecha, -2);
        logger.info(sdf.format(viernesSanto));
        diasDeFiesta.add(viernesSanto);
        logger.info(String.format("Viernes Santo: [%s]",sdf.format(viernesSanto)));
        java.util.Date lunesPascua = org.apache.commons.lang.time.DateUtils.addDays(fecha, +1);
        logger.info(sdf.format(lunesPascua));
        diasDeFiesta.add(lunesPascua);
        logger.info(String.format("Lunes de Pascua: [%s]",sdf.format(lunesPascua)));
        
        String result;
        switch(n)
        {
            case 1:
                result = "January ";
                break;
            case 2:
                result = "February ";
                break;
            case 3:
                result = "March ";
                break;
            case 4:
                result = "April ";
                break;
            case 5:
                result = "May ";
                break;
            case 6:
                result = "June ";
                break;
            case 7:
                result = "July ";
                break;
            case 8:
                result = "August ";
                break;
            case 9:
                result = "September ";
                break;
            case 10:
                result = "October ";
                break;
            case 11:
                result = "November ";
                break;
            case 12:
                result = "December ";
                break;
            default:
                result = "error";
        }

        return result + p;
    }
	
	public static void main (String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		logger.info(CalculoFechas.getEasterSundayDate(2015));
		for(Date date : diasDeFiesta){
			logger.info(String.format("Fiesta: [%s]",sdf.format(date)));
		}
		
		java.util.Date fecha = Calendar.getInstance().getTime();
		fecha = primerDiaHabil(fecha, false);
		logger.info(String.format("Primer día hábil: [%s]",sdf.format(fecha)));
		
		java.util.Date fecha13hora1430 = Calendar.getInstance().getTime();
		fecha13hora1430 = DateUtils.setDays(fecha13hora1430, 13);
		fecha13hora1430 = DateUtils.setHours(fecha13hora1430, 14);
		fecha13hora1430 = DateUtils.setMinutes(fecha13hora1430, 30);
		
		fecha13hora1430 = primerDiaHabil(fecha13hora1430, false);
		logger.info(String.format("Primer día hábil 13/02/2015 14:30: [%s]",sdf.format(fecha13hora1430)));

		java.util.Date fecha13hora1430_tipo14 = Calendar.getInstance().getTime();
		fecha13hora1430_tipo14 = DateUtils.setDays(fecha13hora1430_tipo14, 13);
		fecha13hora1430_tipo14 = DateUtils.setHours(fecha13hora1430_tipo14, 14);
		fecha13hora1430_tipo14 = DateUtils.setMinutes(fecha13hora1430_tipo14, 30);
		
		fecha13hora1430_tipo14 = primerDiaHabil(fecha13hora1430_tipo14, true);
		logger.info(String.format("Primer día hábil 13/02/2015 14:30 (tipo 14): [%s]",sdf.format(fecha13hora1430_tipo14)));
	}
}
